package cn.edu.zjut.web.page.manager;

import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_partner_universitiesDomain;

public class Partnerunis extends DefaultAction {
	private Zjut_partner_universitiesDomain partner_universitiesDomain;

	private IbatisPage page;

	public String list() {
		page = (new Zjut_partner_universitiesDomain()).getSelectPage(page);
		return super.SUCCESS;
	}

	public String detail() {
		if (partner_universitiesDomain == null
				|| partner_universitiesDomain.getId() == null) {
			throw new RuntimeException("缺少参数，不能查看详情!");
		}

		partner_universitiesDomain.loadEqual();
		return super.SUCCESS;
	}

	public String edit() {
		if (partner_universitiesDomain == null
				|| partner_universitiesDomain.getId() == null) {
			throw new RuntimeException("缺少参数，不能编辑!");
		}
		partner_universitiesDomain.loadEqual();
		return super.SUCCESS;
	}

	public String input() {
		partner_universitiesDomain = new Zjut_partner_universitiesDomain();
		return "manager/partnerunis/edit.html";
	}

	public Zjut_partner_universitiesDomain getPartner_universitiesDomain() {
		return partner_universitiesDomain;
	}

	public void setPartner_universitiesDomain(
			Zjut_partner_universitiesDomain partner_universitiesDomain) {
		this.partner_universitiesDomain = partner_universitiesDomain;
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}

}
