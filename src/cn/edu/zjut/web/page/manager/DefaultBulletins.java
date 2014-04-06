package cn.edu.zjut.web.page.manager;

import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.util.BeanHelper;
import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_bulletinDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_bulletinDomain;

public abstract class DefaultBulletins extends DefaultAction {
	private IbatisPage page;

	private Zjut_bulletinDomain zjut_bulletinDomain;

	private Zjut_organization_bulletinDomain zjut_organization_bulletinsDomain;

	private Zjut_organizationDomain zjut_organizationDomain;

	public abstract Zjut_organizationDomain getOrganizationDomain();

	public abstract String getSource();

	public String list() {
		zjut_organization_bulletinsDomain = new Zjut_organization_bulletinDomain();
		zjut_organization_bulletinsDomain.setOrganizationDomain(this
				.getOrganizationDomain());
		page = zjut_organization_bulletinsDomain.getSelectPage(page);
		return "manager/defaultbulletins/list.html";
	}

	public String edit() {
		if (zjut_bulletinDomain == null || zjut_bulletinDomain.getId() == null) {
			throw new RuntimeException("缺少公告编号，不能修改!");
		}

		zjut_bulletinDomain.loadEqual();
		return "manager/defaultbulletins/edit.html";
	}

	public String left() {
		if (zjut_bulletinDomain.getId() == null) {
			throw new RuntimeException("缺少新闻的信息，不能查看详情!");
		}
		zjut_bulletinDomain.loadEqual();
		return "manager/defaultbulletins/left.html";
	}

	public String input() {
		zjut_organizationDomain = new Zjut_organizationDomain();
		BeanHelper.copy(this.getOrganizationDomain(), zjut_organizationDomain);
		zjut_bulletinDomain = new Zjut_bulletinDomain();
		zjut_bulletinDomain.setSource(getSource());
		return "manager/defaultbulletins/edit.html";
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}

	public Zjut_bulletinDomain getZjut_bulletinDomain() {
		return zjut_bulletinDomain;
	}

	public void setZjut_bulletinDomain(Zjut_bulletinDomain zjut_bulletinDomain) {
		this.zjut_bulletinDomain = zjut_bulletinDomain;
	}

	public Zjut_organization_bulletinDomain getZjut_organization_bulletinsDomain() {
		return zjut_organization_bulletinsDomain;
	}

	public void setZjut_organization_bulletinsDomain(
			Zjut_organization_bulletinDomain zjut_organization_bulletinsDomain) {
		this.zjut_organization_bulletinsDomain = zjut_organization_bulletinsDomain;
	}

	public Zjut_organizationDomain getZjut_organizationDomain() {
		return zjut_organizationDomain;
	}

	public void setZjut_organizationDomain(
			Zjut_organizationDomain zjut_organizationDomain) {
		this.zjut_organizationDomain = zjut_organizationDomain;
	}

}
