package org.sevenstar.app.module.domain;

import java.util.Date;
import java.util.List;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.manager.domain.S_userDomain;
import org.sevenstar.app.system.domain.S_systemDomain;
import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSCacheModel;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;
import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSManyToOne;
import org.sevenstar.persistent.db.find.annotation.model.SSOneToMany;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;
import org.sevenstar.persistent.db.find.annotation.model.SSProperty;
import org.sevenstar.util.RegexpHelper;
import org.sevenstar.web.Constants;

 
@SSDomain(table="s_module",condition="sts<>'N'")
@SSId(generateType="seq",seq="seq_app")
@SSCacheModel(selectAll=true,readonly="true")
public class S_moduleDomain extends PersistentObject {
	@SSPrimaryKey
	private Long id;
	 
	private String name;
 
	@SSManyToOne(parameter="upDomain.id",column="up_id")
	private S_moduleDomain upDomain;
	
	@SSManyToOne(parameter = "systemDomain.id", column = "system_id")
	private S_systemDomain systemDomain;
	 
	private String url;
	 
	private String pattern;
	 
	private String description;
	 
	private String target;
	 
	private String sts;
 
	private String ismenu;
 
	private String html;

	private Long lvl;
	
	@SSProperty(value = "sysdate", update = false)
	private Date create_date;
	
	@SSManyToOne(column = "create_user_id", parameter = "createUserDomain.id")
	private S_userDomain createUserDomain;
	
	@SSProperty(value = "sysdate")
	private Date modify_date;
	
	@SSManyToOne(column = "modify_user_id", parameter = "modifyUserDomain.id")
	private S_userDomain modifyUserDomain;

	@SSOneToMany(className="org.sevenstar.app.module.domain.S_moduleDomain",column="up_id")
	private List childList;
	
	public S_moduleDomain getS_moduleDomain(String url) {
		List moduleList = (new S_moduleDomain()).selectAll();
		S_moduleDomain moduleDomain = null;
		for (int i = 0; i < moduleList.size(); i++) {
			S_moduleDomain domain = (S_moduleDomain) moduleList.get(i);
			if (match(url, domain.getPattern())) {
				if (moduleDomain == null) {
					moduleDomain = domain;
				} else {
					if (moduleDomain.getLvl().longValue() < domain.getLvl()
							.longValue()) {
						/**
						 * 取最符合的模块
						 */
						moduleDomain = domain;
					}
				}
			}
		}
		return moduleDomain;
	}
	
	private boolean match(String url, String pattern) {
		if (pattern == null || "".equals(pattern) || url == null
				|| "".equals(url)) {
			return false;
		}
		try {
			if (pattern.indexOf(Constants.separator) == -1) {
				return RegexpHelper.isGlobmatches(url, pattern);
			} else {
				String[] ss = pattern.split(Constants.separator);
				for (int i = 0; i < ss.length; i++) {
					if (RegexpHelper.isGlobmatches(url, ss[i])) {
						return true;
					}
				}
				return false;
			}
		} catch (Throwable e) {
           return false;
		}
	}
	
	public void update( ) {
		this.setSts("Y");
		S_moduleDomain paramDomain = new S_moduleDomain();
		paramDomain.setName(this.getName());
		if(paramDomain.updateExist(this.getId())){
			throw new ApplicationException("模块名["
					+ this.getName() + "]已存在");
		}
		super.update();
	}

	public void insert( ) {
		this.setSts("Y");
		S_moduleDomain paramDomain = new S_moduleDomain();
		paramDomain.setName(this.getName());
		if(paramDomain.exist()){
			throw new ApplicationException("模块名["
					+ this.getName() + "]已存在");
		}
		if(this.getUpDomain() != null && this.getUpDomain().getId() != null){
			this.getUpDomain().loadEqual();
			this.setLvl(new Long(this.getUpDomain().getLvl().longValue()+1));
		}else{
			this.setLvl(new Long(1));
		}
		super.insert();
	}

	public void insertOrUpdate( ) {
		 
		if (this.getId() != null) {
			this.update();
		} else {
			this.insert();
		}
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

	public void delete( ) {
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

	public void setUpDomain(
			org.sevenstar.app.module.domain.S_moduleDomain upDomain) {
		this.upDomain = upDomain;
	}

	public org.sevenstar.app.module.domain.S_moduleDomain getUpDomain() {
		return this.upDomain;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return this.pattern;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTarget() {
		return this.target;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public String getSts() {
		return this.sts;
	}


	public String getIsmenu() {
		return ismenu;
	}

	public void setIsmenu(String ismenu) {
		this.ismenu = ismenu;
	}

	public List getChildList() {
		return childList;
	}

	public void setChildList(List childList) {
		this.childList = childList;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public Long getLvl() {
		return lvl;
	}

	public void setLvl(Long lvl) {
		this.lvl = lvl;
	}

	public S_systemDomain getSystemDomain() {
		return systemDomain;
	}

	public void setSystemDomain(S_systemDomain systemDomain) {
		this.systemDomain = systemDomain;
	}

 
	

}
