package cn.edu.zjut.web.page.manager;

import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_resource_typeDomain;

public class Resourcetype extends DefaultAction {
	private Zjut_resource_typeDomain zjut_resource_typeDomain;

	private IbatisPage page;

	public String list() {
		page = (new Zjut_resource_typeDomain()).getSelectPage(page);
		return super.SUCCESS;
	}

	public String edit() {
		if (zjut_resource_typeDomain == null
				|| zjut_resource_typeDomain.getId() == null) {
			throw new RuntimeException("缺少参数，不能编辑");
		}
		zjut_resource_typeDomain.loadEqual();
		return super.SUCCESS;
	}

	public String left() {
		if (zjut_resource_typeDomain == null
				|| zjut_resource_typeDomain.getId() == null) {
			throw new RuntimeException("缺少参数，不能查看!");
		}

		zjut_resource_typeDomain.loadEqual();
		return super.SUCCESS;
	}

	public String input() {
		return "/manager/resourcetype/edit.html";
	}

	public Zjut_resource_typeDomain getZjut_resource_typeDomain() {
		return zjut_resource_typeDomain;
	}

	public void setZjut_resource_typeDomain(
			Zjut_resource_typeDomain zjut_resource_typeDomain) {
		this.zjut_resource_typeDomain = zjut_resource_typeDomain;
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}

}
