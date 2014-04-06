package cn.edu.zjut.web.page.manager;

import java.util.List;

import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.util.BeanHelper;
import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_resourceDomain;
import cn.edu.zjut.cfg.domain.Zjut_resource_typeDomain;
import cn.edu.zjut.cfg.domain.Zjut_resourcesDomain;

public abstract class DefaultResources extends DefaultAction {

	private IbatisPage page;

	private Zjut_organization_resourceDomain zjut_organization_resourceDomain;

	private Zjut_resourcesDomain zjut_resourceDomain;

	private Zjut_organizationDomain zjut_organizationDomain;

	private Zjut_resource_typeDomain zjut_resource_typeDomain;

	public abstract Zjut_organizationDomain getOrganizationDomain();

	public List typeList;

	public String tree() {
		typeList = (new Zjut_resource_typeDomain()).selectAll();
		return "manager/defaultResources/tree.html";
	}
	
	public String main(){
		return "manager/defaultResources/main.html";
	}

	public String list() {
		if (zjut_resource_typeDomain == null
				|| zjut_resource_typeDomain.getId() == null) {
			zjut_resource_typeDomain = new Zjut_resource_typeDomain();
			zjut_resource_typeDomain.setName("default");
			zjut_resource_typeDomain.loadEqual();
		}
		
		zjut_organization_resourceDomain = new Zjut_organization_resourceDomain();
		zjut_organization_resourceDomain
				.setOrganizationDomain(getOrganizationDomain());
		zjut_organization_resourceDomain
				.setResourceTypeDomain(zjut_resource_typeDomain);

		page = zjut_organization_resourceDomain.getSelectPage(page);
		return "manager/defaultResources/list.html";
	}

	public String edit() {
		if (zjut_resourceDomain == null || zjut_resourceDomain.getId() == null) {
			throw new RuntimeException("缺少资源编号，不能修改!");
		}
		zjut_resourceDomain.loadEqual();
		
		zjut_resource_typeDomain = new Zjut_resource_typeDomain();
		zjut_resource_typeDomain.setId(zjut_resourceDomain.getTypeDomain().getId());
		zjut_resourceDomain.loadEqual();
		
		zjut_organizationDomain = new Zjut_organizationDomain();
		BeanHelper.copy(getOrganizationDomain(), zjut_organizationDomain);
		return "manager/defaultResources/edit.html";
	}

	public String left() {
		if (zjut_resourceDomain.getId() == null) {
			throw new RuntimeException("缺少资源的信息，不能查看详情!");
		}

		zjut_resourceDomain.loadEqual();
		return "manager/defaultResources/left.html";
	}

	public String input() {
		if(zjut_resource_typeDomain == null || zjut_resource_typeDomain.getId() == null){
			throw new RuntimeException("缺少资源类型，不能新增!");
		}
		zjut_resource_typeDomain.loadEqual();
		
		zjut_organizationDomain = new Zjut_organizationDomain();
		BeanHelper.copy(getOrganizationDomain(), zjut_organizationDomain);
		
		zjut_resourceDomain = new Zjut_resourcesDomain();
		zjut_resourceDomain.setTypeDomain(zjut_resource_typeDomain);
		return "manager/defaultResources/edit.html";
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}

	public Zjut_organization_resourceDomain getZjut_organization_resourceDomain() {
		return zjut_organization_resourceDomain;
	}

	public void setZjut_organization_resourceDomain(
			Zjut_organization_resourceDomain zjut_organization_resourceDomain) {
		this.zjut_organization_resourceDomain = zjut_organization_resourceDomain;
	}

	public Zjut_organizationDomain getZjut_organizationDomain() {
		return zjut_organizationDomain;
	}

	public void setZjut_organizationDomain(
			Zjut_organizationDomain zjut_organizationDomain) {
		this.zjut_organizationDomain = zjut_organizationDomain;
	}

	public Zjut_resourcesDomain getZjut_resourceDomain() {
		return zjut_resourceDomain;
	}

	public void setZjut_resourceDomain(Zjut_resourcesDomain zjut_resourceDomain) {
		this.zjut_resourceDomain = zjut_resourceDomain;
	}

	public Zjut_resource_typeDomain getZjut_resource_typeDomain() {
		return zjut_resource_typeDomain;
	}

	public void setZjut_resource_typeDomain(
			Zjut_resource_typeDomain zjut_resource_typeDomain) {
		this.zjut_resource_typeDomain = zjut_resource_typeDomain;
	}

	public List getTypeList() {
		return typeList;
	}

	public void setTypeList(List typeList) {
		this.typeList = typeList;
	}

//	public static void main(String[] ben) {
//		Zjut_resourcesDomain zjut_resourceDomain = null;
//
//		Zjut_organizationDomain zjut_organizationDomain = null;
//
//		Zjut_resource_typeDomain zjut_resource_typeDomain = null;
//
//		Zjut_organization_resourceDomain zjut_organization_resourceDomain = null;
//
//		IbatisPage page = null;
//		zjut_resource_typeDomain = new Zjut_resource_typeDomain();
//		zjut_resource_typeDomain.setName("course");
//		zjut_resource_typeDomain.loadEqual();
//		zjut_organizationDomain = new Zjut_organizationDomain();
//		zjut_organizationDomain.setName("college");
//		zjut_organizationDomain.loadEqual();
//		if (zjut_resource_typeDomain == null
//				|| zjut_resource_typeDomain.getId() == null) {
//			throw new RuntimeException("缺少资源类型！");
//		}
//		zjut_organization_resourceDomain = new Zjut_organization_resourceDomain();
//		zjut_organization_resourceDomain
//				.setOrganizationDomain(zjut_organizationDomain);
//		zjut_organization_resourceDomain
//				.setResourceTypeDomain(zjut_resource_typeDomain);
//
//		page = zjut_organization_resourceDomain.getSelectPage(page);
//		System.out.println(page.getTotal());
//	}

}
