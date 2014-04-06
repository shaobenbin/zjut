package cn.edu.zjut.web.page.manager;

import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_videoDomain;
import cn.edu.zjut.cfg.domain.Zjut_videosDomain;

public abstract class Defaultvideos extends DefaultAction {
	private IbatisPage page;

	private Zjut_videosDomain zjut_videosDomain;

	private Zjut_organizationDomain zjut_organizationDomain;

	public abstract Zjut_organizationDomain getOrganizationDomain();

	public abstract String getSource();

	public String list() {
		Zjut_organization_videoDomain organization_videoDomain = new Zjut_organization_videoDomain();
		organization_videoDomain.setOrganizationDomain(this.getOrganizationDomain());
		page = organization_videoDomain.getSelectPage(page);
		return "manager/defaultvideos/list.html";
	}

	public String edit() {
		if (zjut_videosDomain == null || zjut_videosDomain.getId() == null) {
			throw new RuntimeException("缺少参数，不能修改视频信息!");
		}
		zjut_organizationDomain = this.getOrganizationDomain();
		zjut_videosDomain.loadEqual();
		return "manager/defaultvideos/edit.html";
	}
	
	public String left() {
		if (zjut_videosDomain == null || zjut_videosDomain.getId() == null) {
			throw new RuntimeException("缺少参数，不能修改视频信息!");
		}
		zjut_videosDomain.loadEqual();
		return "manager/defaultvideos/left.html";
	}

	public String input() {
		zjut_organizationDomain = this.getOrganizationDomain();
		return "manager/defaultvideos/edit.html";
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}

	public Zjut_videosDomain getZjut_videosDomain() {
		return zjut_videosDomain;
	}

	public void setZjut_videosDomain(Zjut_videosDomain zjut_videosDomain) {
		this.zjut_videosDomain = zjut_videosDomain;
	}

	public Zjut_organizationDomain getZjut_organizationDomain() {
		return zjut_organizationDomain;
	}

	public void setZjut_organizationDomain(Zjut_organizationDomain zjut_organizationDomain) {
		this.zjut_organizationDomain = zjut_organizationDomain;
	}

}
