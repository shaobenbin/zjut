package cn.edu.zjut.web.page.manager;

import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_questionDomain;
import cn.edu.zjut.cfg.domain.Zjut_questionDomain;

public abstract class Defaultquestion extends DefaultAction {

	private IbatisPage page;

	private Zjut_questionDomain zjut_questionDomain;

	private Zjut_organizationDomain zjut_organizationDomain;

	public abstract Zjut_organizationDomain getOrganizationDomain();

	public abstract String getSource();

	public String list() {
		Zjut_organization_questionDomain organization_questionDomain = new Zjut_organization_questionDomain();
		organization_questionDomain
				.setOrganizationDomain(getOrganizationDomain());
		page = organization_questionDomain.getSelectPage(page);
		return "manager/defaultquestion/list.html";

	}

	public String edit() {
		if (zjut_questionDomain == null || zjut_questionDomain.getId() == null) {
			throw new RuntimeException("缺少参数!");
		}
		zjut_questionDomain.loadEqual();
		zjut_organizationDomain = getOrganizationDomain();
		return "manager/defaultquestion/edit.html";
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}

	public Zjut_questionDomain getZjut_questionDomain() {
		return zjut_questionDomain;
	}

	public void setZjut_questionDomain(Zjut_questionDomain zjut_questionDomain) {
		this.zjut_questionDomain = zjut_questionDomain;
	}

	public Zjut_organizationDomain getZjut_organizationDomain() {
		return zjut_organizationDomain;
	}

	public void setZjut_organizationDomain(
			Zjut_organizationDomain zjut_organizationDomain) {
		this.zjut_organizationDomain = zjut_organizationDomain;
	}

}
