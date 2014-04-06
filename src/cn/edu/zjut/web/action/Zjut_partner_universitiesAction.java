package cn.edu.zjut.web.action;

import java.util.Date;

import org.sevenstar.app.common.context.ApplicationContext;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;

import cn.edu.zjut.cfg.domain.Zjut_partner_universitiesDomain;
import cn.edu.zjut.cfg.service.Zjut_partner_universitiesService;

@SSAction(name = "zjut_partner_universities")
public class Zjut_partner_universitiesAction extends DefaultAction {
	private Zjut_partner_universitiesDomain partner_universitiesDomain;

	public String delete() {
		if (partner_universitiesDomain == null
				|| partner_universitiesDomain.getId() == null) {
			throw new RuntimeException("缺少必要的参数，不能删除!");
		}

		partner_universitiesDomain.loadEqual();
		partner_universitiesDomain.delete();
		Zjut_partner_universitiesService.instance.load();
		return "删除[" + partner_universitiesDomain.getName() + "]成功!";
	}

	public String insertOrUpdate() {
		if (partner_universitiesDomain == null) {
			throw new RuntimeException("缺少必要的参数，不能编辑!");
		}
		if (partner_universitiesDomain.getUrl().indexOf("http://") >= 0) {
			partner_universitiesDomain.setUrl(partner_universitiesDomain
					.getUrl().replace("http://", ""));
		}
		partner_universitiesDomain.setModify_date(new Date());
		partner_universitiesDomain
				.setModifyUserDomain(ApplicationContext.get());
		partner_universitiesDomain.insertOrUpdate();
		Zjut_partner_universitiesService.instance.load();
		return "编辑[" + partner_universitiesDomain.getName() + "]成功";
	}

	public Zjut_partner_universitiesDomain getPartner_universitiesDomain() {
		return partner_universitiesDomain;
	}

	public void setPartner_universitiesDomain(
			Zjut_partner_universitiesDomain partner_universitiesDomain) {
		this.partner_universitiesDomain = partner_universitiesDomain;
	}

}
