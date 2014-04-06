package cn.edu.zjut.cfg.domain;

import java.util.Date;

import org.sevenstar.app.manager.domain.S_userDomain;
import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;
import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSManyToOne;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;
import org.sevenstar.persistent.db.find.annotation.model.SSProperty;

@SSDomain(table = "zjut_videos", condition = "sts<>'N'")
@SSId(generateType = "seq", seq = "zjut_app")
public class Zjut_videosDomain extends PersistentObject {
	@SSPrimaryKey
	private Long id;
	private String title;
	private String author;
	private String is_valid;
	private String source;
	private Long browse_count;
	private String context;
	private String video_path;
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
		this.setSts("Y");
		this.setCreate_date(new Date());
		super.insert();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIs_valid() {
		return is_valid;
	}

	public void setIs_valid(String is_valid) {
		this.is_valid = is_valid;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Long getBrowse_count() {
		return browse_count;
	}

	public void setBrowse_count(Long browse_count) {
		this.browse_count = browse_count;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getVideo_path() {
		return video_path;
	}

	public void setVideo_path(String video_path) {
		this.video_path = video_path;
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
