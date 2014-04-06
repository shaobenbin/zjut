package org.sevenstar.app.rbac.domain;

import java.util.List;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.system.domain.S_systemDomain;
import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSCacheModel;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;
import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSManyToOne;
import org.sevenstar.persistent.db.find.annotation.model.SSOneToMany;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;

@SSDomain(table="s_rbac_role",condition="sts<>'N'")
@SSId(generateType="seq",seq="seq_app")
@SSCacheModel(selectAll=true,flushonexecute="S_moduleDomain_base_insert,S_moduleDomain_base_update,S_moduleDomain_base_delete,S_rbac_roleDomain_base_insert,S_rbac_roleDomain_base_update,S_rbac_roleDomain_base_delete,S_rbac_permissionDomain_base_insert,S_rbac_permissionDomain_base_update,S_rbac_permissionDomain_base_delete,S_rbac_operationDomain_base_insert,S_rbac_operationDomain_base_update,S_rbac_operationDomain_base_delete,S_rbac_resourceDomain_base_insert,S_rbac_resourceDomain_base_update,S_rbac_resourceDomain_base_delete")
public class S_rbac_roleDomain extends PersistentObject {
	 
	@SSPrimaryKey
	private Long id;
 
	private String name;
	 
	private String description;
	
	@SSManyToOne(parameter = "systemDomain.id", column = "system_id")
	private S_systemDomain systemDomain;
	 
	@SSOneToMany(select="S_rbac_roleDomain_exp_select_permission_by_roleid")
	private List permissionList;
	 
	@SSOneToMany(select="S_rbac_roleDomain_exp_select_module_by_roleid")
	private List moduleList;
	
	private String sts;

	public S_systemDomain getSystemDomain() {
		return systemDomain;
	}

	public void setSystemDomain(S_systemDomain systemDomain) {
		this.systemDomain = systemDomain;
	}

	public void update( ){
		this.setSts("Y");
		S_rbac_roleDomain paramDomain = new S_rbac_roleDomain();
		paramDomain.setName(this.getName());
 		if(paramDomain.updateExist(this.getId())){
			throw new ApplicationException("角色名[" + this.getName() + "]已存在");
		}
 		super.update();
	}

	public void insert( ) {
		this.setSts("Y");
		if (hasRole(this.getName())) {
			throw new ApplicationException("角色名[" + this.getName() + "]已存在");
		}
		super.insert();
	}
	
	public void delete( ) {
		this.setSts("N");
		super.update();
	}


	public void insertOrUpdate( ) {
		if(this.getId() != null){
			this.update();
		}else{
			this.insert();
		}
	}

	
	public boolean hasRole(String name) {
		S_rbac_roleDomain param = new S_rbac_roleDomain();
		param.setName(name);
		return param.exist();
	}
	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.Long getId() {
		return this.id;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.String getDescription() {
		return this.description;
	}

	public void setSts(java.lang.String sts) {
		this.sts = sts;
	}

	public java.lang.String getSts() {
		return this.sts;
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
