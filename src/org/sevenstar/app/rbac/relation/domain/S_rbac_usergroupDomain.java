package org.sevenstar.app.rbac.relation.domain;

import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;
import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSManyToOne;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;

 
@SSDomain(table="s_rbac_usergroup")
@SSId(generateType="seq",seq="seq_app")
public class S_rbac_usergroupDomain extends PersistentObject {

	@SSPrimaryKey
	private java.lang.Long id;
	 
	@SSManyToOne(parameter="userDomain.id",column="user_id")
	private org.sevenstar.app.manager.domain.S_userDomain userDomain;
	 
	@SSManyToOne(parameter="groupDomain.id",column="group_id")
	private org.sevenstar.app.rbac.domain.S_rbac_groupDomain groupDomain;

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.Long getId() {
		return this.id;
	}

	public void setUserDomain(
			org.sevenstar.app.manager.domain.S_userDomain userDomain) {
		this.userDomain = userDomain;
	}

	public org.sevenstar.app.manager.domain.S_userDomain getUserDomain() {
		return this.userDomain;
	}

	public void setGroupDomain(
			org.sevenstar.app.rbac.domain.S_rbac_groupDomain groupDomain) {
		this.groupDomain = groupDomain;
	}

	public org.sevenstar.app.rbac.domain.S_rbac_groupDomain getGroupDomain() {
		return this.groupDomain;
	}
}
