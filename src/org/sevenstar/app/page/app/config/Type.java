package org.sevenstar.app.page.app.config;

 
import java.util.List;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.config.domain.S_configure_typeDomain;
import org.sevenstar.web.action.java.DefaultAction;

public class Type extends DefaultAction {
	 

	private S_configure_typeDomain s_configure_typeDomain;

 
	public String input(){
		return "app/config/type/edit.html";
	}
	
	public String edit(){
		if(s_configure_typeDomain == null || s_configure_typeDomain.getId() == null){
			throw new ApplicationException("请先选择一项[配置类型]修改");
		}
		s_configure_typeDomain.load();
		return super.SUCCESS;
	}
	
	private List configTypeList;
	
	public String tree(){
		configTypeList = (new S_configure_typeDomain()).selectAll();
		return super.SUCCESS;
	}
	
	public String left(){
		if(s_configure_typeDomain == null){
			return welcome();
		}
		s_configure_typeDomain.load();
		return super.SUCCESS;
	}
	
	public String welcome(){
		return "app/config/type/welcome.html";
	}

 

	public S_configure_typeDomain getS_configure_typeDomain() {
		return s_configure_typeDomain;
	}

	public void setS_configure_typeDomain(S_configure_typeDomain domain) {
		s_configure_typeDomain = domain;
	}

	public List getConfigTypeList() {
		return configTypeList;
	}

	public void setConfigTypeList(List configTypeList) {
		this.configTypeList = configTypeList;
	}

	 
}
