package org.sevenstar.app.rbac.action;

import java.util.List;

import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.util.BeanHelper;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;
import org.sevenstar.web.annotation.SSList;
import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.module.domain.S_moduleDomain;
import org.sevenstar.app.rbac.domain.S_rbac_permissionDomain;
import org.sevenstar.app.rbac.domain.S_rbac_roleDomain;
import org.sevenstar.app.rbac.relation.domain.S_rbac_rolemoduleDomain;
import org.sevenstar.app.rbac.relation.domain.S_rbac_rolepermissionDomain;
 
@SSAction(name = "s_rbac_role")
public class S_rbac_roleAction extends DefaultAction {
	private S_rbac_roleDomain s_rbac_roleDomain;
 	@SSList(type="org.sevenstar.app.rbac.domain.S_rbac_roleDomain")
	private List roleList;
	@SSList(type="org.sevenstar.app.rbac.domain.S_rbac_permissionDomain")
	private List permissionList;
	@SSList(type="org.sevenstar.app.module.domain.S_moduleDomain")
	private List moduleList;
	
	private IbatisPage page;
	public String deleteList() throws Exception {
		if (roleList != null && roleList.size() > 0) {
			for (int i = 0; i < roleList.size(); i++) {
				S_rbac_roleDomain s_rbac_roleDomain = (S_rbac_roleDomain) roleList
						.get(i);
				s_rbac_roleDomain.delete();
			}
		} else {
			throw new ApplicationException("请先选择角色");
		}
		return "批量删除角色成功";
	}
	

	public IbatisPage query() {
		if (s_rbac_roleDomain == null) {
			s_rbac_roleDomain = new S_rbac_roleDomain();
		}
		page = s_rbac_roleDomain.getSelectPage(page);
		List dataList = page.getDataList();
		if (dataList != null && dataList.size() > 0) {
			for (int i = 0; i < dataList.size(); i++) {
				S_rbac_roleDomain roleDomain = (S_rbac_roleDomain) dataList.get(i);
				S_rbac_roleDomain newRoleDomain = new S_rbac_roleDomain();
				BeanHelper.copyNotNullPrimitiveProperties(roleDomain,
						newRoleDomain);
				dataList.set(i, newRoleDomain);
			}
		}
		return page;
	}


	public IbatisPage getPage() {
		return page;
	}


	public void setPage(IbatisPage page) {
		this.page = page;
	}


	public String delete() throws Exception {
		super.setMsg("删除角色[" + s_rbac_roleDomain.getName() + "]成功");
		s_rbac_roleDomain.delete();
		super.setNextUrl("app/rbac/role/list.html");
		return super.REDIRECT;
	}

	public String insert() throws Exception {
		s_rbac_roleDomain.insert();
		if(permissionList != null && permissionList.size() > 0){
			for(int i=0;i<permissionList.size();i++){
				S_rbac_rolepermissionDomain s_rbac_rolepermissionDomain = new S_rbac_rolepermissionDomain();
				s_rbac_rolepermissionDomain.setRoleDomain(s_rbac_roleDomain);
				s_rbac_rolepermissionDomain.setPermissionDomain((S_rbac_permissionDomain)permissionList.get(i));
				s_rbac_rolepermissionDomain.insert();
			}
		}
		if(moduleList != null && moduleList.size() > 0){
			for(int i=0;i<moduleList.size();i++){
				S_rbac_rolemoduleDomain s_rbac_rolemoduleDomain = new S_rbac_rolemoduleDomain();
				s_rbac_rolemoduleDomain.setRoleDomain(s_rbac_roleDomain);
				s_rbac_rolemoduleDomain.setModuleDomain((S_moduleDomain)moduleList.get(i));
				s_rbac_rolemoduleDomain.insert();
			}
		}
		super.setMsg("添加角色[" + s_rbac_roleDomain.getName() + "]成功");
		super.setNextUrl("app/rbac/role/list.html");
		return super.REDIRECT;
	}

	public String update() throws Exception {
		s_rbac_roleDomain.update();
		doUpdatePermission();
		super.setMsg("修改角色[" + s_rbac_roleDomain.getName() + "]成功");
		super.setNextUrl("app/rbac/role/list.html");
		return super.REDIRECT;
	}

	private void doUpdatePermission() {
		S_rbac_rolepermissionDomain paramDomain = new S_rbac_rolepermissionDomain();
		paramDomain.setRoleDomain(s_rbac_roleDomain);
		List rolepermissionList = paramDomain.selectEqual();
		for(int i=0;i<rolepermissionList.size();i++){
			S_rbac_rolepermissionDomain s_rbac_rolepermissionDomain = (S_rbac_rolepermissionDomain)rolepermissionList.get(i);
			s_rbac_rolepermissionDomain.delete();
		}
		S_rbac_rolemoduleDomain paramModuleDomain = new S_rbac_rolemoduleDomain();
		paramModuleDomain.setRoleDomain(s_rbac_roleDomain);
		List roleModuleList = paramModuleDomain.selectEqual();
		for(int i=0;i<roleModuleList.size();i++){
			S_rbac_rolemoduleDomain rolemoduleDomain = (S_rbac_rolemoduleDomain)roleModuleList.get(i);
			rolemoduleDomain.delete();
		}
		if(permissionList != null && permissionList.size() > 0){
			for(int i=0;i<permissionList.size();i++){
				S_rbac_rolepermissionDomain s_rbac_rolepermissionDomain = new S_rbac_rolepermissionDomain();
				s_rbac_rolepermissionDomain.setRoleDomain(s_rbac_roleDomain);
				s_rbac_rolepermissionDomain.setPermissionDomain((S_rbac_permissionDomain)permissionList.get(i));
				s_rbac_rolepermissionDomain.insert();
			}
		}

		if(moduleList != null && moduleList.size() > 0){
			for(int i=0;i<moduleList.size();i++){
				S_rbac_rolemoduleDomain s_rbac_rolemoduleDomain = new S_rbac_rolemoduleDomain();
				s_rbac_rolemoduleDomain.setRoleDomain(s_rbac_roleDomain);
				s_rbac_rolemoduleDomain.setModuleDomain((S_moduleDomain)moduleList.get(i));
				s_rbac_rolemoduleDomain.insert();
			}
		}
	}

	public String insertOrUpdate() throws Exception {
		s_rbac_roleDomain.insertOrUpdate();
		doUpdatePermission();
		return "编辑角色[" + s_rbac_roleDomain.getName() + "]成功";
	}
	public S_rbac_roleDomain getS_rbac_roleDomain() {
		return s_rbac_roleDomain;
	}

	public void setS_rbac_roleDomain(S_rbac_roleDomain s_rbac_roleDomain) {
		this.s_rbac_roleDomain = s_rbac_roleDomain;
	}

 

	public List getRoleList() {
		return roleList;
	}

	public void setRoleList(List roleList) {
		this.roleList = roleList;
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


}
