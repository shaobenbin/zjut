package org.sevenstar.app.rbac.relation.domain;

import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;
import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSManyToOne;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;

 
@SSDomain(table="s_rbac_rolepermission")
@SSId(generateType="seq",seq="seq_app")
public class S_rbac_rolepermissionDomain extends PersistentObject {

	@SSPrimaryKey
	private java.lang.Long id;
	 
	@SSManyToOne(parameter="roleDomain.id",column="role_id")
	private org.sevenstar.app.rbac.domain.S_rbac_roleDomain roleDomain;
	 
	@SSManyToOne(parameter="permissionDomain.id",column="permission_id")
	private org.sevenstar.app.rbac.domain.S_rbac_permissionDomain permissionDomain;

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

	public void setPermissionDomain(
			org.sevenstar.app.rbac.domain.S_rbac_permissionDomain permissionDomain) {
		this.permissionDomain = permissionDomain;
	}

	public org.sevenstar.app.rbac.domain.S_rbac_permissionDomain getPermissionDomain() {
		return this.permissionDomain;
	}
}
