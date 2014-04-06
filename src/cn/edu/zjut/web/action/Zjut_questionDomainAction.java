package cn.edu.zjut.web.action;

import java.util.Date;

import org.sevenstar.app.common.context.ApplicationContext;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;

import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_questionDomain;
import cn.edu.zjut.cfg.domain.Zjut_questionDomain;
import cn.edu.zjut.cfg.service.Zjut_questionService;

@SSAction(name = "zjut_question")
public class Zjut_questionDomainAction extends DefaultAction {

	private Zjut_questionDomain zjut_questionDomain;

	private Zjut_organizationDomain zjut_organizationDomain;

	public String delete() {
		if (zjut_questionDomain == null || zjut_questionDomain.getId() == null) {
			throw new RuntimeException("缺少参数，不能删除！");
		}

		zjut_questionDomain.loadEqual();
		Zjut_organization_questionDomain zjut_organization_questionDomain = new Zjut_organization_questionDomain();
		zjut_organization_questionDomain.setQuestionDomain(zjut_questionDomain);
		zjut_organization_questionDomain.delete();
		zjut_questionDomain.delete();
		
		Zjut_questionService.instance.reload();

		return "删除问题成功!";
	}

	public String insertOrUpdate() {
		if (zjut_questionDomain == null || zjut_organizationDomain == null
				|| zjut_organizationDomain.getId() == null) {
			throw new RuntimeException("缺少参数，不能提交问题！");
		}

		Zjut_organization_questionDomain zjut_organization_questionDomain = null;
		if (zjut_questionDomain.getId() == null) {
			zjut_organization_questionDomain = new Zjut_organization_questionDomain();
			zjut_organizationDomain.loadEqual();
			zjut_organization_questionDomain
					.setOrganizationDomain(zjut_organizationDomain);
			zjut_organization_questionDomain.setModify_date(new Date());

		} else {
			zjut_questionDomain.setModifyUserDomain(ApplicationContext.get());
		}

		zjut_questionDomain.setModify_date(new Date());
		zjut_questionDomain.setSts("Y");
		zjut_questionDomain.insertOrUpdate();
		if (zjut_organization_questionDomain != null) {
			zjut_organization_questionDomain
					.setQuestionDomain(zjut_questionDomain);
			zjut_organization_questionDomain.insertOrUpdate();
		}
		
		Zjut_questionService.instance.reload();

		return "提交问题成功!";
	}

	public Zjut_questionDomain getZjut_questionDomain() {
		return zjut_questionDomain;
	}

	public void setZjut_questionDomain(Zjut_questionDomain zjut_questionDomain) {
		this.zjut_questionDomain = zjut_questionDomain;
	}

	public Zjut_organizationDomain getZjut_organizationDomain() {
		return zjut_organizationDomain;
	}

	public void setZjut_organizationDomain(
			Zjut_organizationDomain zjut_organizationDomain) {
		this.zjut_organizationDomain = zjut_organizationDomain;
	}

}
