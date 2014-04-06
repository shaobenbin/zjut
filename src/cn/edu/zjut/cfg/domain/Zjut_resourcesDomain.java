package cn.edu.zjut.cfg.domain;

import java.util.Date;

import org.sevenstar.app.common.context.ApplicationContext;
import org.sevenstar.app.manager.domain.S_userDomain;
import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;
import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSManyToOne;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;
import org.sevenstar.persistent.db.find.annotation.model.SSProperty;

/**
 * 资源下载类，包括课程下载等等
 * @author shao
 *
 */
@SSDomain(table = "zjut_resources", condition = "sts<>'N'")
@SSId(generateType = "seq", seq = "zjut_app")
public class Zjut_resourcesDomain extends PersistentObject {
	@SSPrimaryKey
	private Long id;
	private String name;
	private String description;
	private String download_path;
	private Date expire_date;

	@SSManyToOne(column = "type_id", parameter = "typeDomain.id")
	private Zjut_resource_typeDomain typeDomain;

	@SSProperty(value = "sysdate", update = false)
	private Date create_date;
	@SSManyToOne(column = "create_user_id", parameter = "createUserDomain.id")
	private S_userDomain createUserDomain;
	@SSProperty(value = "sysdate")
	private Date modify_date;
	@SSManyToOne(column = "modify_user_id", parameter = "modifyUserDomain.id")
	private S_userDomain modifyUserDomain;
	private String sts;
	
	public void insertOrUpdate(){
		if(this.id == null){
			this.insert();
		}else{
			super.update();
		}
	}
	
	public void insert(){
		this.setSts("Y");
		this.setCreate_date(new Date());
		this.setCreateUserDomain(ApplicationContext.get());
		super.insert();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDownload_path() {
		return download_path;
	}

	public void setDownload_path(String download_path) {
		this.download_path = download_path;
	}

	public Date getExpire_date() {
		return expire_date;
	}

	public void setExpire_date(Date expire_date) {
		this.expire_date = expire_date;
	}

	public Zjut_resource_typeDomain getTypeDomain() {
		return typeDomain;
	}

	public void setTypeDomain(Zjut_resource_typeDomain typeDomain) {
		this.typeDomain = typeDomain;
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

}
