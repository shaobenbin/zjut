package org.sevenstar.app.config.domain;

import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;

import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSOneToMany;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;
import org.sevenstar.persistent.db.find.annotation.model.SSProperty;

import java.util.Date;
import java.util.List;

import org.sevenstar.persistent.db.find.annotation.model.SSManyToOne;
import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.manager.domain.S_userDomain;
import org.sevenstar.app.system.domain.S_systemDomain;

@SSDomain(table = "s_configure_type", condition = "sts<>'N'")
@SSId(generateType = "seq", seq = "seq_app")
public class S_configure_typeDomain extends PersistentObject {

	@SSPrimaryKey
	private Long id;
	private String name;
	@SSManyToOne(parameter = "systemDomain.id", column = "system_id")
	private S_systemDomain systemDomain;
	@SSProperty(value = "sysdate", update = false)
	private Date create_date;
	@SSManyToOne(column = "up_id", parameter = "upDomain.id")
	private S_configure_typeDomain upDomain;
	@SSManyToOne(column = "create_user_id", parameter = "createUserDomain.id")
	private S_userDomain createUserDomain;
	@SSProperty(value = "sysdate")
	private Date modify_date;
	@SSManyToOne(column = "modify_user_id", parameter = "modifyUserDomain.id")
	private S_userDomain modifyUserDomain;

	private String sts;
	@SSOneToMany(className = "org.sevenstar.app.config.domain.S_configure_typeDomain", column = "up_id")
	private List childList;
	@SSOneToMany(className = "org.sevenstar.app.config.domain.S_configureDomain", column = "type_id")
	private List configList;

	public void update() {
		this.setSts("Y");
		S_configure_typeDomain paramDomain = new S_configure_typeDomain();
		paramDomain.setName(this.getName());
		if (paramDomain.updateExist(this.getId())) {
			throw new ApplicationException("配置[" + this.getName() + "]已存在");
		}
		super.update();
	}

	public void insert() {
		this.setSts("Y");
		S_configure_typeDomain paramDomain = new S_configure_typeDomain();
		paramDomain.setName(this.getName());
		if (paramDomain.exist()) {
			throw new ApplicationException("配置[" + this.getName() + "]已存在");
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

	 

	public void setSts(String sts) {
		this.sts = sts;
	}

	public String getSts() {
		return this.sts;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getCreate_date() {
		return this.create_date;
	}

	public void setCreateUserDomain(S_userDomain createUserDomain) {
		this.createUserDomain = createUserDomain;
	}

	public S_userDomain getCreateUserDomain() {
		return this.createUserDomain;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public Date getModify_date() {
		return this.modify_date;
	}

	public void setModifyUserDomain(S_userDomain modifyUserDomain) {
		this.modifyUserDomain = modifyUserDomain;
	}

	public S_userDomain getModifyUserDomain() {
		return this.modifyUserDomain;
	}

	public List getChildList() {
		return childList;
	}

	public void setChildList(List childList) {
		this.childList = childList;
	}

	public S_configure_typeDomain getUpDomain() {
		return upDomain;
	}

	public void setUpDomain(S_configure_typeDomain upDomain) {
		this.upDomain = upDomain;
	}

 

	public List getConfigList() {
		return configList;
	}

	public void setConfigList(List configList) {
		this.configList = configList;
	}

	public S_systemDomain getSystemDomain() {
		return systemDomain;
	}

	public void setSystemDomain(S_systemDomain systemDomain) {
		this.systemDomain = systemDomain;
	}	
	
	
}
