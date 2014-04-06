package cn.edu.zjut.web.action;

import java.util.Date;

import org.sevenstar.app.common.context.ApplicationContext;
import org.sevenstar.persistent.db.transaction.TransactionFactory;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;

import cn.edu.zjut.cfg.domain.Zjut_bulletinDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_bulletinDomain;
import cn.edu.zjut.cfg.service.Zjut_bulletinService;

@SSAction(name = "zjut_bulletins")
public class Zjut_bulletinsDomainAction extends DefaultAction {
	private Zjut_bulletinDomain zjut_bulletinDomain;
	private Zjut_organizationDomain zjut_organizationDomain;

	public Zjut_bulletinDomain getZjut_bulletinDomain() {
		return zjut_bulletinDomain;
	}

	public void setZjut_bulletinDomain(Zjut_bulletinDomain zjut_bulletinDomain) {
		this.zjut_bulletinDomain = zjut_bulletinDomain;
	}

	public Zjut_organizationDomain getZjut_organizationDomain() {
		return zjut_organizationDomain;
	}

	public void setZjut_organizationDomain(
			Zjut_organizationDomain zjut_organizationDomain) {
		this.zjut_organizationDomain = zjut_organizationDomain;
	}

	public String delete() {
		if (zjut_bulletinDomain == null
				|| zjut_bulletinDomain.getId() == null) {
			throw new RuntimeException("缺少公告主键，不能进行删除操作!");
		}

		zjut_bulletinDomain.loadEqual();

		Zjut_organization_bulletinDomain zjut_organization_bulletinDomain = new Zjut_organization_bulletinDomain();
		zjut_organization_bulletinDomain.setBulletinDomain(zjut_bulletinDomain);
		zjut_organization_bulletinDomain.loadEqual();

		try {
			TransactionFactory.startTransaction();
			zjut_organization_bulletinDomain.delete();
			zjut_bulletinDomain.delete();
			TransactionFactory.commitTransaction();
		} finally {
			TransactionFactory.endTransaction();
		}
		Zjut_bulletinService.instance.reload();
		return "删除成功!";
	}

	public String insertOrUpdate() {
		Zjut_organization_bulletinDomain zjut_organization_bulletinsDomain = null;
		String context = zjut_bulletinDomain.getContext();
		if (context.indexOf("'") >= 0) {
			zjut_bulletinDomain.setContext(context.replaceAll("'", "‘"));
		}
		if (zjut_bulletinDomain.getId() == null) {
			if (zjut_organizationDomain == null
					|| zjut_organizationDomain.getId() == null) {
				throw new RuntimeException("缺少组织信息，不能新增新闻!");
			}
			zjut_organizationDomain.loadEqual();
			zjut_organization_bulletinsDomain = new Zjut_organization_bulletinDomain();
			zjut_organization_bulletinsDomain
					.setOrganizationDomain(zjut_organizationDomain);
			zjut_bulletinDomain.setCreate_date(new Date());
			zjut_bulletinDomain.setCreateUserDomain(ApplicationContext.get());
		}
		zjut_bulletinDomain.setModify_date(new Date());
		zjut_bulletinDomain.setModifyUserDomain(ApplicationContext.get());
		if(!"Y".equalsIgnoreCase(zjut_bulletinDomain.getIs_en())){
			zjut_bulletinDomain.setIs_en("N");
		}
		zjut_bulletinDomain.insertOrUpdate();

		if (zjut_organization_bulletinsDomain != null) {
			zjut_organization_bulletinsDomain.setBulletinDomain(zjut_bulletinDomain);
			zjut_organization_bulletinsDomain.setCreate_date(new Date());
			zjut_organization_bulletinsDomain.setModify_date(new Date());
			zjut_organization_bulletinsDomain.setIs_en(zjut_bulletinDomain.getIs_en());
			zjut_organization_bulletinsDomain
					.setCreateUserDomain(ApplicationContext.get());
			zjut_organization_bulletinsDomain
					.setModifyUserDomain(ApplicationContext.get());
			zjut_organization_bulletinsDomain.insert();
		}
		
		Zjut_bulletinService.instance.reload();
		return "编辑成功!";
	}
}
