package cn.edu.zjut.web.page.manager;

import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_contentDomain;
import cn.edu.zjut.cfg.domain.Zjut_content_typeDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;

public abstract class DefaultContent extends DefaultAction {
	private IbatisPage page;

	private Zjut_contentDomain zjut_contentDomain;

	public abstract Zjut_organizationDomain getOrganizationDomain();
	
	public abstract Zjut_content_typeDomain getContentTypeDomain();

	public String list() {
		zjut_contentDomain = new Zjut_contentDomain();
		zjut_contentDomain.setTypeDomain(getContentTypeDomain());
		zjut_contentDomain.setOrganizationDomain(getOrganizationDomain());
		page = zjut_contentDomain.getSelectPage(page);
		return "manager/defaultcontent/list.html";
	}

	public String edit() {
		if (zjut_contentDomain == null || zjut_contentDomain.getId() == null) {
			throw new RuntimeException("缺少必要参数，不能编辑!");
		}

		zjut_contentDomain.loadEqual();
		return "manager/defaultcontent/edit.html";
	}

	public String input() {
		zjut_contentDomain.setTypeDomain(getContentTypeDomain());
		zjut_contentDomain.setOrganizationDomain(getOrganizationDomain());
		return "manager/defaultcontent/edit.html";
	}

	public String detail() {
		if (zjut_contentDomain == null || zjut_contentDomain.getId() == null) {
			throw new RuntimeException("缺少必要参数，不能编辑!");
		}
		
		zjut_contentDomain.loadEqual();
		return "manager/defaultcontent/detail.html";
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}

	public Zjut_contentDomain getZjut_contentDomain() {
		return zjut_contentDomain;
	}

	public void setZjut_contentDomain(Zjut_contentDomain zjut_contentDomain) {
		this.zjut_contentDomain = zjut_contentDomain;
	}
}
