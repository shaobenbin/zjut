package org.sevenstar.app.rbac.relation.domain;

import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;
import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSManyToOne;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;

 
@SSDomain(table="s_rbac_grouprole")
@SSId(generateType="seq",seq="seq_app")
public class S_rbac_grouproleDomain extends PersistentObject {

	@SSPrimaryKey
	private java.lang.Long id;
	 
	@SSManyToOne(parameter="groupDomain.id",column="group_id")
	private org.sevenstar.app.rbac.domain.S_rbac_groupDomain groupDomain;
 
	@SSManyToOne(parameter="roleDomain.id",column="role_id")
	private org.sevenstar.app.rbac.domain.S_rbac_roleDomain roleDomain;

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.Long getId() {
		return this.id;
	}

	public void setGroupDomain(
			org.sevenstar.app.rbac.domain.S_rbac_groupDomain groupDomain) {
		this.groupDomain = groupDomain;
	}

	public org.sevenstar.app.rbac.domain.S_rbac_groupDomain getGroupDomain() {
		return this.groupDomain;
	}

	public void setRoleDomain(
			org.sevenstar.app.rbac.domain.S_rbac_roleDomain roleDomain) {
		this.roleDomain = roleDomain;
	}

	public org.sevenstar.app.rbac.domain.S_rbac_roleDomain getRoleDomain() {
		return this.roleDomain;
	}
}
