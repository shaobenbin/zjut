package org.sevenstar.app.config.domain;

import java.util.Date;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.manager.domain.S_userDomain;
import org.sevenstar.app.system.domain.S_systemDomain;
import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;

import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSManyToOne;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;
import org.sevenstar.persistent.db.find.annotation.model.SSProperty;

@SSDomain(table = "s_configure", condition = "sts<>'N'")
@SSId(generateType = "seq", seq = "seq_app")
public class S_configureDomain extends PersistentObject {

	@SSPrimaryKey
	private Long id;
	@SSManyToOne(column = "type_id", parameter = "typeDomain.id")
	private S_configure_typeDomain typeDomain;
	@SSManyToOne(parameter = "systemDomain.id", column = "system_id")
	private S_systemDomain systemDomain;
	private String code;
	private String name;
	private Long pos;
	@SSProperty(value = "sysdate", update = false)
	private Date create_date;
	@SSManyToOne(column = "create_user_id", parameter = "createUserDomain.id")
	private S_userDomain createUserDomain;
	@SSProperty(value = "sysdate")
	private Date modify_date;
	@SSManyToOne(column = "modify_user_id", parameter = "modifyUserDomain.id")
	private S_userDomain modifyUserDomain;
	private String sts;

	public void update() {
		this.setSts("Y");
		S_configureDomain paramDomain = new S_configureDomain();
		paramDomain.setName(this.getName());
		paramDomain.setTypeDomain(this.getTypeDomain());
		if (paramDomain.updateExist(this.getId())) {
			throw new ApplicationException("配置["
					+ this.getTypeDomain().getName() + "_" + this.getName()
					+ "]已存在");
		}
		super.update();
	}

	public void insert() {
		this.setSts("Y");
		S_configureDomain paramDomain = new S_configureDomain();
		paramDomain.setName(this.getName());
		paramDomain.setTypeDomain(this.getTypeDomain());
		if (paramDomain.exist()) {
			throw new ApplicationException("配置["
					+ this.getTypeDomain().getName() + "_" + this.getName()
					+ "]已存在");
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

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.Long getId() {
		return this.id;
	}

	public S_configure_typeDomain getTypeDomain() {
		return typeDomain;
	}

	public void setTypeDomain(S_configure_typeDomain typeDomain) {
		this.typeDomain = typeDomain;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.String getCode() {
		return this.code;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setPos(java.lang.Long pos) {
		this.pos = pos;
	}

	public java.lang.Long getPos() {
		return this.pos;
	}

	public void setSts(java.lang.String sts) {
		this.sts = sts;
	}

	public java.lang.String getSts() {
		return this.sts;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public S_userDomain getCreateUserDomain() {
		return createUserDomain;
	}

	public void setCreateUserDomain(S_userDomain createUserDomain) {
		this.createUserDomain = createUserDomain;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public S_userDomain getModifyUserDomain() {
		return modifyUserDomain;
	}

	public void setModifyUserDomain(S_userDomain modifyUserDomain) {
		this.modifyUserDomain = modifyUserDomain;
	}

	public S_systemDomain getSystemDomain() {
		return systemDomain;
	}

	public void setSystemDomain(S_systemDomain systemDomain) {
		this.systemDomain = systemDomain;
	}

}
