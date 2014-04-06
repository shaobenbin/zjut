package org.sevenstar.app.rbac.relation.domain;

import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;
import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSManyToOne;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;

 
@SSDomain(table="s_rbac_rolemodule")
@SSId(generateType="seq",seq="seq_app")
public class S_rbac_rolemoduleDomain extends PersistentObject {

	@SSPrimaryKey
	private java.lang.Long id;
	 
	@SSManyToOne(parameter="roleDomain.id",column="role_id")
	private org.sevenstar.app.rbac.domain.S_rbac_roleDomain roleDomain;
 
	@SSManyToOne(parameter="moduleDomain.id",column="module_id")
	private org.sevenstar.app.module.domain.S_moduleDomain moduleDomain;

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.Long getId() {
		return this.id;
	}

	public void setRoleDomain(
			org.sevenstar.app.rbac.domain.S_rbac_roleDomain roleDomain) {
		this.roleDomain = roleDomain;
	}

	public org.sevenstar.app.rbac.domain.S_rbac_roleDomain getRoleDomain() {
		return this.roleDomain;
	}

	public void setModuleDomain(
			org.sevenstar.app.module.domain.S_moduleDomain moduleDomain) {
		this.moduleDomain = moduleDomain;
	}

	public org.sevenstar.app.module.domain.S_moduleDomain getModuleDomain() {
		return this.moduleDomain;
	}
}
