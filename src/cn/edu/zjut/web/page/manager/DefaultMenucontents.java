package cn.edu.zjut.web.page.manager;

import java.util.ArrayList;
import java.util.List;

import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_menu_contentDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;

public abstract class DefaultMenucontents extends DefaultAction {
	private IbatisPage page;

	private Zjut_menu_contentDomain zjut_menu_contentDomain;

	private Zjut_organizationDomain zjut_organizationDomain;

	private List menuList;

	public abstract Zjut_organizationDomain getOrganizationDomain();

	public String tree() {
		zjut_menu_contentDomain = new Zjut_menu_contentDomain();
		zjut_menu_contentDomain.setOrganizationDomain(getOrganizationDomain());
		try{
			menuList = zjut_menu_contentDomain.selectEqual();
		}catch(RuntimeException e){
			menuList = new ArrayList();
		}
		return "manager/menucontents/tree.html";
	}

	public String main() {
		return "manager/menucontents/main.html";
	}

	public String list() {
		zjut_menu_contentDomain = new Zjut_menu_contentDomain();
		zjut_menu_contentDomain.setOrganizationDomain(getOrganizationDomain());
		page = zjut_menu_contentDomain.getSelectPage(page);
		return "manager/menucontents/list.html";
	}

	public String edit() {
		if (zjut_menu_contentDomain == null
				|| zjut_menu_contentDomain.getId() == null) {
			throw new RuntimeException("缺少参数，不能编辑");
		}

		zjut_menu_contentDomain.loadEqual();
		return "manager/menucontents/edit.html";
	}

	public String left() {
		if (zjut_menu_contentDomain == null
				|| zjut_menu_contentDomain.getId() == null) {
			throw new RuntimeException("缺少参数，不能查看");
		}

		zjut_menu_contentDomain.loadEqual();
		return "manager/menucontents/left.html";
	}

	public String input() {
		if (zjut_menu_contentDomain != null
				&& zjut_menu_contentDomain.getUpDomain() != null
				&& zjut_menu_contentDomain.getUpDomain().getId() != null) {
			zjut_menu_contentDomain.getUpDomain().loadEqual();
		}
		zjut_organizationDomain = getOrganizationDomain();
		return "manager/menucontents/edit.html";
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}

	public Zjut_menu_contentDomain getZjut_menu_contentDomain() {
		return zjut_menu_contentDomain;
	}

	public void setZjut_menu_contentDomain(
			Zjut_menu_contentDomain zjut_menu_contentDomain) {
		this.zjut_menu_contentDomain = zjut_menu_contentDomain;
	}

	public List getMenuList() {
		return menuList;
	}

	public void setMenuList(List menuList) {
		this.menuList = menuList;
	}

	public Zjut_organizationDomain getZjut_organizationDomain() {
		return zjut_organizationDomain;
	}

	public void setZjut_organizationDomain(
			Zjut_organizationDomain zjut_organizationDomain) {
		this.zjut_organizationDomain = zjut_organizationDomain;
	}

}
