package org.sevenstar.app.page.app.rbac;

import java.util.ArrayList;
import java.util.List;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.module.domain.S_moduleDomain;
import org.sevenstar.app.module.service.S_moduleService;
import org.sevenstar.app.rbac.domain.S_rbac_permissionDomain;
import org.sevenstar.app.rbac.domain.S_rbac_roleDomain;
import org.sevenstar.app.system.domain.S_systemDomain;
import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.util.BeanHelper;
import org.sevenstar.web.action.java.DefaultAction;

/**
 * @author rtm 2008-5-8
 */
public class Role extends DefaultAction {
	private S_rbac_roleDomain s_rbac_roleDomain;

	private IbatisPage page;

	private List permissionList;

	private List moduleList;
	
	private List systemList;

	private S_moduleService s_moduleService = new S_moduleService();

	public String list() {
		if (s_rbac_roleDomain == null) {
			s_rbac_roleDomain = new S_rbac_roleDomain();
		}
		page = s_rbac_roleDomain.getSelectPage(page);
		return super.SUCCESS;
	}

	public String edit() {
		if (s_rbac_roleDomain == null) {
			throw new ApplicationException("没有选中角色");
		}
		s_rbac_roleDomain.load();
		permissionList = BeanHelper.copyNewList((new S_rbac_permissionDomain()).selectAll());
		/**
		 * 清理已选中选项
		 */
		List hasSelectPermissionList = s_rbac_roleDomain.getPermissionList();
		for (int i = 0; i < permissionList.size(); i++) {
			S_rbac_permissionDomain domain = (S_rbac_permissionDomain) permissionList
					.get(i);
			for (int j = 0; j < hasSelectPermissionList.size(); j++) {
				S_rbac_permissionDomain selectedPermissionDomain = (S_rbac_permissionDomain) hasSelectPermissionList
						.get(j);
				if (selectedPermissionDomain.getId().equals(domain.getId())) {
					permissionList.remove(i);
					i--;
					break;
				}
			}
		}

		List firstLevelModuleList = s_moduleService.selectFirstLevel("后台管理系统");
		moduleList = new ArrayList();
		for(int i=0;i<firstLevelModuleList.size();i++){
			addModule((S_moduleDomain)firstLevelModuleList.get(i));
		}
		/**
		 * 清理已选中选项
		 */
		List hasSelectedModuleList = s_rbac_roleDomain.getModuleList();
		for(int i=0;i<moduleList.size();i++){
			S_moduleDomain moduleDomain = (S_moduleDomain)moduleList.get(i);
			for(int j=0;j<hasSelectedModuleList.size();j++){
				S_moduleDomain selectedModuleDomain = (S_moduleDomain)hasSelectedModuleList.get(j);
				if(selectedModuleDomain.getId().equals(moduleDomain.getId())){
					this.moduleList.remove(i);
					i--;
					break;
				}
			}
		}
		systemList = (new S_systemDomain()).selectAll();
		return super.SUCCESS;
	}

 

	public String input() {
		permissionList = (new S_rbac_permissionDomain()).selectAll();
		List firstLevelModuleList = s_moduleService.selectFirstLevel("后台管理系统");
		moduleList = new ArrayList();
		for(int i=0;i<firstLevelModuleList.size();i++){
			addModule((S_moduleDomain)firstLevelModuleList.get(i));
		}
		systemList = (new S_systemDomain()).selectAll();
		return "app/rbac/role/edit.html";
	}

	private void addModule(S_moduleDomain s_moduleDomain){
		moduleList.add(s_moduleDomain);
		List childList = s_moduleDomain.getChildList();
		for(int i=0;i<childList.size();i++){
			addModule((S_moduleDomain)childList.get(i));
		}
	}

	public S_rbac_roleDomain getS_rbac_roleDomain() {
		return s_rbac_roleDomain;
	}

	public void setS_rbac_roleDomain(S_rbac_roleDomain domain) {
		s_rbac_roleDomain = domain;
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}

	public List getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List permissionList) {
		this.permissionList = permissionList;
	}

	public List getModuleList() {
		return moduleList;
	}

	public void setModuleList(List moduleList) {
		this.moduleList = moduleList;
	}

	public List getSystemList() {
		return systemList;
	}

	public void setSystemList(List systemList) {
		this.systemList = systemList;
	}

}
