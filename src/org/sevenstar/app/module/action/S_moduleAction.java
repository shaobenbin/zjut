package org.sevenstar.app.module.action;

import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;
import org.sevenstar.app.module.domain.S_moduleDomain;
import org.sevenstar.app.module.service.S_moduleService;

@SSAction(name = "s_module")
public class S_moduleAction extends DefaultAction {
	private S_moduleDomain s_moduleDomain;
	private S_moduleService s_moduleService = new S_moduleService();
 
 
	public String delete() throws Exception {
		s_moduleDomain.load();
		String message = "删除模块[" + s_moduleDomain.getName() + "]成功";
		s_moduleDomain.delete();
		return message;
	}

	public String insert() throws Exception {
		s_moduleDomain.insert();
		super.setMsg("添加模块[" + s_moduleDomain.getName() + "]成功");
		if (s_moduleDomain.getUpDomain() != null
				&& s_moduleDomain.getUpDomain().getId() != null) {
			super.setNextUrl("app/module/list.html?s_moduleDomain.id="
					+ s_moduleDomain.getUpDomain().getId());
		} else {
			super.setNextUrl("app/module/list.html?s_moduleDomain.id="
					+ s_moduleDomain.getId());
		}
		return super.REDIRECT;
	}

	public String update() throws Exception {
		s_moduleDomain.update();
		super.setMsg("修改模块[" + s_moduleDomain.getName() + "]成功");
		super.setNextUrl("app/module/view.html?s_moduleDomain.id="
				+ s_moduleDomain.getId());
		return super.REDIRECT;
	}

 

	public String insertOrUpdate() throws Exception {
		s_moduleDomain.insertOrUpdate();
		return "编辑模块[" + s_moduleDomain.getName() + "]成功" ;
	}

	public S_moduleDomain getS_moduleDomain() {
		return s_moduleDomain;
	}

	public void setS_moduleDomain(S_moduleDomain s_moduleDomain) {
		this.s_moduleDomain = s_moduleDomain;
	}

	public S_moduleService getS_moduleService() {
		return s_moduleService;
	}

	public void setS_moduleService(S_moduleService s_moduleService) {
		this.s_moduleService = s_moduleService;
	}

 
}
