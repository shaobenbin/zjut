package org.sevenstar.app.page.app.manager.org;

import java.util.List;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.manager.domain.S_org_positionDomain;
import org.sevenstar.app.rbac.domain.S_rbac_roleDomain;
import org.sevenstar.util.BeanHelper;
import org.sevenstar.web.action.java.DefaultAction;
/**
 * @author rtm 2008-5-8
 */
public class Position extends DefaultAction {
	
 

	private S_org_positionDomain s_org_positionDomain;

	private List roleList;


	public String input() {
		if (s_org_positionDomain == null
				|| s_org_positionDomain.getOrgDomain() == null
				|| s_org_positionDomain.getOrgDomain().getId() == null) {
			throw new ApplicationException("请先选择部门");
		}
		s_org_positionDomain.getOrgDomain().load();
		roleList = (new S_rbac_roleDomain()).selectAll();
		return "app/manager/org/position/edit.html";
	}

	public String edit() {
		if (s_org_positionDomain == null
				|| s_org_positionDomain.getId() == null) {
			throw new ApplicationException("请先选择职位");
		}
		s_org_positionDomain.load();
		roleList = BeanHelper.copyNewList((new S_rbac_roleDomain()).selectAll());
		/**
		 * 清理已选中选项
		 */
		List hasSelectRoleList = s_org_positionDomain.getRoleList();
		for (int i = 0; i < roleList.size(); i++) {
			S_rbac_roleDomain domain = (S_rbac_roleDomain) roleList.get(i);
			for (int j = 0; j < hasSelectRoleList.size(); j++) {
				S_rbac_roleDomain selectedRoleDomain = (S_rbac_roleDomain) hasSelectRoleList
						.get(j);
				if (selectedRoleDomain.getId().equals(domain.getId())) {
					roleList.remove(i);
					i--;
					continue;
				}
			}
		}
		return super.SUCCESS;
	}

 
 

	public S_org_positionDomain getS_org_positionDomain() {
		return s_org_positionDomain;
	}

	public void setS_org_positionDomain(S_org_positionDomain domain) {
		s_org_positionDomain = domain;
	}

	public List getRoleList() {
		return roleList;
	}

	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}

}
