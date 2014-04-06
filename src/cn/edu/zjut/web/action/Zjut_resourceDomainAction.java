package cn.edu.zjut.web.action;

import java.util.Date;

import org.sevenstar.app.common.context.ApplicationContext;
import org.sevenstar.persistent.db.transaction.TransactionFactory;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;

import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_resourceDomain;
import cn.edu.zjut.cfg.domain.Zjut_resource_typeDomain;
import cn.edu.zjut.cfg.domain.Zjut_resourcesDomain;

@SSAction(name = "zjut_resource")
public class Zjut_resourceDomainAction extends DefaultAction {
	private Zjut_resourcesDomain zjut_resourceDomain;
	private Zjut_organizationDomain zjut_organizationDomain;

	private Zjut_resource_typeDomain zjut_resource_typeDomain;

	private String filedata;

	public Zjut_resourcesDomain getZjut_resourceDomain() {
		return zjut_resourceDomain;
	}

	public void setZjut_resourceDomain(Zjut_resourcesDomain zjut_resourceDomain) {
		this.zjut_resourceDomain = zjut_resourceDomain;
	}

	public Zjut_organizationDomain getZjut_organizationDomain() {
		return zjut_organizationDomain;
	}

	public void setZjut_organizationDomain(
			Zjut_organizationDomain zjut_organizationDomain) {
		this.zjut_organizationDomain = zjut_organizationDomain;
	}

	public String delete() {
		if (zjut_resourceDomain == null || zjut_resourceDomain.getId() == null) {
			throw new RuntimeException("缺少资源编号，不能删除!");
		}

		zjut_resourceDomain.loadEqual();

		Zjut_organization_resourceDomain zor = new Zjut_organization_resourceDomain();
		zor.setResourceDomain(zjut_resourceDomain);
		try {
			TransactionFactory.startTransaction();
			zor.loadEqual();
			zor.delete();
			zjut_resourceDomain.delete();
			TransactionFactory.commitTransaction();
		} finally {
			TransactionFactory.endTransaction();
		}

		return "删除[" + zjut_resourceDomain.getName() + "]成功";

	}

	public String insertOrUpdate() {
		Zjut_organization_resourceDomain zjut_organization_resouceDomain = null;
		Zjut_organization_resourceDomain zjut_organization_resourceDomain = new Zjut_organization_resourceDomain();
		if (zjut_resourceDomain.getId() == null) {

			if (zjut_organizationDomain == null
					|| zjut_organizationDomain.getId() == null) {
				throw new RuntimeException("缺少组织信息，不能新增新闻!");
			}
			zjut_organizationDomain.loadEqual();
			zjut_organization_resouceDomain = new Zjut_organization_resourceDomain();
			zjut_organization_resouceDomain
					.setOrganizationDomain(zjut_organizationDomain);
		}
		zjut_resourceDomain.setModify_date(new Date());
		zjut_resourceDomain.setModifyUserDomain(ApplicationContext.get());
		zjut_resourceDomain.insertOrUpdate();
		if (zjut_organization_resouceDomain != null) {
			zjut_organization_resouceDomain
					.setResourceDomain(zjut_resourceDomain);
			zjut_organization_resouceDomain.setResourceTypeDomain(zjut_resourceDomain.getTypeDomain());
			zjut_organization_resouceDomain.setModify_date(new Date());
			zjut_organization_resouceDomain
					.setModifyUserDomain(ApplicationContext.get());
			zjut_organization_resouceDomain.insertOrUpdate();
		}

		return "编辑成功!";

	}

	public Zjut_resource_typeDomain getZjut_resource_typeDomain() {
		return zjut_resource_typeDomain;
	}

	public void setZjut_resource_typeDomain(
			Zjut_resource_typeDomain zjut_resource_typeDomain) {
		this.zjut_resource_typeDomain = zjut_resource_typeDomain;
	}

	public String getFiledata() {
		return filedata;
	}

	public void setFiledata(String filedata) {
		this.filedata = filedata;
	}

}
