package org.sevenstar.app.manager.domain;

import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;
import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;
@SSDomain(table = "s_user_type", condition = "sts<>'N'")
@SSId(generateType = "seq", seq = "seq_app")
public class S_user_typeDomain extends PersistentObject {
	@SSPrimaryKey
	private Long id;

	private String name;

	private String description;

	private String sts;
	
	public void update() {
		this.setSts("Y");
		super.update();
	}

	public void insert() {
		this.setSts("Y");
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

	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

}
