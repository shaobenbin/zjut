package cn.edu.zjut.cfg.domain;

import java.util.Date;
import java.util.List;

import org.sevenstar.app.common.context.ApplicationContext;
import org.sevenstar.app.manager.domain.S_userDomain;
import org.sevenstar.persistent.db.PersistentObject;
import org.sevenstar.persistent.db.find.annotation.model.SSDomain;
import org.sevenstar.persistent.db.find.annotation.model.SSId;
import org.sevenstar.persistent.db.find.annotation.model.SSManyToOne;
import org.sevenstar.persistent.db.find.annotation.model.SSOneToMany;
import org.sevenstar.persistent.db.find.annotation.model.SSPrimaryKey;
import org.sevenstar.persistent.db.find.annotation.model.SSProperty;

/**
 * 菜单的内容配置
 * 
 * @author shao
 * 
 */
@SSDomain(table = "zjut_menu_content", condition = "sts<>'N'")
@SSId(generateType = "seq", seq = "zjut_app")
public class Zjut_menu_contentDomain extends PersistentObject {
	@SSPrimaryKey
	private Long id;

	private String menu_name;

	private String menu_display_name;

	@SSManyToOne(column = "up_id", parameter = "upDomain.id")
	private Zjut_menu_contentDomain upDomain;

	@SSOneToMany(className = "cn.edu.zjut.cfg.domain.Zjut_menu_contentDomain", column = "up_id")
	private List childList;

	private String content;

	@SSManyToOne(column = "organization_id", parameter = "organizationDomain.id")
	private Zjut_organizationDomain organizationDomain;

	@SSProperty(value = "sysdate", update = false)
	private Date create_date;
	@SSManyToOne(column = "create_user_id", parameter = "createUserDomain.id")
	private S_userDomain createUserDomain;
	@SSProperty(value = "sysdate")
	private Date modify_date;
	@SSManyToOne(column = "modify_user_id", parameter = "modifyUserDomain.id")
	private S_userDomain modifyUserDomain;
	private String sts;

	public Zjut_menu_contentDomain getChildWithName(String name) {
		if (this.getChildList() == null || name == null) {
			return null;
		}

		List childs = this.getChildList();
		for (int i = 0; i < childs.size(); i++) {
			Zjut_menu_contentDomain domain = (Zjut_menu_contentDomain) childs
					.get(i);
			if (name.equals(domain.getMenu_name())) {
				return domain;
			}
		}
		return null;
	}

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
		this.setCreateUserDomain(ApplicationContext.get());
		super.insert();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getMenu_display_name() {
		return menu_display_name;
	}

	public void setMenu_display_name(String menu_display_name) {
		this.menu_display_name = menu_display_name;
	}

	public Zjut_menu_contentDomain getUpDomain() {
		return upDomain;
	}

	public void setUpDomain(Zjut_menu_contentDomain upDomain) {
		this.upDomain = upDomain;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Zjut_organizationDomain getOrganizationDomain() {
		return organizationDomain;
	}

	public void setOrganizationDomain(Zjut_organizationDomain organizationDomain) {
		this.organizationDomain = organizationDomain;
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

	public List getChildList() {
		return childList;
	}

	public void setChildList(List childList) {
		this.childList = childList;
	}

}
