package cn.edu.zjut.web.page.manager;

import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_faqsDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_faqDomain;

public abstract class Defaultfaqs extends DefaultAction {

	private Zjut_faqsDomain zjut_faqsDomain;

	private Zjut_organizationDomain zjut_organizationDomain;

	private IbatisPage page;

	public abstract Zjut_organizationDomain getOrganizationDomain();

	public abstract String getSource();

	public String list() {
		Zjut_organization_faqDomain zjut_organization_faqDomain = new Zjut_organization_faqDomain();
		zjut_organization_faqDomain.setOrganizationDomain(getOrganizationDomain());
		page = zjut_organization_faqDomain.getSelectPage(page);
		return "manager/defaultfaqs/list.html";
		
	}

	public String edit() {
		if(zjut_faqsDomain == null || zjut_faqsDomain.getId() == null){
			throw new RuntimeException("缺少参数，不能修改!");
		}
		zjut_organizationDomain = getOrganizationDomain();
		zjut_faqsDomain.loadEqual();
		
		return "manager/defaultfaqs/edit.html";
	}

	public String left() {
		if(zjut_faqsDomain == null || zjut_faqsDomain.getId() == null){
			throw new RuntimeException("缺少参数，不能查看!");
		}
		
		zjut_faqsDomain.loadEqual();
		
		return "manager/defaultfaqs/left.html";
	}

	public String input() {
		zjut_organizationDomain = getOrganizationDomain();
		zjut_faqsDomain = new Zjut_faqsDomain();
		return "manager/defaultfaqs/edit.html";
	}

	public Zjut_faqsDomain getZjut_faqsDomain() {
		return zjut_faqsDomain;
	}

	public void setZjut_faqsDomain(Zjut_faqsDomain zjut_faqsDomain) {
		this.zjut_faqsDomain = zjut_faqsDomain;
	}

	public Zjut_organizationDomain getZjut_organizationDomain() {
		return zjut_organizationDomain;
	}

	public void setZjut_organizationDomain(
			Zjut_organizationDomain zjut_organizationDomain) {
		this.zjut_organizationDomain = zjut_organizationDomain;
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}

}
