package cn.edu.zjut.web.page.manager.studyabroad;

import org.sevenstar.persistent.db.ibatis.IbatisPage;

import cn.edu.zjut.cfg.domain.Zjut_bulletinDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_bulletinDomain;
import cn.edu.zjut.web.page.manager.DefaultBulletins;

public class Bulletins extends DefaultBulletins {

	private static final Zjut_organizationDomain zjut_organizationDomainOfCollege;
	private Zjut_organization_bulletinDomain zjut_organization_bulletinsDomain;
	private IbatisPage page;
	private String is_en ;

	static {
		zjut_organizationDomainOfCollege = new Zjut_organizationDomain();
		zjut_organizationDomainOfCollege.setName("study-abroad");
		zjut_organizationDomainOfCollege.loadEqual();
	}

	@Override
	public Zjut_organizationDomain getOrganizationDomain() {
		// TODO Auto-generated method stub
		return zjut_organizationDomainOfCollege;
	}
	
	@Override
	public String getSource(){
		return "院办";
	}
	
	public String list(){
		zjut_organization_bulletinsDomain = new Zjut_organization_bulletinDomain();
		zjut_organization_bulletinsDomain.setOrganizationDomain(this
				.getOrganizationDomain());
		
		if("Y".equalsIgnoreCase(is_en)){
			zjut_organization_bulletinsDomain.setIs_en("Y");
		}else{
			zjut_organization_bulletinsDomain.setIs_en("N");
		}
		page = zjut_organization_bulletinsDomain.getSelectPage(page);
		return super.SUCCESS;
	}

	public String input(){
		super.input();
		if("Y".equals(is_en)){
			super.getZjut_bulletinDomain().setIs_en("Y");
		}else{
			super.getZjut_bulletinDomain().setIs_en("N");
		}
		return "manager/studyabroad/bulletins/edit.html";
	}
	
	public String edit(){
		super.edit();
		return super.SUCCESS;
	}
	
	public String getIs_en() {
		return is_en;
	}

	public void setIs_en(String is_en) {
		this.is_en = is_en;
	}

	public Zjut_organization_bulletinDomain getZjut_organization_bulletinsDomain() {
		return zjut_organization_bulletinsDomain;
	}

	public void setZjut_organization_bulletinsDomain(
			Zjut_organization_bulletinDomain zjut_organization_bulletinsDomain) {
		this.zjut_organization_bulletinsDomain = zjut_organization_bulletinsDomain;
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}
	
	
}
