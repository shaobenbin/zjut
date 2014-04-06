package cn.edu.zjut.web.page.manager.student;

import cn.edu.zjut.cfg.domain.Zjut_content_typeDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.web.page.manager.DefaultContent;

/**
 * 就业政策
 * @author shao
 *
 */
public class Employmentpolicy extends DefaultContent{
	private static final Zjut_organizationDomain zjut_organizationDomainOfCollege;
	private static final Zjut_content_typeDomain zjut_content_typeDomain;

	static {
		zjut_organizationDomainOfCollege = new Zjut_organizationDomain();
		zjut_organizationDomainOfCollege.setName("student");
		zjut_organizationDomainOfCollege.loadEqual();
		
		zjut_content_typeDomain = new Zjut_content_typeDomain();
		zjut_content_typeDomain.setName("employment_policy");
		zjut_content_typeDomain.loadEqual();
	}

	@Override
	public Zjut_content_typeDomain getContentTypeDomain() {
		// TODO Auto-generated method stub
		return zjut_content_typeDomain;
	}

	@Override
	public Zjut_organizationDomain getOrganizationDomain() {
		// TODO Auto-generated method stub
		return zjut_organizationDomainOfCollege;
	}
	
}
