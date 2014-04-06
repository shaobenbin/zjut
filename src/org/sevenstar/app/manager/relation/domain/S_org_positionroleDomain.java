package org.sevenstar.app.manager.relation.domain;

import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;
import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSManyToOne;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;

 
@SSDomain(table="s_org_positionrole")
@SSId(generateType="seq",seq="seq_app")
public class S_org_positionroleDomain extends PersistentObject {

	@SSPrimaryKey
	private java.lang.Long id;
	 
	@SSManyToOne(parameter="roleDomain.id",column="role_id")
	private org.sevenstar.app.rbac.domain.S_rbac_roleDomain roleDomain;
 
	@SSManyToOne(parameter="positionDomain.id",column="position_id")
	private org.sevenstar.app.manager.domain.S_org_positionDomain positionDomain;

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

	public void setPositionDomain(
			org.sevenstar.app.manager.domain.S_org_positionDomain positionDomain) {
		this.positionDomain = positionDomain;
	}

	public org.sevenstar.app.manager.domain.S_org_positionDomain getPositionDomain() {
		return this.positionDomain;
	}
}
