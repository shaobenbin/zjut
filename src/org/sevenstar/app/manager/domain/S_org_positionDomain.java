package org.sevenstar.app.manager.domain;

import java.util.List;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSCacheModel;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;
import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSManyToOne;
import org.sevenstar.persistent.db.find.annotation.model.SSOneToMany;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;


@SSDomain(table="s_org_position",condition="sts<>'N'")
@SSId(generateType="seq",seq="seq_app")
@SSCacheModel(selectAll=true,flushonexecute="S_moduleDomain_base_insert,S_moduleDomain_base_update,S_moduleDomain_base_delete,S_org_positionDomain_base_insert,S_org_positionDomain_base_update,S_org_positionDomain_base_delete")
public class S_org_positionDomain extends PersistentObject {
	@SSPrimaryKey
	private java.lang.Long id;
	 
	private java.lang.String name;
	 
	@SSManyToOne(parameter="orgDomain.id",column="org_id")
	private S_orgDomain orgDomain;
	 
	private java.lang.String description;
	 
	private java.lang.String sts;

	 
	@SSOneToMany(select="S_userDomain_exp_select_user_by_positionid")
	private List userList;

 
	@SSOneToMany(select="S_roleDomain_exp_select_by_positionid")
	private List roleList;
	
	public void update( ){
		this.setSts("Y");
		S_org_positionDomain paramDomain = new S_org_positionDomain();
		paramDomain.setName(this.getName());
		List queryDomainList = paramDomain.selectEqual();
		for(int i=0;i<queryDomainList.size();i++){
			S_org_positionDomain queryDomain = (S_org_positionDomain)queryDomainList.get(i);
			if(!this.getId().equals(queryDomain.getId()) && queryDomain.getOrgDomain().getId().equals(this.getOrgDomain().getId())){
				throw new ApplicationException("职位名[" + this.getName() + "]在部门["+this.getOrgDomain().getName()+"]已存在");
			}
		}
		super.update();
	}

	public void insert( ) {
		this.setSts("Y");
		S_org_positionDomain paramDomain = new S_org_positionDomain();
		paramDomain.setName(this.getName());
		List queryDomainList = paramDomain.selectEqual();
		for(int i=0;i<queryDomainList.size();i++){
			S_org_positionDomain queryDomain = (S_org_positionDomain)queryDomainList.get(i);
			if(queryDomain.getOrgDomain().getId().equals(this.getOrgDomain().getId())){
				throw new ApplicationException("职位名[" + this.getName() + "]在部门["+this.getOrgDomain().getName()+"]已存在");
			}
		}
		super.insert();
	}

	public void insertOrUpdate( ) {
		if(this.getId() != null){
			this.update();
		}else{
			this.insert();
		}
	}

	public void delete( ) {
		this.setSts("N");
		super.update();
	}

	public List getRoleList() {
		return roleList;
	}

	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}

	public List getUserList() {
		return userList;
	}

	public void setUserList(List userList) {
		this.userList = userList;
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

	public void setOrgDomain(
			org.sevenstar.app.manager.domain.S_orgDomain orgDomain) {
		this.orgDomain = orgDomain;
	}

	public org.sevenstar.app.manager.domain.S_orgDomain getOrgDomain() {
		return this.orgDomain;
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
}
