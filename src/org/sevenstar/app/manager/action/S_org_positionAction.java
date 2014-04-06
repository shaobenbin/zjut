package org.sevenstar.app.manager.action;

import java.util.List;

import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.util.BeanHelper;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;
import org.sevenstar.web.annotation.SSList;
import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.manager.domain.S_org_positionDomain;
import org.sevenstar.app.manager.relation.domain.S_org_positionroleDomain;
import org.sevenstar.app.rbac.domain.S_rbac_roleDomain;

@SSAction(name = "s_org_position")
public class S_org_positionAction extends DefaultAction {
	private S_org_positionDomain s_org_positionDomain;

	@SSList(type = "org.sevenstar.app.manager.domain.S_org_positionDomain")
	private List positionList;

	@SSList(type = "org.sevenstar.app.rbac.domain.S_rbac_roleDomain")
	private List roleList;

	private IbatisPage page;

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}

	public IbatisPage query() {
		if (s_org_positionDomain == null) {
			s_org_positionDomain = new S_org_positionDomain();
		}
		page = s_org_positionDomain.getSelectPage(page);
		List dataList = page.getDataList();
		if (dataList != null && dataList.size() > 0) {
			for (int i = 0; i < dataList.size(); i++) {
				S_org_positionDomain positionDomain = (S_org_positionDomain) dataList
						.get(i);
				S_org_positionDomain newPositionDomain = new S_org_positionDomain();
				BeanHelper.copyNotNullPrimitiveProperties(positionDomain,
						newPositionDomain);
				dataList.set(i, newPositionDomain);
			}
		}
		return page;
	}

	public String deleteList() throws Exception {
		if (positionList == null || positionList.size() == 0) {
			throw new ApplicationException("请先选择职位");
		}
		super.setMsg("批量删除职位成功");
		for (int i = 0; i < positionList.size(); i++) {
			S_org_positionDomain positionDomain = (S_org_positionDomain) positionList
					.get(i);
			positionDomain.load();
			super
					.setNextUrl("app/manager/org/position/list.htm?s_orgDomain.id="
							+ positionDomain.getOrgDomain().getId());
			positionDomain.delete();
		}
		return super.REDIRECT;
	}

	public String delete() throws Exception {
		super.setMsg("删除职位[" + s_org_positionDomain.getName() + "]成功");
		s_org_positionDomain.load();
		super.setNextUrl("app/manager/org/position/list.htm?s_orgDomain.id="
				+ s_org_positionDomain.getOrgDomain().getId());
		s_org_positionDomain.delete();
		return super.REDIRECT;
	}

	public String insert() throws Exception {
		s_org_positionDomain.insert();
		if (roleList != null && roleList.size() > 0) {
			for (int i = 0; i < roleList.size(); i++) {
				S_org_positionroleDomain s_org_positionroleDomain = new S_org_positionroleDomain();
				s_org_positionroleDomain
						.setPositionDomain(s_org_positionDomain);
				s_org_positionroleDomain
						.setRoleDomain((S_rbac_roleDomain) roleList.get(i));
				s_org_positionroleDomain.insert();
			}
		}
		super.setMsg("增加职位[" + s_org_positionDomain.getName() + "]成功");
		super.setNextUrl("app/manager/org/position/list.htm?s_orgDomain.id="
				+ s_org_positionDomain.getOrgDomain().getId());
		return super.REDIRECT;
	}

	public String update() throws Exception {
		s_org_positionDomain.update();
		doUpdateRole();
		super.setMsg("修改职位[" + s_org_positionDomain.getName() + "]成功");
		super.setNextUrl("app/manager/org/position/list.htm?s_orgDomain.id="
				+ s_org_positionDomain.getOrgDomain().getId());
		return super.REDIRECT;
	}

	private void doUpdateRole() {
		S_org_positionroleDomain paramDomain = new S_org_positionroleDomain();
		paramDomain.setPositionDomain(s_org_positionDomain);
		List groupRoleList = paramDomain.selectEqual();
		for (int i = 0; i < groupRoleList.size(); i++) {
			S_org_positionroleDomain positionroleDomain = (S_org_positionroleDomain) groupRoleList
					.get(i);
			positionroleDomain.delete();
		}
		if (roleList != null && roleList.size() > 0) {
			for (int i = 0; i < roleList.size(); i++) {
				S_org_positionroleDomain s_org_positionroleDomain = new S_org_positionroleDomain();
				s_org_positionroleDomain
						.setPositionDomain(s_org_positionDomain);
				s_org_positionroleDomain
						.setRoleDomain((S_rbac_roleDomain) roleList.get(i));
				s_org_positionroleDomain.insert();
			}
		}
	}

	public String insertOrUpdate() throws Exception {
		s_org_positionDomain.insertOrUpdate();
		doUpdateRole();
		return "编辑职位[" + s_org_positionDomain.getName() + "]成功";
	}

	public S_org_positionDomain getS_org_positionDomain() {
		return s_org_positionDomain;
	}

	public void setS_org_positionDomain(
			S_org_positionDomain s_org_positionDomain) {
		this.s_org_positionDomain = s_org_positionDomain;
	}

	public List getPositionList() {
		return positionList;
	}

	public void setPositionList(List positionList) {
		this.positionList = positionList;
	}

	public List getRoleList() {
		return roleList;
	}

	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}

}
