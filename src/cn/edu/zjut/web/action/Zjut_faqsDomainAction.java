package cn.edu.zjut.web.action;

import java.util.Date;

import org.sevenstar.app.common.context.ApplicationContext;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;

import cn.edu.zjut.cfg.domain.Zjut_faqsDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_faqDomain;
import cn.edu.zjut.cfg.service.Zjut_faqsService;

@SSAction(name = "zjut_faqs")
public class Zjut_faqsDomainAction extends DefaultAction{
	
	private Zjut_faqsDomain zjut_faqsDomain;
	
	private Zjut_organizationDomain zjut_organizationDomain;
	
	public String delete() {
		if(zjut_faqsDomain == null || zjut_faqsDomain.getId() == null){
			throw new RuntimeException("缺少参数，不能删除!");
		}
		
		zjut_faqsDomain.loadEqual();
		Zjut_organization_faqDomain organization_faqDomain = new Zjut_organization_faqDomain();
		organization_faqDomain.setFaqsDomain(zjut_faqsDomain);
		organization_faqDomain.loadEqual();
		organization_faqDomain.delete();
		zjut_faqsDomain.delete();
		Zjut_faqsService.instance.reload();
		return "删除["+zjut_faqsDomain.getTitle()+"]成功";
		
	}
	
	public String insertOrUpdate() {
		if(zjut_faqsDomain == null || zjut_organizationDomain == null || zjut_organizationDomain.getId() == null){
			throw new RuntimeException("缺少参数，不能录入!");
		}
		
		Zjut_organization_faqDomain organization_faqDomain  = null;
		if(zjut_faqsDomain.getId() == null){
			organization_faqDomain = new Zjut_organization_faqDomain();
			zjut_organizationDomain.loadEqual();
			organization_faqDomain.setOrganizationDomain(zjut_organizationDomain);
			organization_faqDomain.setModify_date(new Date());
			organization_faqDomain.setModifyUserDomain(ApplicationContext.get());
		}
		
		zjut_faqsDomain.setModify_date(new Date());
		zjut_faqsDomain.setModifyUserDomain(ApplicationContext.get());
		zjut_faqsDomain.insertOrUpdate();
		
		if(organization_faqDomain!=null){
			organization_faqDomain.setFaqsDomain(zjut_faqsDomain);
			organization_faqDomain.insertOrUpdate();
		}
		Zjut_faqsService.instance.reload();
		return "添加["+zjut_faqsDomain.getTitle()+"]成功";
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
	
	
}
