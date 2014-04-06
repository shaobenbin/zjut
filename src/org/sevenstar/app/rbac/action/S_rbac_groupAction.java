package org.sevenstar.app.rbac.action;

import java.util.List;

import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.util.BeanHelper;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;
import org.sevenstar.web.annotation.SSList;
import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.rbac.domain.S_rbac_groupDomain;
import org.sevenstar.app.rbac.domain.S_rbac_roleDomain;
import org.sevenstar.app.rbac.relation.domain.S_rbac_grouproleDomain;
 @SSAction(name="s_rbac_group")
public class S_rbac_groupAction extends DefaultAction   {
	private S_rbac_groupDomain s_rbac_groupDomain;
 	@SSList(type="org.sevenstar.app.rbac.domain.S_rbac_groupDomain")
	private List groupList;
	@SSList(type="org.sevenstar.app.rbac.domain.S_rbac_roleDomain")
	private List roleList;
	
	private IbatisPage page;
	
	public IbatisPage getPage() {
		return page;
	}


	public void setPage(IbatisPage page) {
		this.page = page;
	}
	

	public IbatisPage query() {
		if (s_rbac_groupDomain == null) {
			s_rbac_groupDomain = new S_rbac_groupDomain();
		}
		page = s_rbac_groupDomain.getSelectPage(page);
		List dataList = page.getDataList();
		if (dataList != null && dataList.size() > 0) {
			for (int i = 0; i < dataList.size(); i++) {
				S_rbac_groupDomain groupDomain = (S_rbac_groupDomain) dataList.get(i);
				S_rbac_groupDomain newGroupDomain = new S_rbac_groupDomain();
				BeanHelper.copyNotNullPrimitiveProperties(groupDomain,
						newGroupDomain);
				dataList.set(i, newGroupDomain);
			}
		}
		return page;
	}
	
	public String deleteList() throws Exception {
		if (groupList != null && groupList.size() > 0) {
			for (int i = 0; i < groupList.size(); i++) {
				S_rbac_groupDomain s_rbac_groupDomain = (S_rbac_groupDomain) groupList
						.get(i);
				s_rbac_groupDomain.delete();
			}
		} else {
			throw new ApplicationException("请先选择组");
		}
		return "批量删除组成功";
	}

	public String delete() throws Exception {
		super.setMsg("删除组[" + s_rbac_groupDomain.getName() + "]成功");
		s_rbac_groupDomain.delete();
		super.setNextUrl("app/rbac/group/list.html");
		return super.REDIRECT;
	}

	public String insert() throws Exception {
		s_rbac_groupDomain.insert();
		if(roleList != null && roleList.size() > 0){
			for(int i=0;i<roleList.size();i++){
				S_rbac_grouproleDomain s_rbac_grouproleDomain = new S_rbac_grouproleDomain();
				s_rbac_grouproleDomain.setGroupDomain(s_rbac_groupDomain);
				s_rbac_grouproleDomain.setRoleDomain((S_rbac_roleDomain)roleList.get(i));
				s_rbac_grouproleDomain.insert();
			}
		}
		super.setMsg("添加组[" + s_rbac_groupDomain.getName() + "]成功");
		super.setNextUrl("app/rbac/group/list.html");
		return super.REDIRECT;
	}

	public String update() throws Exception {
		s_rbac_groupDomain.update();
		doUpdateRole();
		super.setMsg("修改组[" + s_rbac_groupDomain.getName() + "]成功");
		super.setNextUrl("app/rbac/group/list.html");
		return super.REDIRECT;
	}

	private void doUpdateRole() {
		S_rbac_grouproleDomain paramDomain = new S_rbac_grouproleDomain();
		paramDomain.setGroupDomain(s_rbac_groupDomain);
		List groupRoleList = paramDomain.selectEqual();
		for(int i=0;i<groupRoleList.size();i++){
			S_rbac_grouproleDomain s_rbac_grouproleDomain = (S_rbac_grouproleDomain)groupRoleList.get(i);
			s_rbac_grouproleDomain.delete();
		}
		if(roleList != null && roleList.size() > 0){
			for(int i=0;i<roleList.size();i++){
				S_rbac_grouproleDomain s_rbac_grouproleDomain = new S_rbac_grouproleDomain();
				s_rbac_grouproleDomain.setGroupDomain(s_rbac_groupDomain);
				s_rbac_grouproleDomain.setRoleDomain((S_rbac_roleDomain)roleList.get(i));
				s_rbac_grouproleDomain.insert();
			}
		}
	}

	public String insertOrUpdate() throws Exception {
		s_rbac_groupDomain.insertOrUpdate();
		doUpdateRole();
		return "编辑组[" + s_rbac_groupDomain.getName() + "]成功";
	}
	public S_rbac_groupDomain getS_rbac_groupDomain() {
		return s_rbac_groupDomain;
	}
	public void setS_rbac_groupDomain(S_rbac_groupDomain s_rbac_groupDomain) {
		this.s_rbac_groupDomain = s_rbac_groupDomain;
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

}
