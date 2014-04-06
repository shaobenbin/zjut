package cn.edu.zjut.web.page.student;

import java.util.List;

import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.service.Zjut_contentService;

public class Jyzl extends DefaultAction {
	private static Zjut_organizationDomain organizationDomain;

	static {
		organizationDomain = new Zjut_organizationDomain();
		organizationDomain.setName("student");
		organizationDomain.loadEqual();
	}

	private String employment_policyJson;
	private String entrepreneurial_baseJson;
	private String recruitment_infoJson;

	public String jyzc() {
		jyzl();
		return "student/jyzl.html";
	}

	public String cyjd() {
		jyzl();
		return "student/jyzl.html";
	}

	public String zpxx() {
		jyzl();
		return "student/jyzl.html";
	}
	
	private void jyzl(){
		List employment_policyList = Zjut_contentService.instance.getContentList(organizationDomain.getName(), "employment_policy");
		this.employment_policyJson = Zjut_contentService.instance.contentList2json(employment_policyList);
		
		List entrepreneurial_baseList = Zjut_contentService.instance.getContentList(organizationDomain.getName(), "entrepreneurial_base");
		this.entrepreneurial_baseJson = Zjut_contentService.instance.contentList2json(entrepreneurial_baseList);
		
		List recruitment_infoList = Zjut_contentService.instance.getContentList(organizationDomain.getName(), "recruitment_info");
		this.recruitment_infoJson = Zjut_contentService.instance.contentList2json(recruitment_infoList);
	}

	public String getEmployment_policyJson() {
		return employment_policyJson;
	}

	public void setEmployment_policyJson(String employment_policyJson) {
		this.employment_policyJson = employment_policyJson;
	}

	public String getEntrepreneurial_baseJson() {
		return entrepreneurial_baseJson;
	}

	public void setEntrepreneurial_baseJson(String entrepreneurial_baseJson) {
		this.entrepreneurial_baseJson = entrepreneurial_baseJson;
	}

	public String getRecruitment_infoJson() {
		return recruitment_infoJson;
	}

	public void setRecruitment_infoJson(String recruitment_infoJson) {
		this.recruitment_infoJson = recruitment_infoJson;
	}

}
