package org.sevenstar.app.resource.domain;

import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;

import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSOneToMany;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;
import org.sevenstar.persistent.db.find.annotation.model.SSManyToOne;
import org.sevenstar.persistent.db.find.annotation.model.SSProperty;
import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.manager.domain.S_userDomain;
import org.sevenstar.app.resource.domain.S_resource_typeDomain;

import java.util.Date;
import java.util.List;

@SSDomain(table = "s_resource_type",condition="sts<>'N'")
@SSId(generateType = "seq", seq = "seq_app")
public class S_resource_typeDomain extends PersistentObject {

	@SSPrimaryKey
	private Long id;
	
	private String name;
	
	private String code;
	
	@SSManyToOne(column = "up_id", parameter = "upDomain.id")
	private S_resource_typeDomain upDomain;
	private String description;
	
	@SSManyToOne(column = "create_user_id", parameter = "createUserDomain.id")
	private S_userDomain createUserDomain;
	
	@SSProperty(value = "sysdate")
	private Date create_date;
	
	@SSManyToOne(column = "modify_user_id", parameter = "modifyUserDomain.id")
	private S_userDomain modifyUserDomain;
	
	@SSProperty(value = "sysdate", update = false)
	private Date modify_date;
	
	private Long lvl;
	
	@SSOneToMany(className = "org.sevenstar.app.resource.domain.S_resource_typeDomain", column = "up_id")
	private List childList;
	
	@SSOneToMany(className = "org.sevenstar.app.resource.domain.S_resourceDomain", column = "type_id")
	private List resourceList;

	private String sts;

	public void update() {
		this.setSts("Y");
		S_resource_typeDomain paramDomain = new S_resource_typeDomain();
		paramDomain.setName(this.getName());
		paramDomain.setUpDomain(upDomain);
		if (paramDomain.updateExist(this.getId())) {
			throw new ApplicationException("配置[" + this.getName() + "]已存在");
		}
		super.update();
	}

	public void insert() {
		this.setSts("Y");
		S_resource_typeDomain paramDomain = new S_resource_typeDomain();
		paramDomain.setName(this.getName());
		paramDomain.setUpDomain(upDomain);
		if (paramDomain.exist()) {
			throw new ApplicationException("配置[" + this.getName() + "]已存在");
		}
		if(this.getUpDomain() != null && this.getUpDomain().getLvl() != null){
			this.setLvl(new Long(this.getUpDomain().getLvl().longValue() + 1));
		}else{
			this.setLvl(new Long(0));
		}
		super.insert();
	}

	public void insertOrUpdate() {
		if (this.getId() != null) {
			this.update();
		} else {
			this.insert();
		}
	}

	public void delete() {
		this.setSts("N");
		super.update();
	}
	
	

	public List getChildList() {
		return childList;
	}

	public void setChildList(List childList) {
		this.childList = childList;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public void setUpDomain(S_resource_typeDomain upDomain) {
		this.upDomain = upDomain;
	}

	public S_resource_typeDomain getUpDomain() {
		return this.upDomain;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setCreateUserDomain(S_userDomain createUserDomain) {
		this.createUserDomain = createUserDomain;
	}

	public S_userDomain getCreateUserDomain() {
		return this.createUserDomain;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getCreate_date() {
		return this.create_date;
	}

	public void setModifyUserDomain(S_userDomain modifyUserDomain) {
		this.modifyUserDomain = modifyUserDomain;
	}

	public S_userDomain getModifyUserDomain() {
		return this.modifyUserDomain;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public Date getModify_date() {
		return this.modify_date;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public String getSts() {
		return this.sts;
	}

	public List getResourceList() {
		return resourceList;
	}

	public void setResourceList(List resourceList) {
		this.resourceList = resourceList;
	}

	public Long getLvl() {
		return lvl;
	}

	public void setLvl(Long lvl) {
		this.lvl = lvl;
	}
	
	
	
	
}
