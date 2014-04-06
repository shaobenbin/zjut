package org.sevenstar.app.system.action;

import java.util.List;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.system.domain.S_systemDomain;
 import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;
import org.sevenstar.web.annotation.SSList;

@SSAction(name = "s_system")
public class S_systemAction extends DefaultAction {
	private S_systemDomain s_systemDomain;
	 
	@SSList(type = "org.sevenstar.app.system.domain.S_systemDomain")
	private List systemList;

	public String deleteList() throws Exception {
		if (systemList != null && systemList.size() > 0) {
			for (int i = 0; i < systemList.size(); i++) {
				S_systemDomain s_systemDomain = (S_systemDomain) systemList
						.get(i);
				s_systemDomain.delete();
			}
		} else {
			throw new ApplicationException("请先选择系统");
		}
		return "批量删除系统成功";
	}

	public String delete() throws Exception {
		super.setMsg("删除系统[" + s_systemDomain.getName() + "]成功");
		s_systemDomain.delete();
		super.setNextUrl("app/system/list.html");
		return super.REDIRECT;
	}

	public String insert() throws Exception {
		s_systemDomain.insert();
		super.setMsg("添加系统[" + s_systemDomain.getName() + "]成功");
		super.setNextUrl("app/system/list.html");
		return super.REDIRECT;
	}

	public String update() throws Exception {
		s_systemDomain.update();
		super.setMsg("修改系统[" + s_systemDomain.getName() + "]成功");
		super.setNextUrl("app/system/list.html");
		return super.REDIRECT;
	}

	public String insertOrUpdate() throws Exception {
		s_systemDomain.insertOrUpdate();
		return "编辑系统[" + s_systemDomain.getName() + "]成功";
	}
	public S_systemDomain getS_systemDomain() {
		return s_systemDomain;
	}
	public void setS_systemDomain(S_systemDomain s_systemDomain) {
		this.s_systemDomain = s_systemDomain;
	}

	public List getSystemList() {
		return systemList;
	}

	public void setSystemList(List systemList) {
		this.systemList = systemList;
	}

 
 


}
