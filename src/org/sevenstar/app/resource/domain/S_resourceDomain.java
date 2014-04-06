package org.sevenstar.app.resource.domain;

import java.util.Date;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.manager.domain.S_userDomain;
import org.sevenstar.app.resource.service.S_resourceService;
import org.sevenstar.persistent.db.PersistentFactory;
import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSCacheModel;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;
import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSManyToOne;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;
import org.sevenstar.persistent.db.find.annotation.model.SSProperty;

@SSDomain(table = "s_resource", condition = "sts<>'N'")
@SSId(generateType = "seq", seq = "seq_app")
@SSCacheModel(selectAll = true)
public class S_resourceDomain extends PersistentObject {
	@SSPrimaryKey
	private Long id;

	@SSManyToOne(column = "type_id", parameter = "typeDomain.id")
	private S_resource_typeDomain typeDomain;

	private String name;

	private String code;

	private String path;

	private String content;

	private Long version;

	private String is_top;

	@SSProperty(value = "sysdate", update = false)
	private Date create_date;

	@SSManyToOne(column = "create_user_id", parameter = "createUserDomain.id")
	private S_userDomain createUserDomain;

	@SSProperty(value = "sysdate")
	private Date modify_date;

	@SSManyToOne(column = "modify_user_id", parameter = "modifyUserDomain.id")
	private S_userDomain modifyUserDomain;

	private String sts;
	
	public boolean isFile(){
		return this.getPath() != null && !"".equals(this.getPath());
	}

	/**
	 * 更新生成新的版本
	 */
	public void update() {
		if(this.getPath() != null){
			this.setPath(this.getPath().trim());
		}
		if(this.getContent() != null){
			this.setContent(this.getContent().trim());
		}
		if (this.getName() == null || "".equals(this.getName())) {
			throw new ApplicationException("资源名称不能为空");
		}
		if (this.path != null && !"".equals(this.path) && this.content != null
				&& !"".equals(this.content)) {
			throw new ApplicationException("资源[" + this.getName()
					+ "]路径与内容不能共存");
		}
		S_resourceDomain maxVersionDomain = null;
		/**
		 * 如果是最高版本,则修改老版本
		 */
		if ("Y".equals(this.getIs_top())) {
			S_resourceDomain oldDomain = new S_resourceDomain();
			oldDomain.setId(this.getId());
			oldDomain.load();
			oldDomain.setIs_top("N");
			PersistentFactory.update(oldDomain);
			maxVersionDomain = oldDomain;
		}else{
			maxVersionDomain = S_resourceService.instance
				.getMaxVersionDomain(this.getTypeDomain(), this.getName());
			maxVersionDomain.setIs_top("N");
			PersistentFactory.update(maxVersionDomain);
		}
		/**
		 * 生成新版本
		 */
		this.setIs_top("Y");
		this.setVersion(new Long(maxVersionDomain.getVersion().longValue()) + 1);
		this.setSts("Y");
		/**
		 * 2000-4000以内的补数
		 */
		if( this.getContent().length() < 4000 ){
			while(this.getContent().length() <= 4000){
				this.setContent(this.getContent()+" ");
			}
		}
		super.insert();
	}

	public void insert() {
		if(this.getPath() != null){
			this.setPath(this.getPath().trim());
		}
		if(this.getContent() != null){
			this.setContent(this.getContent().trim());
		}
		if (this.getName() == null || "".equals(this.getName())) {
			throw new ApplicationException("资源名称不能为空");
		}
		if (this.path != null && !"".equals(this.path) && this.content != null
				&& !"".equals(this.content)) {
			throw new ApplicationException("资源[" + this.getName()
					+ "]路径与内容不能共存");
		}

		this.setSts("Y");
		/**
		 * 查询是否有同名的
		 */
		S_resourceDomain paramDomain = new S_resourceDomain();
		paramDomain.setName(this.getName());
		paramDomain.setTypeDomain(this.getTypeDomain());
		if (paramDomain.exist()) {
			throw new ApplicationException("资源[" + this.getName() + "]已存在");
		}
		this.setVersion(new Long(0));
		this.setIs_top("Y");
		/**
		 * 2000-4000以内的补数
		 */
		if( this.getContent().length() < 4000 ){
			while(this.getContent().length() <= 4000){
				this.setContent(this.getContent()+" ");
			}
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
		/**
		 * 判断版本,如果是删除最新的版本,则将次版本设置成最新的版本
		 */
		S_resourceDomain domain = new S_resourceDomain();
		domain.setId(this.getId());
		domain.load();
		if ("Y".equals(domain.getIs_top())
				&& domain.getVersion().longValue() > 0) {
			/**
			 * 将次版本设置成最新的版本
			 */
			S_resourceDomain sencondDomain = S_resourceService.instance
					.getSecondVersionDomain(this);
			sencondDomain.setIs_top("Y");
			PersistentFactory.update(sencondDomain);
		}
		this.setIs_top("N");
		this.setSts("N");
		super.update();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public S_resource_typeDomain getTypeDomain() {
		return typeDomain;
	}

	public void setTypeDomain(S_resource_typeDomain typeDomain) {
		this.typeDomain = typeDomain;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getIs_top() {
		return is_top;
	}

	public void setIs_top(String is_top) {
		this.is_top = is_top;
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

}
