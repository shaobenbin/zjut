package cn.edu.zjut.cfg.domain;

import java.util.Date;

import org.sevenstar.app.manager.domain.S_userDomain;
import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;
import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSManyToOne;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;
import org.sevenstar.persistent.db.find.annotation.model.SSProperty;

@SuppressWarnings("serial")
@SSDomain(table = "zjut_organization_new", condition = "sts<>'N'")
@SSId(generateType = "seq", seq = "zjut_app")
public class Zjut_organization_newDomain extends PersistentObject {

	@SSPrimaryKey
	private Long id;

	@SSManyToOne(column = "organization_id", parameter = "organizationDomain.id")
	private Zjut_organizationDomain organizationDomain;

	@SSManyToOne(column = "new_id", parameter = "newDomain.id")
	private Zjut_newDomain newDomain;

	@SSManyToOne(column = "new_type_id", parameter = "newTypeDomain.id")
	private Zjut_new_typeDomain newTypeDomain;

	@SSProperty(value = "sysdate", update = false)
	private Date create_date;
	@SSManyToOne(column = "create_user_id", parameter = "createUserDomain.id")
	private S_userDomain createUserDomain;
	@SSProperty(value = "sysdate")
	private Date modify_date;
	@SSManyToOne(column = "modify_user_id", parameter = "modifyUserDomain.id")
	private S_userDomain modifyUserDomain;
	private String sts;

	public void insertOrUpdate() {
		if (this.id == null) {
			this.insert();
		} else {
			super.update();
		}
	}

	public void insert() {
		this.sts = "Y";
		super.insert();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Zjut_organizationDomain getOrganizationDomain() {
		return organizationDomain;
	}

	public void setOrganizationDomain(Zjut_organizationDomain organizationDomain) {
		this.organizationDomain = organizationDomain;
	}

	public Zjut_newDomain getNewDomain() {
		return newDomain;
	}

	public void setNewDomain(Zjut_newDomain newDomain) {
		this.newDomain = newDomain;
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

	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public Zjut_new_typeDomain getNewTypeDomain() {
		return newTypeDomain;
	}

	public void setNewTypeDomain(Zjut_new_typeDomain newTypeDomain) {
		this.newTypeDomain = newTypeDomain;
	}

}
