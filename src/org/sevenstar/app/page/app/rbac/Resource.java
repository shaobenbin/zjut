package org.sevenstar.app.page.app.rbac;

import java.util.List;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.rbac.domain.S_rbac_resourceDomain;
import org.sevenstar.app.system.domain.S_systemDomain;
import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.web.action.java.DefaultAction;
/**
 * @author rtm 2008-5-8
 */
public class Resource extends DefaultAction{
	private S_rbac_resourceDomain s_rbac_resourceDomain;

	private IbatisPage page;
	
	private List systemList;

	public String list() {
		if(s_rbac_resourceDomain == null){
			s_rbac_resourceDomain = new S_rbac_resourceDomain();
		}
		page = s_rbac_resourceDomain.getSelectPage(page);
		return super.SUCCESS;
	}

	public String edit(){
		if(s_rbac_resourceDomain == null){
			throw new ApplicationException("没有选中资源");
		}
		s_rbac_resourceDomain.load();
		systemList = (new S_systemDomain()).selectAll();
		return super.SUCCESS;
	}

	public String view(){
		if(s_rbac_resourceDomain == null){
			throw new ApplicationException("没有选中资源");
		}
		s_rbac_resourceDomain.load();
		return super.SUCCESS;
	}

	public String input(){
		systemList = (new S_systemDomain()).selectAll();
		return "app/rbac/resource/edit.html";
	}

	public S_rbac_resourceDomain getS_rbac_resourceDomain() {
		return s_rbac_resourceDomain;
	}

	public void setS_rbac_resourceDomain(S_rbac_resourceDomain domain) {
		s_rbac_resourceDomain = domain;
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
