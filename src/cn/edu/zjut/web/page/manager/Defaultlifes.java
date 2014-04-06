package cn.edu.zjut.web.page.manager;

import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_lifeDomain;
import cn.edu.zjut.cfg.domain.Zjut_school_lifeDomain;

public abstract class Defaultlifes extends DefaultAction {
	private IbatisPage page;

	private Zjut_school_lifeDomain zjut_school_lifeDomain;

	private Zjut_organizationDomain zjut_organizationDomain;

	private Zjut_organization_lifeDomain zjut_organization_lifeDomain;

	public abstract Zjut_organizationDomain getOrganizationDomain();

	public String list() {
		zjut_organization_lifeDomain = new Zjut_organization_lifeDomain();
		zjut_organization_lifeDomain
				.setOrganizationDomain(getOrganizationDomain());
		page = zjut_organization_lifeDomain.getSelectPage(page);
		return "manager/defaultlifes/list.html";
	}
	
	public String left(){
		if (zjut_school_lifeDomain == null
				|| zjut_school_lifeDomain.getId() == null) {
			throw new RuntimeException("缺少参数，不能进入查看页面！");
		}
		
		zjut_school_lifeDomain.loadEqual();
		return "manager/defaultlifes/left.html";
	}

	public String edit() {
		if (zjut_school_lifeDomain == null
				|| zjut_school_lifeDomain.getId() == null) {
			throw new RuntimeException("缺少参数，不能进入编辑页面！");
		}
		zjut_organizationDomain = getOrganizationDomain();
		zjut_school_lifeDomain.loadEqual();
		return "manager/defaultlifes/edit.html";
	}
	
	public String input(){
		zjut_organizationDomain = getOrganizationDomain();
		return "manager/defaultlifes/edit.html";
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}

	public Zjut_school_lifeDomain getZjut_school_lifeDomain() {
		return zjut_school_lifeDomain;
	}

	public void setZjut_school_lifeDomain(
			Zjut_school_lifeDomain zjut_school_lifeDomain) {
		this.zjut_school_lifeDomain = zjut_school_lifeDomain;
	}

	public Zjut_organizationDomain getZjut_organizationDomain() {
		return zjut_organizationDomain;
	}

	public void setZjut_organizationDomain(
			Zjut_organizationDomain zjut_organizationDomain) {
		this.zjut_organizationDomain = zjut_organizationDomain;
	}

	public Zjut_organization_lifeDomain getZjut_organization_lifeDomain() {
		return zjut_organization_lifeDomain;
	}

	public void setZjut_organization_lifeDomain(
			Zjut_organization_lifeDomain zjut_organization_lifeDomain) {
		this.zjut_organization_lifeDomain = zjut_organization_lifeDomain;
	}

}
