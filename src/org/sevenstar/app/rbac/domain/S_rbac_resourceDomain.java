package org.sevenstar.app.rbac.domain;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.system.domain.S_systemDomain;
import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSCacheModel;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;
import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSManyToOne;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;

 
@SSDomain(table="s_rbac_resource",condition="sts<>'N'")
@SSId(generateType="seq",seq="seq_app")
@SSCacheModel(selectAll=true)
public class S_rbac_resourceDomain extends PersistentObject {
	@SSPrimaryKey
	private java.lang.Long id;
	 
	private java.lang.String name;
	 
	private java.lang.String description;
	
	@SSManyToOne(parameter = "systemDomain.id", column = "system_id")
	private S_systemDomain systemDomain;
	 
	private java.lang.String sts;
	
	
	public S_systemDomain getSystemDomain() {
		return systemDomain;
	}

	public void setSystemDomain(S_systemDomain systemDomain) {
		this.systemDomain = systemDomain;
	}

	public void update( ){
		this.setSts("Y");
		S_rbac_resourceDomain paramDomain = new S_rbac_resourceDomain();
		paramDomain.setName(this.getName());
 		if(paramDomain.updateExist(this.getId())){
			throw new ApplicationException("资源名[" + this.getName() + "]已存在");
		}
 		super.update();
	}

	public void insert( ) {
		this.setSts("Y");
		if (hasResource(this.getName())) {
			throw new ApplicationException("资源名[" + this.getName() + "]已存在");
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

	public boolean hasResource(String name) {
		S_rbac_resourceDomain param = new S_rbac_resourceDomain();
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
}
