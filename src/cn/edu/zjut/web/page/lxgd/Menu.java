package cn.edu.zjut.web.page.lxgd;


import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_menu_contentDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_resourceDomain;
import cn.edu.zjut.cfg.service.Zjut_menu_contentService;

public class Menu extends DefaultAction {
	private Zjut_menu_contentDomain zjut_menu_contentDomain;

	private IbatisPage page = new IbatisPage();

	private static Zjut_organizationDomain organizationDomain;
	static {
		organizationDomain = new Zjut_organizationDomain();
		organizationDomain.setName("study-abroad");
		organizationDomain.loadEqual();

	}

	public String about_us() {
		getMenuDomain("about_us");
		return super.SUCCESS;
	}

	public String gzzd() {
		getMenuDomain("gzzd");
		return super.SUCCESS;
	}
	
	public String jxj(){
		getMenuDomain("jxj");
		return super.SUCCESS;
	}
	
	public String lxwm(){
		getMenuDomain("lxwm");
		return super.SUCCESS;
	}
	
	public String rxsq(){
		getMenuDomain("rxsq");
		return super.SUCCESS;
	}

	public String shzn(){
		getMenuDomain("shzn");
		return super.SUCCESS;
	}

	public String tsxm(){
		getMenuDomain("tsxm");
		return super.SUCCESS;
	}
	
	public String xxyd(){
		getMenuDomain("xxyd");
		return super.SUCCESS;
	}
	
	public String xyyzy(){
		getMenuDomain("xyyzy");
		return super.SUCCESS;
	}



	public String recruit_students() {
		getMenuDomain("recruit_students");
		return super.SUCCESS;
	}

	public String teach() {
		Zjut_organization_resourceDomain organization_resourceDomain = new Zjut_organization_resourceDomain();
		organization_resourceDomain.setOrganizationDomain(organizationDomain);
		page.setPagesize(10);
		page = organization_resourceDomain.getSelectPage(page);

		getMenuDomain("teach");
		return super.SUCCESS;
	}
	
	public String fybz(){
		getMenuDomain("feiyongbiaozhun");
		return super.SUCCESS;
	}

	public String research() {
		getMenuDomain("research");
		return super.SUCCESS;
	}

	public String schoolfellow() {
		getMenuDomain("schoolfellow");
		return super.SUCCESS;
	}

	private void getMenuDomain(String menuName) {
		zjut_menu_contentDomain = Zjut_menu_contentService.instance
				.getMenuDomain(menuName, organizationDomain.getName());

	}

	public Zjut_menu_contentDomain getZjut_menu_contentDomain() {
		return zjut_menu_contentDomain;
	}

	public void setZjut_menu_contentDomain(
			Zjut_menu_contentDomain zjut_menu_contentDomain) {
		this.zjut_menu_contentDomain = zjut_menu_contentDomain;
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}
}
