package org.sevenstar.app.monitor.domain;

import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;
import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSManyToOne;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;
@SSDomain(table="s_monitor_exception_detail")
@SSId(generateType="seq",seq="seq_monitor")
public class S_monitor_exception_detailDomain extends PersistentObject{
	@SSPrimaryKey
	private Long id;
	
	@SSManyToOne(parameter="mainDomain.id",column="exception_id")
	private S_monitor_exceptionDomain mainDomain;
	
	private String stacktrace;
	
	private String request;
	
	private String response;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public S_monitor_exceptionDomain getMainDomain() {
		return mainDomain;
	}

	public void setMainDomain(S_monitor_exceptionDomain mainDomain) {
		this.mainDomain = mainDomain;
	}

	public String getStacktrace() {
		return stacktrace;
	}

	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
