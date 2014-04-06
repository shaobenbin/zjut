package org.sevenstar.app.monitor.domain;

import java.util.Date;

import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;
import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;
import org.sevenstar.persistent.db.find.annotation.model.SSProperty;

@SSDomain(table="s_monitor_sql")
@SSId(generateType="seq",seq="seq_monitor")
public class S_monitor_sqlDomain extends PersistentObject{
	@SSPrimaryKey
	private Long id;
	
	private String sqlmapid;
	
	private Long resultset_size;
	
	private String stacktrace;
	
	private Long execute_time;
	
	@SSProperty(value="sysdate")
	private Date create_date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSqlmapid() {
		return sqlmapid;
	}

	public void setSqlmapid(String sqlmapid) {
		this.sqlmapid = sqlmapid;
	}

	public Long getResultset_size() {
		return resultset_size;
	}

	public void setResultset_size(Long resultset_size) {
		this.resultset_size = resultset_size;
	}

	public String getStacktrace() {
		return stacktrace;
	}

	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
	}

	public Long getExecute_time() {
		return execute_time;
	}

	public void setExecute_time(Long execute_time) {
		this.execute_time = execute_time;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

}
