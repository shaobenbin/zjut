package cn.edu.zjut.web.page.manager;

import java.util.List;

import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.util.BeanHelper;
import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_newDomain;
import cn.edu.zjut.cfg.domain.Zjut_new_typeDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_newDomain;
import cn.edu.zjut.util.ImagePathAnalyse;

public abstract class DefaultNews extends DefaultAction {
	private IbatisPage page;

	private Zjut_newDomain zjut_newDomain;
	private List newList;

	private Zjut_new_typeDomain zjut_new_typeDomain;

	private Zjut_organization_newDomain zjut_organization_newDomain;

	private Zjut_organizationDomain zjut_organizationDomain;

	private List imageList;

	public abstract Zjut_organizationDomain getOrganizationDomain();

	public abstract String getSource();

	/**
	 * 2012.3.27修改，加入图片类型的帅选
	 * 
	 * @return
	 */
	public String list() {
		if (zjut_new_typeDomain != null && zjut_new_typeDomain.getName() != null) {
			zjut_new_typeDomain.loadEqual();
		} else {
			zjut_new_typeDomain = new Zjut_new_typeDomain();
			zjut_new_typeDomain.setName("common");
			zjut_new_typeDomain.loadEqual();
		}
		zjut_organization_newDomain = new Zjut_organization_newDomain();
		zjut_organization_newDomain.setOrganizationDomain(this
				.getOrganizationDomain());
		zjut_organization_newDomain.setNewTypeDomain(zjut_new_typeDomain);
		page = zjut_organization_newDomain.getSelectPage(page);
		return "manager/defaultnews/list.html";
	}

	public String edit() {
		if (zjut_newDomain == null || zjut_newDomain.getId() == null) {
			throw new RuntimeException("缺少新闻编号，不能修改!");
		}

		newList = (new Zjut_new_typeDomain()).selectAll();
		zjut_newDomain.loadEqual();
		imageList = ImagePathAnalyse.instance.analyseImagePaths(zjut_newDomain
				.getContext());
		return "manager/defaultnews/edit.html";
	}

	public String left() {
		if (zjut_newDomain.getId() == null) {
			throw new RuntimeException("缺少新闻的信息，不能查看详情!");
		}
		zjut_newDomain.loadEqual();
		return "manager/defaultnews/left.html";
	}

	public String input() {
		newList = (new Zjut_new_typeDomain()).selectAll();
		zjut_organizationDomain = new Zjut_organizationDomain();
		BeanHelper.copy(this.getOrganizationDomain(), zjut_organizationDomain);
		zjut_newDomain = new Zjut_newDomain();
		zjut_newDomain.setSource(getSource());
		return "manager/defaultnews/edit.html";
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}

	public Zjut_newDomain getZjut_newDomain() {
		return zjut_newDomain;
	}

	public void setZjut_newDomain(Zjut_newDomain zjut_newDomain) {
		this.zjut_newDomain = zjut_newDomain;
	}

	public Zjut_organizationDomain getZjut_organizationDomain() {
		return zjut_organizationDomain;
	}

	public void setZjut_organizationDomain(
			Zjut_organizationDomain zjut_organizationDomain) {
		this.zjut_organizationDomain = zjut_organizationDomain;
	}

	public List getImageList() {
		return imageList;
	}

	public void setImageList(List imageList) {
		this.imageList = imageList;
	}

	public Zjut_new_typeDomain getZjut_new_typeDomain() {
		return zjut_new_typeDomain;
	}

	public void setZjut_new_typeDomain(Zjut_new_typeDomain zjut_new_typeDomain) {
		this.zjut_new_typeDomain = zjut_new_typeDomain;
	}

	public Zjut_organization_newDomain getZjut_organization_newDomain() {
		return zjut_organization_newDomain;
	}

	public void setZjut_organization_newDomain(
			Zjut_organization_newDomain zjut_organization_newDomain) {
		this.zjut_organization_newDomain = zjut_organization_newDomain;
	}

	public List getNewList() {
		return newList;
	}

	public void setNewList(List newList) {
		this.newList = newList;
	}

}
