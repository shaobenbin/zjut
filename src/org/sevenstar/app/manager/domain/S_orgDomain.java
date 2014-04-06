package org.sevenstar.app.manager.domain;

import java.util.List;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.system.domain.S_systemDomain;
import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSCacheModel;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;
import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSManyToOne;
import org.sevenstar.persistent.db.find.annotation.model.SSOneToMany;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;
import org.sevenstar.persistent.db.ibatis.IbatisDao;

@SSDomain(table = "S_org", condition = "sts<>'N'")
@SSId(generateType = "seq", seq = "seq_app")
@SSCacheModel(selectAll = true, flushinterval=30,flushonexecute = "S_orgDomain_base_insert,S_orgDomain_base_update,S_orgDomain_base_delete,S_org_positionDomain_base_insert,S_org_positionDomain_base_update,S_org_positionDomain_base_delete,S_moduleDomain_base_insert,S_moduleDomain_base_update,S_moduleDomain_base_delete")
public class S_orgDomain extends PersistentObject {

	@SSPrimaryKey
	private java.lang.Long id;

	private java.lang.String name;

	@SSManyToOne(parameter = "upDomain.id", column = "up_id")
	private S_orgDomain upDomain;
	
	@SSManyToOne(parameter = "systemDomain.id", column = "system_id")
	private S_systemDomain systemDomain;

	private java.lang.Long lvl;

	private java.lang.String description;

	private java.lang.String sts;

	@SSOneToMany(className = "org.sevenstar.app.manager.domain.S_orgDomain", column = "up_id")
	private List childList;

	@SSOneToMany(className = "org.sevenstar.app.manager.domain.S_org_positionDomain", column = "org_id")
	private List positionList;

	private String code;
	
	 

	public void update() {
		this.setSts("Y");
		S_orgDomain paramDomain = new S_orgDomain();
		paramDomain.setName(this.getName());
		if (paramDomain.updateExist(this.getId()) ){
			throw new ApplicationException("部门名[" + this.getName() + "]已存在");
		}
		this.setLvl(null);
		this.setCode(null);
		super.update();
	}

	public void insert() {
		this.setSts("Y");
		if (hasOrg(this.getName())) {
			throw new ApplicationException("部门名[" + this.getName() + "]已存在");
		}
		this.setCode(this.getCode(this));
		if(this.getUpDomain() != null && this.getUpDomain().getLvl() != null){
			this.setLvl(new Long(this.getUpDomain().getLvl().longValue() + 1));
		}else{
			this.setLvl(new Long(0));
		}
		super.insert();
	}

	private String getCode(S_orgDomain orgDomain) {
		if (orgDomain.getUpDomain() == null) {
			return "0001";
		}
		String prefix = orgDomain.getUpDomain().getCode();
		if(prefix == null){
			prefix = "0001";
		}
		String maxcode = IbatisDao.getDao().queryForString(
				"S_orgDomain_exp_select_maxcode", prefix);
		if (maxcode == null || "".equals(maxcode) || prefix.equals(maxcode)) {
			return prefix + "0001";
		} else {
			String seq = maxcode.substring(prefix.length());
			seq = String.valueOf(Long.parseLong(seq) + 1);
			if (seq.length() == 1) {
				seq = "000" + seq;
			}
			if (seq.length() == 2) {
				seq = "00" + seq;
			}
			if (seq.length() == 3) {
				seq = "0" + seq;
			}
			if (seq.length() > 4) {
				throw new ApplicationException("code 超出长度,请联系系统管理员解决");
			}
			return prefix + seq;
		}
	}
	
	

	 

	public S_systemDomain getSystemDomain() {
		return systemDomain;
	}

	public void setSystemDomain(S_systemDomain systemDomain) {
		this.systemDomain = systemDomain;
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

	public boolean hasOrg(String name) {
		S_orgDomain param = new S_orgDomain();
		param.setName(name);
		param.setSts("Y");
		return param.exist();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public void setUpDomain(
			org.sevenstar.app.manager.domain.S_orgDomain upDomain) {
		this.upDomain = upDomain;
	}

	public org.sevenstar.app.manager.domain.S_orgDomain getUpDomain() {
		return this.upDomain;
	}

	public void setLvl(java.lang.Long lvl) {
		this.lvl = lvl;
	}

	public java.lang.Long getLvl() {
		return this.lvl;
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

	public List getPositionList() {
		return positionList;
	}

	public void setPositionList(List positionList) {
		this.positionList = positionList;
	}

	public List getChildList() {
		return childList;
	}

	public void setChildList(List childList) {
		this.childList = childList;
	}

}
