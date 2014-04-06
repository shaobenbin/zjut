package cn.edu.zjut.web.action;

import java.util.Date;

import org.sevenstar.app.common.context.ApplicationContext;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;

import cn.edu.zjut.cfg.domain.Zjut_contentDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.service.Zjut_contentService;

@SSAction(name = "zjut_content")
public class Zjut_contentDomainAction extends DefaultAction {
	private Zjut_contentDomain zjut_contentDomain;

	private Zjut_organizationDomain organizationDomain;

	public String delete() {
		if (zjut_contentDomain == null || zjut_contentDomain.getId() == null) {
			throw new RuntimeException("缺少参数，不能删除");
		}
		zjut_contentDomain.loadEqual();
		zjut_contentDomain.delete();
		Zjut_contentService.instance.load();
		return "删除[" + zjut_contentDomain.getTitle() + "]成功!";
	}

	public String insertOrUpdate() {
		if (zjut_contentDomain == null
				|| zjut_contentDomain.getTypeDomain().getId() == null
				|| zjut_contentDomain.getOrganizationDomain().getId() == null) {
			throw new RuntimeException("缺少参数，不能编辑");
		}

		zjut_contentDomain.getTypeDomain().loadEqual();
		zjut_contentDomain.getOrganizationDomain().loadEqual();

		zjut_contentDomain.setModify_date(new Date());
		zjut_contentDomain.setModifyUserDomain(ApplicationContext.get());
		zjut_contentDomain.insertOrUpdate();
		Zjut_contentService.instance.load();
		return "编辑[" + zjut_contentDomain.getTitle() + "]成功!";
	}

	public Zjut_contentDomain getZjut_contentDomain() {
		return zjut_contentDomain;
	}

	public void setZjut_contentDomain(Zjut_contentDomain zjut_contentDomain) {
		this.zjut_contentDomain = zjut_contentDomain;
	}

	public Zjut_organizationDomain getOrganizationDomain() {
		return organizationDomain;
	}

	public void setOrganizationDomain(Zjut_organizationDomain organizationDomain) {
		this.organizationDomain = organizationDomain;
	}

}
