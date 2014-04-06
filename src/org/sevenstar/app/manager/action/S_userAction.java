package org.sevenstar.app.manager.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;
import org.sevenstar.web.annotation.SSList;
import org.sevenstar.web.context.WebContext;
import org.sevenstar.app.common.context.ApplicationContext;
import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.manager.domain.S_org_positionDomain;
import org.sevenstar.app.manager.domain.S_userDomain;
import org.sevenstar.app.manager.relation.domain.S_userpositionDomain;
import org.sevenstar.app.manager.service.S_userService;
import org.sevenstar.app.rbac.domain.S_rbac_groupDomain;
import org.sevenstar.app.rbac.domain.S_rbac_roleDomain;
import org.sevenstar.app.rbac.relation.domain.S_rbac_usergroupDomain;
import org.sevenstar.app.rbac.relation.domain.S_rbac_userroleDomain;
import org.sevenstar.app.utils.Constant;
import org.sevenstar.app.utils.CookieUtil;
import org.sevenstar.component.security.MD5;
import org.sevenstar.util.BeanHelper;

@SSAction(name = "s_user")
public class S_userAction extends DefaultAction {
	private S_userDomain s_userDomain;
	private S_userService s_userService = new S_userService();

	@SSList(type = "org.sevenstar.app.manager.domain.S_userDomain")
	private List userList;

	@SSList(type = "org.sevenstar.app.rbac.domain.S_rbac_groupDomain")
	private List groupList;

	@SSList(type = "org.sevenstar.app.rbac.domain.S_rbac_roleDomain")
	private List roleList;
	@SSList(type = "org.sevenstar.app.manager.domain.S_org_positionDomain")
	private List positionList;

	private IbatisPage page;

	public String logout() {
		ApplicationContext.remove();
		super.setNextUrl("login.html");
		return super.REDIRECT;
	}

	public IbatisPage query() {
		if (s_userDomain == null) {
			s_userDomain = new S_userDomain();
		}
		page = s_userDomain.getSelectPage(page);
		List dataList = page.getDataList();
		if (dataList != null && dataList.size() > 0) {
			for (int i = 0; i < dataList.size(); i++) {
				S_userDomain userDomain = (S_userDomain) dataList.get(i);
				S_userDomain newUserDomain = new S_userDomain();
				BeanHelper.copyNotNullPrimitiveProperties(userDomain,
						newUserDomain);
				dataList.set(i, newUserDomain);
			}
		}
		return page;
	}

	public String deleteList() throws Exception {
		if (userList != null && userList.size() > 0) {
			for (int i = 0; i < userList.size(); i++) {
				S_userDomain userDomain = (S_userDomain) userList.get(i);
				userDomain.delete();
			}
		} else {
			throw new ApplicationException("请先选择用户");
		}
		return "批量删除用户成功";
	}

	public String login() {
		if (s_userDomain == null) {
			throw new ApplicationException("请先输入用户名密码");
		}
		s_userService.login(s_userDomain);
		ApplicationContext.put(s_userDomain);

		CookieUtil.setCookie(WebContext.getResponse(), Constant.HCOA_LOGINNAME,
				s_userDomain.getName(), Integer.MAX_VALUE);

		super.setNextUrl("index.html");
		return super.REDIRECT;
	}

	public String portalLogin() {
		login();
		super.setNextUrl("app/portal/index.html");
		return super.REDIRECT;
	}

	public String delete() throws Exception {
		super.setMsg("删除用户[" + s_userDomain.getName() + "]成功");
		s_userDomain.delete();
		return super.getMsg();
	}

	public String insert() throws Exception {
		MD5 md5 = new MD5();
		if (s_userDomain.getPasswd() != null && !"".equals(s_userDomain)) {
			s_userDomain.setPasswd(md5.getMD5ofStr(s_userDomain.getPasswd()));
		}
		s_userDomain.insert();
		if (groupList != null && groupList.size() > 0) {
			for (int i = 0; i < groupList.size(); i++) {
				S_rbac_usergroupDomain s_rbac_usergroupDomain = new S_rbac_usergroupDomain();
				s_rbac_usergroupDomain.setUserDomain(s_userDomain);
				s_rbac_usergroupDomain
						.setGroupDomain((S_rbac_groupDomain) groupList.get(i));
				s_rbac_usergroupDomain.insert();
			}
		}
		if (roleList != null && roleList.size() > 0) {
			for (int i = 0; i < roleList.size(); i++) {
				S_rbac_userroleDomain s_rbac_grouproleDomain = new S_rbac_userroleDomain();
				s_rbac_grouproleDomain.setUserDomain(s_userDomain);
				s_rbac_grouproleDomain
						.setRoleDomain((S_rbac_roleDomain) roleList.get(i));
				s_rbac_grouproleDomain.insert();
			}
		}
		if (positionList != null && positionList.size() > 0) {
			for (int i = 0; i < positionList.size(); i++) {
				S_userpositionDomain s_userpositionDomain = new S_userpositionDomain();
				s_userpositionDomain.setUserDomain(s_userDomain);
				s_userpositionDomain
						.setPositionDomain((S_org_positionDomain) positionList
								.get(i));
				s_userpositionDomain.insert();
			}
		}
		super.setMsg("添加用户[" + s_userDomain.getName() + "]成功");
		super.setNextUrl("app/manager/user/list.html");
		return super.REDIRECT;
	}

