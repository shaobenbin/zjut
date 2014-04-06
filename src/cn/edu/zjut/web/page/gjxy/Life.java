package cn.edu.zjut.web.page.gjxy;

import org.sevenstar.web.action.java.DefaultAction;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_school_lifeDomain;

public class Life extends DefaultAction{
	private Zjut_school_lifeDomain zjut_school_lifeDomain;
	private Long id;
	
	private static Zjut_organizationDomain organizationDomain;
	static {
		organizationDomain = new Zjut_organizationDomain();
		organizationDomain.setName("international-college");
		organizationDomain.loadEqual();
	}
	
	public String detail() {
		if (id == null) {
			throw new RuntimeException("缺少新闻编号，不能查看");
		}
		
		zjut_school_lifeDomain = new Zjut_school_lifeDomain();
		zjut_school_lifeDomain.setId(id);
		zjut_school_lifeDomain.loadEqual();
	
		return super.SUCCESS;
	}

	public Zjut_school_lifeDomain getZjut_school_lifeDomain() {
		return zjut_school_lifeDomain;
	}

	public void setZjut_school_lifeDomain(
			Zjut_school_lifeDomain zjut_school_lifeDomain) {
		this.zjut_school_lifeDomain = zjut_school_lifeDomain;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
