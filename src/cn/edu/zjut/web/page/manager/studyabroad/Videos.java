package cn.edu.zjut.web.page.manager.studyabroad;

import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.web.page.manager.Defaultvideos;

public class Videos extends Defaultvideos{

	private static final Zjut_organizationDomain zjut_organizationDomainOfAbroad;

	static {
		zjut_organizationDomainOfAbroad = new Zjut_organizationDomain();
		zjut_organizationDomainOfAbroad.setName("study-abroad");
		zjut_organizationDomainOfAbroad.loadEqual();
	}
	
	@Override
	public Zjut_organizationDomain getOrganizationDomain() {
		// TODO Auto-generated method stub
		return zjut_organizationDomainOfAbroad;
	}

	@Override
	public String getSource() {
		// TODO Auto-generated method stub
		return "留学工大";
	}

}
