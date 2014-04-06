package org.sevenstar.app.page;

import java.util.List;

import org.sevenstar.app.common.context.ApplicationContext;
import org.sevenstar.app.module.domain.S_moduleDomain;
import org.sevenstar.app.module.service.S_moduleService;
import org.sevenstar.app.utils.Constant;
import org.sevenstar.util.CookieHelper;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.context.WebContext;

public class Root extends DefaultAction {
	private String loginname;
	
	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String login() {
		loginname = CookieHelper.getAttribute(Constant.HCOA_LOGINNAME, WebContext.getRequest());
		return SUCCESS;
	}

	public String main_frame() {
		return SUCCESS;
	}

	private List menuList;

	private S_moduleDomain domain;

	private List moduleList;

	public S_moduleDomain getDomain() {
		return domain;
	}

	public void setDomain(S_moduleDomain domain) {
		this.domain = domain;
	}

	public String leftmenu() {
		S_moduleService moduleService = new S_moduleService();
		menuList = moduleService.selectFirstMenu("后台管理系统");
		for(int i=0;i<menuList.size();i++){
			S_moduleDomain moduleDomain = (S_moduleDomain)menuList.get(i);
			if(!ApplicationContext.get().hasPermission(moduleDomain)){
				menuList.remove(i);
				i--;
			}else{
				addModule(menuList,moduleDomain);
			}
		}
		return SUCCESS;
	}
	
	private void addModule(List menuList,S_moduleDomain moduleDomain){
		List childList = moduleDomain.getChildList();
		 for(int i=0;i<childList.size();i++){
			 S_moduleDomain child = (S_moduleDomain)childList.get(i);
			 if(ApplicationContext.get().hasPermission(child)){
				 menuList.add(child);
				 addModule( menuList, child);
			 }
		 }
	}

 

	public List getMenuList() {
		return menuList;
	}

	public void setMenuList(List menuList) {
		this.menuList = menuList;
	}

}
