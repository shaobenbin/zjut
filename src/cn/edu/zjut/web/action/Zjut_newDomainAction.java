package cn.edu.zjut.web.action;

import java.util.Date;
import java.util.List;

import org.sevenstar.app.common.context.ApplicationContext;
import org.sevenstar.persistent.db.transaction.TransactionFactory;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;

import cn.edu.zjut.cfg.domain.Zjut_newDomain;
import cn.edu.zjut.cfg.domain.Zjut_new_typeDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_newDomain;
import cn.edu.zjut.cfg.service.Zjut_newService;
import cn.edu.zjut.util.ImagePathAnalyse;

@SSAction(name = "zjut_new")
public class Zjut_newDomainAction extends DefaultAction {
	private Zjut_newDomain zjut_newDomain;
	private Zjut_organizationDomain zjut_organizationDomain;
	private Zjut_new_typeDomain zjut_new_typeDomain;

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

	public String delete() {
		if (zjut_newDomain == null || zjut_newDomain.getId() == null) {
			throw new RuntimeException("缺少新闻主键，不能进行删除操作!");
		}

		zjut_newDomain.loadEqual();

		Zjut_organization_newDomain zjut_organization_newDomain = new Zjut_organization_newDomain();
		zjut_organization_newDomain.setNewDomain(zjut_newDomain);
		zjut_organization_newDomain.loadEqual();

		try {
			TransactionFactory.startTransaction();
			zjut_organization_newDomain.delete();
			zjut_newDomain.delete();
			TransactionFactory.commitTransaction();
		} finally {
			TransactionFactory.endTransaction();
		}
		Zjut_newService.instance.reload();
		return "删除成功!";
	}

	public String insertOrUpdate() {
		Zjut_organization_newDomain zjut_organization_newDomain = null;
		String context = zjut_newDomain.getContext();
		if (context.indexOf("'") >= 0) {
			zjut_newDomain.setContext(context.replaceAll("'", "‘"));
		}

		if (zjut_new_typeDomain.getId() == null
				|| zjut_new_typeDomain.getId() == null) {
			throw new RuntimeException("缺少新闻类型!");
		}
		zjut_new_typeDomain.loadEqual();

		if (zjut_newDomain.getId() == null) {
			if (zjut_organizationDomain == null
					|| zjut_organizationDomain.getId() == null) {
				throw new RuntimeException("缺少组织信息，不能新增新闻!");
			}
			zjut_organizationDomain.loadEqual();
			zjut_organization_newDomain = new Zjut_organization_newDomain();
			zjut_organization_newDomain
					.setOrganizationDomain(zjut_organizationDomain);
			zjut_newDomain.setCreate_date(new Date());
			zjut_newDomain.setCreateUserDomain(ApplicationContext.get());
		}

		// 没有设置image2index图片的情况下默认第一张为首页图片
		if (zjut_newDomain.getImage2index() == null
				|| "".equals(zjut_newDomain.getImage2index())) {
			List imageList = ImagePathAnalyse.instance
					.analyseImagePaths(zjut_newDomain.getContext());
			if (imageList != null && !imageList.isEmpty()) {
				zjut_newDomain.setImage2index(String.valueOf(imageList.get(0)));
			}
		}

		zjut_newDomain.setNewTypeDomain(zjut_new_typeDomain);
		zjut_newDomain.setModify_date(new Date());
		zjut_newDomain.setModifyUserDomain(ApplicationContext.get());
		zjut_newDomain.insertOrUpdate();

		if (zjut_organization_newDomain != null) {
			zjut_organization_newDomain.setNewDomain(zjut_newDomain);
			zjut_organization_newDomain.setCreate_date(new Date());
			zjut_organization_newDomain.setModify_date(new Date());
			zjut_organization_newDomain.setCreateUserDomain(ApplicationContext
					.get());
			zjut_organization_newDomain.setModifyUserDomain(ApplicationContext
					.get());
			zjut_organization_newDomain.setNewTypeDomain(zjut_new_typeDomain);
			zjut_organization_newDomain.insert();
		}else{
			zjut_organization_newDomain = new Zjut_organization_newDomain();
			zjut_organization_newDomain.setNewDomain(zjut_newDomain);
			zjut_organization_newDomain.loadEqual();
			zjut_organization_newDomain.setNewTypeDomain(zjut_new_typeDomain);
			zjut_organization_newDomain.update();
		}
		Zjut_newService.instance.reload();
		return "编辑成功!";
	}

	public Zjut_new_typeDomain getZjut_new_typeDomain() {
		return zjut_new_typeDomain;
	}

	public void setZjut_new_typeDomain(Zjut_new_typeDomain zjut_new_typeDomain) {
		this.zjut_new_typeDomain = zjut_new_typeDomain;
	}

}
