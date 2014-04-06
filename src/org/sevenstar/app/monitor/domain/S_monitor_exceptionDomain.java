package org.sevenstar.app.monitor.domain;

import java.util.Date;

import org.sevenstar.app.manager.domain.S_userDomain;
import org.sevenstar.app.module.domain.S_moduleDomain;
import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;
import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSManyToOne;
import org.sevenstar.persistent.db.find.annotation.model.SSOneToOne;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;
import org.sevenstar.persistent.db.find.annotation.model.SSProperty;

@SSDomain(table = "s_monitor_exception")
@SSId(generateType = "seq", seq = "seq_monitor")
public class S_monitor_exceptionDomain extends PersistentObject {
	@SSPrimaryKey
	private Long id;
	
	private String url;
	
	private String message;
	
	@SSManyToOne(parameter = "userDomain.id", column = "user_id")
	private S_userDomain userDomain;
	
	@SSManyToOne(parameter = "moduleDomain.id", column = "module_id")
	private S_moduleDomain moduleDomain;
	
	@SSProperty(value = "sysdate")
	private Date create_date;
	
	private String key_param0;
	
	private String key_param1;
	
	private String key_param2;
	
	@SSOneToOne(column = "exception_id")
	private S_monitor_exception_detailDomain detailDomain;

	public S_monitor_exception_detailDomain getDetailDomain() {
		return detailDomain;
	}

	public void setDetailDomain(S_monitor_exception_detailDomain detailDomain) {
		this.detailDomain = detailDomain;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public S_userDomain getUserDomain() {
		return userDomain;
	}

	public void setUserDomain(S_userDomain userDomain) {
		this.userDomain = userDomain;
	}

	public S_moduleDomain getModuleDomain() {
		return moduleDomain;
	}

	public void setModuleDomain(S_moduleDomain moduleDomain) {
		this.moduleDomain = moduleDomain;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getKey_param0() {
		return key_param0;
	}

	public void setKey_param0(String key_param0) {
		this.key_param0 = key_param0;
	}

	public String getKey_param1() {
		return key_param1;
	}

	public void setKey_param1(String key_param1) {
		this.key_param1 = key_param1;
	}

	public String getKey_param2() {
		return key_param2;
	}

	public void setKey_param2(String key_param2) {
		this.key_param2 = key_param2;
	}

}
