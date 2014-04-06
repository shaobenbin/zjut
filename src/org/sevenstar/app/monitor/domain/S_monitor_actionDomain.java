package org.sevenstar.app.monitor.domain;

import java.util.Date;

import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;
import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;
import org.sevenstar.persistent.db.find.annotation.model.SSProperty;

@SSDomain(table="s_monitor_action")
@SSId(generateType="seq",seq="seq_monitor")
public class S_monitor_actionDomain extends PersistentObject {
	
	@SSPrimaryKey
	private Long id;
	
	private Long execute_time;
	
	private String url;
	
	private Long sqlmapid_num;
	
	private String sqlmapid;
	
	@SSProperty(value="sysdate")
	private Date create_date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getExecute_time() {
		return execute_time;
	}

	public void setExecute_time(Long execute_time) {
		this.execute_time = execute_time;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getSqlmapid_num() {
		return sqlmapid_num;
	}

	public void setSqlmapid_num(Long sqlmapid_num) {
		this.sqlmapid_num = sqlmapid_num;
	}

	public String getSqlmapid() {
		return sqlmapid;
	}

	public void setSqlmapid(String sqlmapid) {
		this.sqlmapid = sqlmapid;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

}
