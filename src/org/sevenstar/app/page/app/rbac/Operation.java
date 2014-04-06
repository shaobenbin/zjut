package org.sevenstar.app.page.app.rbac;

import java.util.List;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.rbac.domain.S_rbac_operationDomain;
import org.sevenstar.app.system.domain.S_systemDomain;
import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.web.action.java.DefaultAction;
/**
 * @author rtm 2008-5-8
 */
public class Operation extends DefaultAction {

	private S_rbac_operationDomain s_rbac_operationDomain;

	private IbatisPage page;
	
	private List systemList;

	public String list() {
		if(s_rbac_operationDomain == null){
			s_rbac_operationDomain = new S_rbac_operationDomain();
		}
		page = s_rbac_operationDomain.getSelectPage(page);
		return super.SUCCESS;
	}

	public String edit(){
		if(s_rbac_operationDomain == null){
			throw new ApplicationException("没有选中操作");
		}
		s_rbac_operationDomain.load();
		systemList = (new S_systemDomain()).selectAll();
		return super.SUCCESS;
	}

	public String view(){
		if(s_rbac_operationDomain == null){
			throw new ApplicationException("没有选中操作");
		}
		s_rbac_operationDomain.load();
		return super.SUCCESS;
	}

	public String input(){
		systemList = (new S_systemDomain()).selectAll();
		return "app/rbac/operation/edit.html";
	}

	public S_rbac_operationDomain getS_rbac_operationDomain() {
		return s_rbac_operationDomain;
	}

	public void setS_rbac_operationDomain(S_rbac_operationDomain domain) {
		s_rbac_operationDomain = domain;
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}

	public List getSystemList() {
		return systemList;
	}

	public void setSystemList(List systemList) {
		this.systemList = systemList;
	}


}
