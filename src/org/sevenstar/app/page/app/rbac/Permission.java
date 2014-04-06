package org.sevenstar.app.page.app.rbac;

import java.util.List;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.rbac.domain.S_rbac_operationDomain;
import org.sevenstar.app.rbac.domain.S_rbac_permissionDomain;
import org.sevenstar.app.rbac.domain.S_rbac_resourceDomain;
import org.sevenstar.app.system.domain.S_systemDomain;
import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.web.action.java.DefaultAction;
/**
 * @author rtm 2008-5-8
 */
public class Permission extends DefaultAction {
	private IbatisPage page;

	private S_rbac_permissionDomain s_rbac_permissionDomain;

	 
	private List resourceList;

	private List operationList;
	
	private List systemList;

	public String list() {
		if (s_rbac_permissionDomain == null) {
			s_rbac_permissionDomain = new S_rbac_permissionDomain();
		}
		page = s_rbac_permissionDomain.getSelectPage(page);
		return super.SUCCESS;
	}

	public String input() {
		resourceList = (new S_rbac_resourceDomain()).selectAll();
		operationList = (new S_rbac_operationDomain()).selectAll();
		systemList = (new S_systemDomain()).selectAll();
		return "app/rbac/permission/edit.html";
	}

	public String edit(){
		if(s_rbac_permissionDomain == null){
			throw new ApplicationException("没有选中权限");
		}
		resourceList =  (new S_rbac_resourceDomain()).selectAll();
		operationList = (new S_rbac_operationDomain()).selectAll();
		s_rbac_permissionDomain.load();
		systemList = (new S_systemDomain()).selectAll();
		return super.SUCCESS;
	}

	public String view(){
		if(s_rbac_permissionDomain == null){
			throw new ApplicationException("没有选中权限");
		}
		s_rbac_permissionDomain.load();
		return super.SUCCESS;
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}

	public S_rbac_permissionDomain getS_rbac_permissionDomain() {
		return s_rbac_permissionDomain;
	}

	public void setS_rbac_permissionDomain(S_rbac_permissionDomain domain) {
		s_rbac_permissionDomain = domain;
	}

	public List getResourceList() {
		return resourceList;
	}

	public void setResourceList(List resourceList) {
		this.resourceList = resourceList;
	}

	public List getOperationList() {
		return operationList;
	}

	public void setOperationList(List operationList) {
		this.operationList = operationList;
	}

	public List getSystemList() {
		return systemList;
	}

	public void setSystemList(List systemList) {
		this.systemList = systemList;
	}

}
