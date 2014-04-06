package cn.edu.zjut.web.page.manager.student;

import cn.edu.zjut.cfg.domain.Zjut_content_typeDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.web.page.manager.DefaultContent;

/**
 * entrepreneurial_base
 * @author shao
 * 创业基地
 */
public class Entrepreneurialbase extends DefaultContent{
	private static final Zjut_organizationDomain zjut_organizationDomainOfStudent;
	private static final Zjut_content_typeDomain zjut_content_typeDomain;
	
	static {
		zjut_organizationDomainOfStudent = new Zjut_organizationDomain();
		zjut_organizationDomainOfStudent.setName("student");
		zjut_organizationDomainOfStudent.loadEqual();
		
		zjut_content_typeDomain = new Zjut_content_typeDomain();
		zjut_content_typeDomain.setName("entrepreneurial_base");
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
		return zjut_organizationDomainOfStudent;
	}
}