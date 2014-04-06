package cn.edu.zjut.web.action;

import java.util.Date;

import org.sevenstar.app.common.context.ApplicationContext;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;

import cn.edu.zjut.cfg.domain.Zjut_resource_typeDomain;

@SSAction(name = "zjut_resource_type")
public class Zjut_resource_typeDomainAction extends DefaultAction {
	private Zjut_resource_typeDomain zjut_resource_typeDomain;

	public String delete() {
		if (zjut_resource_typeDomain == null
				|| zjut_resource_typeDomain.getId() == null) {
			throw new RuntimeException("缺少参数，不能删除!");
		}
		zjut_resource_typeDomain.loadEqual();
		zjut_resource_typeDomain.delete();
		return "删除" + zjut_resource_typeDomain.getName() + "成功!";
	}

	public String insertOrUpdate() {
		if (zjut_resource_typeDomain == null) {
			throw new RuntimeException("缺少参数，不能新增!");
		}
		zjut_resource_typeDomain.setModify_date(new Date());
		zjut_resource_typeDomain.setModifyUserDomain(ApplicationContext.get());
		zjut_resource_typeDomain.insertOrUpdate();

		return "添加" + zjut_resource_typeDomain.getName() + "成功!";
	}

	public Zjut_resource_typeDomain getZjut_resource_typeDomain() {
		return zjut_resource_typeDomain;
	}

	public void setZjut_resource_typeDomain(
			Zjut_resource_typeDomain zjut_resource_typeDomain) {
		this.zjut_resource_typeDomain = zjut_resource_typeDomain;
	}

}
