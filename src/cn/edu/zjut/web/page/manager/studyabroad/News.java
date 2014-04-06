package cn.edu.zjut.web.page.manager.studyabroad;

import cn.edu.zjut.cfg.domain.Zjut_new_typeDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.web.page.manager.DefaultNews;

public class News extends DefaultNews {

	private static final Zjut_organizationDomain zjut_organizationDomainOfCollege;
	
	private Zjut_new_typeDomain typeDomain;

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
		super.list();
		return super.SUCCESS;
	}
	
	public String edit(){
		if(typeDomain == null || typeDomain.getId() == null){
			typeDomain = new Zjut_new_typeDomain();
			typeDomain.setName("common");
			typeDomain.loadEqual();
		}else{
			typeDomain.loadEqual();
		}
		super.edit();
		return super.SUCCESS;
	}
	
	public String input(){
		if(typeDomain == null || typeDomain.getId() == null){
			typeDomain = new Zjut_new_typeDomain();
			typeDomain.setName("common");
			typeDomain.loadEqual();
		}else{
			typeDomain.loadEqual();
		}
		super.input();
		return "manager/studyabroad/news/edit.html";
	}

	public Zjut_new_typeDomain getTypeDomain() {
		return typeDomain;
	}

	public void setTypeDomain(Zjut_new_typeDomain typeDomain) {
		this.typeDomain = typeDomain;
	}
	
	
}
