package org.sevenstar.app.page.app.rbac;

import java.util.List;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.rbac.domain.S_rbac_groupDomain;
import org.sevenstar.app.rbac.domain.S_rbac_roleDomain;
import org.sevenstar.app.system.domain.S_systemDomain;
import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.util.BeanHelper;
import org.sevenstar.web.action.java.DefaultAction;
/**
 * @author rtm 2008-5-8
 */
public class Group extends DefaultAction {
	private S_rbac_groupDomain s_rbac_groupDomain;

	private IbatisPage page;

	private List roleList;
	
	private List systemList;

	 
	public String list() {
		if (s_rbac_groupDomain == null) {
			s_rbac_groupDomain = new S_rbac_groupDomain();
		}
		page = s_rbac_groupDomain.getSelectPage(page);
		return super.SUCCESS;
	}

	public String edit() {
		if (s_rbac_groupDomain == null) {
			throw new ApplicationException("没有选中组");
		}
		s_rbac_groupDomain.load();
		roleList = BeanHelper.copyNewList((new S_rbac_roleDomain()).selectAll());
		/**
		 * 清理已选中选项
		 */
		List hasSelectRoleList = s_rbac_groupDomain.getRoleList();
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
		systemList = (new S_systemDomain()).selectAll();
		return super.SUCCESS;
	}

	public String view() {
		if (s_rbac_groupDomain == null) {
			throw new ApplicationException("没有选中组");
		}
		s_rbac_groupDomain.load();
		return super.SUCCESS;
	}

	public String input() {
		roleList = (new S_rbac_roleDomain()).selectAll();
		systemList = (new S_systemDomain()).selectAll();
		return "app/rbac/group/edit.html";
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}

	public S_rbac_groupDomain getS_rbac_groupDomain() {
		return s_rbac_groupDomain;
	}

	public void setS_rbac_groupDomain(S_rbac_groupDomain domain) {
		s_rbac_groupDomain = domain;
	}

	public List getRoleList() {
		return roleList;
	}

	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}

	public List getSystemList() {
		return systemList;
	}

	public void setSystemList(List systemList) {
		this.systemList = systemList;
	}

 

}