	public String update() throws Exception {
		if (s_userDomain.getPasswd() != null
				&& !"".equals(s_userDomain.getPasswd())
				&& s_userDomain.getPasswd().length() != 32) {
			MD5 md5 = new MD5();
			s_userDomain.setPasswd(md5.getMD5ofStr(s_userDomain.getPasswd()));
		}
		s_userDomain.update();
		doUpdateRbac();
		super.setMsg("修改用户[" + s_userDomain.getName() + "]成功");
		super.setNextUrl("app/manager/user/list.html");
		return super.REDIRECT;
	}

	private void doUpdateRbac() {
		/**
		 * 删除组
		 */
		S_rbac_usergroupDomain s_rbac_usergroupDomain = new S_rbac_usergroupDomain();
		s_rbac_usergroupDomain.setUserDomain(s_userDomain);
		List userGroupList = s_rbac_usergroupDomain.selectEqual();
		for (int i = 0; i < userGroupList.size(); i++) {
			S_rbac_usergroupDomain domain = (S_rbac_usergroupDomain) userGroupList
					.get(i);
			domain.delete();
		}
		/**
		 * 删除角色
		 */
		S_rbac_userroleDomain s_rbac_userroleDomain = new S_rbac_userroleDomain();
		s_rbac_userroleDomain.setUserDomain(s_userDomain);
		List userRoleList = s_rbac_userroleDomain.selectEqual();
		for (int i = 0; i < userRoleList.size(); i++) {
			S_rbac_userroleDomain domain = (S_rbac_userroleDomain) userRoleList
					.get(i);
			domain.delete();
		}
		/**
		 * 删除职位
		 */
		S_userpositionDomain s_userpositionDomain = new S_userpositionDomain();
		s_userpositionDomain.setUserDomain(s_userDomain);
		List userPositionList = s_userpositionDomain.selectEqual();
		for (int i = 0; i < userPositionList.size(); i++) {
			S_userpositionDomain domain = (S_userpositionDomain) userPositionList
					.get(i);
			domain.delete();
		}
		/**
		 * 添加组
		 */
		if (groupList != null && groupList.size() > 0) {
			for (int i = 0; i < groupList.size(); i++) {
				S_rbac_usergroupDomain usergroupDomain = new S_rbac_usergroupDomain();
				usergroupDomain.setUserDomain(s_userDomain);
				usergroupDomain.setGroupDomain((S_rbac_groupDomain) groupList
						.get(i));
				usergroupDomain.insert();
			}
		}
		/**
		 * 添加角色
		 */
		if (roleList != null && roleList.size() > 0) {
			for (int i = 0; i < roleList.size(); i++) {
				S_rbac_userroleDomain userroleDomain = new S_rbac_userroleDomain();
				userroleDomain.setUserDomain(s_userDomain);
				userroleDomain.setRoleDomain((S_rbac_roleDomain) roleList
						.get(i));
				userroleDomain.insert();
			}
		}
		/**
		 * 添加职位
		 */
		if (positionList != null && positionList.size() > 0) {
			for (int i = 0; i < positionList.size(); i++) {
				S_userpositionDomain userpositionDomain = new S_userpositionDomain();
				userpositionDomain.setUserDomain(s_userDomain);
				userpositionDomain
						.setPositionDomain((S_org_positionDomain) positionList
								.get(i));
				userpositionDomain.insert();
			}
		}
	}

	public String insertOrUpdate() throws Exception {
		if (s_userDomain.getPasswd() != null
				&& !"".equals(s_userDomain.getPasswd())
				&& s_userDomain.getPasswd().length() != 32) {
			MD5 md5 = new MD5();
			s_userDomain.setPasswd(md5.getMD5ofStr(s_userDomain.getPasswd()));
		}
		s_userDomain.insertOrUpdate();
		doUpdateRbac();
		return "编辑用户[" + s_userDomain.getName() + "]成功";
	}

	public S_userDomain getS_userDomain() {
		return s_userDomain;
	}

	public void setS_userDomain(S_userDomain s_userDomain) {
		this.s_userDomain = s_userDomain;
	}

	public S_userService getS_userService() {
		return s_userService;
	}

	public void setS_userService(S_userService s_userService) {
		this.s_userService = s_userService;
	}

	public List getUserList() {
		return userList;
	}

	public void setUserList(List userList) {
		this.userList = userList;
	}

	public List getGroupList() {
		return groupList;
	}

	public void setGroupList(List groupList) {
		this.groupList = groupList;
	}

	public List getRoleList() {
		return roleList;
	}

	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}

	public List getPositionList() {
		return positionList;
	}

	public void setPositionList(List positionList) {
		this.positionList = positionList;
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}

}
