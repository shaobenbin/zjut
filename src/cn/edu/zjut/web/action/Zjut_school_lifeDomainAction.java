package cn.edu.zjut.web.action;

import java.util.Date;

import org.sevenstar.app.common.context.ApplicationContext;
import org.sevenstar.persistent.db.transaction.TransactionFactory;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;

import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_lifeDomain;
import cn.edu.zjut.cfg.domain.Zjut_school_lifeDomain;
import cn.edu.zjut.cfg.service.Zjut_school_lifeService;

@SSAction(name = "zjut_school_life")
public class Zjut_school_lifeDomainAction extends DefaultAction {
	private Zjut_school_lifeDomain zjut_school_lifeDomain;

	private Zjut_organizationDomain zjut_organizationDomain;

	private Zjut_organization_lifeDomain zjut_organization_lifeDomain;

	public String delete() {
		if (zjut_school_lifeDomain == null
				|| zjut_school_lifeDomain.getId() == null) {
			throw new RuntimeException("缺少参数，不能删除数据!");
		}

		zjut_school_lifeDomain.loadEqual();

		Zjut_organization_lifeDomain organizationLifeDomain = new Zjut_organization_lifeDomain();
		organizationLifeDomain.setSchoolLifeDomain(zjut_school_lifeDomain);
		organizationLifeDomain.loadEqual();

		try {
			TransactionFactory.startTransaction();
			organizationLifeDomain.delete();
			zjut_school_lifeDomain.delete();
			TransactionFactory.commitTransaction();
		} finally {
			TransactionFactory.endTransaction();
		}

		Zjut_school_lifeService.instance.reload();
		return "删除成功";
	}

	public String insertOrUpdate() {
		if (zjut_school_lifeDomain == null || zjut_organizationDomain == null
				|| zjut_organizationDomain.getId() == null) {
			throw new RuntimeException("缺少参数，不能编辑数据!");
		}

		zjut_organizationDomain.loadEqual();

		try {
			TransactionFactory.startTransaction();
			Zjut_organization_lifeDomain organizationLifeDomain = null;
			if (zjut_school_lifeDomain.getId() == null) {
				organizationLifeDomain = new Zjut_organization_lifeDomain();
			}

			zjut_school_lifeDomain.setModify_date(new Date());
			zjut_school_lifeDomain
					.setModifyUserDomain(ApplicationContext.get());

			zjut_school_lifeDomain.insertOrUpdate();

			if (organizationLifeDomain != null) {
				organizationLifeDomain
						.setSchoolLifeDomain(zjut_school_lifeDomain);
				organizationLifeDomain
						.setOrganizationDomain(zjut_organizationDomain);
				organizationLifeDomain.setModify_date(new Date());
				organizationLifeDomain.setModifyUserDomain(ApplicationContext
						.get());
				organizationLifeDomain.insertOrUpdate();
			}
			TransactionFactory.commitTransaction();
		} finally {
			TransactionFactory.endTransaction();
		}
		Zjut_school_lifeService.instance.reload();
		return "编辑成功";
	}

	public Zjut_school_lifeDomain getZjut_school_lifeDomain() {
		return zjut_school_lifeDomain;
	}

	public void setZjut_school_lifeDomain(
			Zjut_school_lifeDomain zjut_school_lifeDomain) {
		this.zjut_school_lifeDomain = zjut_school_lifeDomain;
	}

	public Zjut_organizationDomain getZjut_organizationDomain() {
		return zjut_organizationDomain;
	}

	public void setZjut_organizationDomain(
			Zjut_organizationDomain zjut_organizationDomain) {
		this.zjut_organizationDomain = zjut_organizationDomain;
	}

	public Zjut_organization_lifeDomain getZjut_organization_lifeDomain() {
		return zjut_organization_lifeDomain;
	}

	public void setZjut_organization_lifeDomain(
			Zjut_organization_lifeDomain zjut_organization_lifeDomain) {
		this.zjut_organization_lifeDomain = zjut_organization_lifeDomain;
	}

}
