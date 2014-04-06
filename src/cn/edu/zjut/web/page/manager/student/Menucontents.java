package cn.edu.zjut.web.page.manager.student;

import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.web.page.manager.DefaultMenucontents;

public class Menucontents extends DefaultMenucontents {

	private static final Zjut_organizationDomain zjut_organizationDomainOfCollege;

	static {
		zjut_organizationDomainOfCollege = new Zjut_organizationDomain();
		zjut_organizationDomainOfCollege.setName("student");
		zjut_organizationDomainOfCollege.loadEqual();
	}

	
	@Override
	public Zjut_organizationDomain getOrganizationDomain() {
		// TODO Auto-generated method stub
		return zjut_organizationDomainOfCollege;
	}

}