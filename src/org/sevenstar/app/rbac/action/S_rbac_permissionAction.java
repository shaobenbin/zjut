package org.sevenstar.app.rbac.action;

import java.util.List;

import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;
import org.sevenstar.web.annotation.SSList;
import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.rbac.domain.S_rbac_permissionDomain;
 
@SSAction(name = "s_rbac_permission")
public class S_rbac_permissionAction extends DefaultAction {
	private S_rbac_permissionDomain s_rbac_permissionDomain;
     @SSList(type="org.sevenstar.app.rbac.domain.S_rbac_permissionDomain")
	private List permissionList;

	public String deleteList() throws Exception {
		if (permissionList != null && permissionList.size() > 0) {
			for (int i = 0; i < permissionList.size(); i++) {
				S_rbac_permissionDomain s_rbac_permissionDomain = (S_rbac_permissionDomain) permissionList
						.get(i);
				s_rbac_permissionDomain.delete();
			}
		} else {
			throw new ApplicationException("请先选择权限");
		}
		return "批量删除权限成功";
	}

	public String delete() throws Exception {
		super.setMsg("删除权限[" + s_rbac_permissionDomain.getName() + "]成功");
		s_rbac_permissionDomain.delete();
		super.setNextUrl("app/rbac/permission/list.html");
		return super.REDIRECT;
	}

	public String insert() throws Exception {
		s_rbac_permissionDomain.insert();
		super.setMsg("添加权限[" + s_rbac_permissionDomain.getName() + "]成功");
		super.setNextUrl("app/rbac/permission/list.html");
		return super.REDIRECT;
	}

	public String update() throws Exception {
		s_rbac_permissionDomain.update();
		super.setMsg("修改权限[" + s_rbac_permissionDomain.getName() + "]成功");
		super.setNextUrl("app/rbac/permission/list.html");
		return super.REDIRECT;
	}

	public String insertOrUpdate() throws Exception {
		s_rbac_permissionDomain.insertOrUpdate();
		return "编辑权限[" + s_rbac_permissionDomain.getName() + "]成功";
	}


	public S_rbac_permissionDomain getS_rbac_permissionDomain() {
		return s_rbac_permissionDomain;
	}

	public void setS_rbac_permissionDomain(
			S_rbac_permissionDomain s_rbac_permissionDomain) {
		this.s_rbac_permissionDomain = s_rbac_permissionDomain;
	}

 

	public List getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List permissionList) {
		this.permissionList = permissionList;
	}


}
