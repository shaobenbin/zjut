package org.sevenstar.app.resource.action;

import java.util.List;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.resource.domain.S_resource_typeDomain;
import org.sevenstar.persistent.db.transaction.SSTransaction;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;
import org.sevenstar.web.annotation.SSList;

@SSAction(name = "s_resource_type")
public class S_resource_typeAction extends DefaultAction {
	private S_resource_typeDomain s_resource_typeDomain;

	@SSList(type = "org.sevenstar.app.resource.domain.S_resource_typeDomain")
	private List typeList;

	@SSTransaction
	public String insertOrUpdate() {
		if (s_resource_typeDomain == null) {
			throw new ApplicationException("请录入资源类型信息");
		}
		s_resource_typeDomain.insertOrUpdate();
		return "编辑资源类型[" + s_resource_typeDomain.getName() + "]成功";
	}

	@SSTransaction
	public String deleteList() {
		if (typeList == null || typeList.size() == 0) {
			throw new ApplicationException("请选择一项资源类型");
		}
		for (int i = 0; i < typeList.size(); i++) {
			S_resource_typeDomain domain = (S_resource_typeDomain) typeList
					.get(i);
			domain.delete();
		}
		return "批量删除成功";
	}

	public S_resource_typeDomain getS_resource_typeDomain() {
		return s_resource_typeDomain;
	}

	public void setS_resource_typeDomain(S_resource_typeDomain domain) {
		s_resource_typeDomain = domain;
	}

	public List getTypeList() {
		return typeList;
	}

	public void setTypeList(List typeList) {
		this.typeList = typeList;
	}

}
