package org.sevenstar.app.resource.action;

import java.util.List;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.resource.domain.S_resourceDomain;
import org.sevenstar.persistent.db.transaction.SSTransaction;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;
import org.sevenstar.web.annotation.SSList;

@SSAction(name = "s_resource")
public class S_resourceAction extends DefaultAction {
	private S_resourceDomain s_resourceDomain;
	
	@SSList(type="org.sevenstar.app.resource.domain.S_resourceDomain")
	private List resourceList;

	@SSTransaction
	public String insertOrUpdate() {
		if(s_resourceDomain == null ){
			throw new ApplicationException("请录入资源信息");
		}
		s_resourceDomain.insertOrUpdate();
        return "编辑资源["+s_resourceDomain.getName()+"]成功";
	}
	
	@SSTransaction
	public String deleteList(){
		if(resourceList == null || resourceList.size() == 0){
			throw new ApplicationException("请选择一项资源");
		}
		for(int i=0;i<resourceList.size();i++){
			S_resourceDomain domain = (S_resourceDomain)resourceList.get(i);
			domain.delete();
		}
		return "批量删除成功";
	}

	public S_resourceDomain getS_resourceDomain() {
		return s_resourceDomain;
	}

	public void setS_resourceDomain(S_resourceDomain domain) {
		s_resourceDomain = domain;
	}

	public List getResourceList() {
		return resourceList;
	}

	public void setResourceList(List resourceList) {
		this.resourceList = resourceList;
	}
	
	

}
