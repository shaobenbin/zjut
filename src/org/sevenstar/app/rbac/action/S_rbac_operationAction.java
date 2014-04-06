package org.sevenstar.app.rbac.action;

import java.util.List;

import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;
import org.sevenstar.web.annotation.SSList;
import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.rbac.domain.S_rbac_operationDomain;

@SSAction(name = "s_rbac_operation")
public class S_rbac_operationAction extends DefaultAction {
	private S_rbac_operationDomain s_rbac_operationDomain;
	@SSList(type = "org.sevenstar.app.rbac.domain.S_rbac_operationDomain")
	private List operationList;

	public String deleteList() throws Exception {
		if (operationList != null && operationList.size() > 0) {
			for (int i = 0; i < operationList.size(); i++) {
				S_rbac_operationDomain s_rbac_operationDomain = (S_rbac_operationDomain) operationList
						.get(i);
				s_rbac_operationDomain.delete();
			}
		} else {
			throw new ApplicationException("请先选择操作");
		}
		return "批量删除操作成功";
	}

	public String delete() throws Exception {
		super.setMsg("删除操作[" + s_rbac_operationDomain.getName() + "]成功");
		s_rbac_operationDomain.delete();
		super.setNextUrl("app/rbac/operation/list.html");
		return super.REDIRECT;
	}

	public String insert() throws Exception {
		s_rbac_operationDomain.insert();
		super.setMsg("添加操作[" + s_rbac_operationDomain.getName() + "]成功");
		super.setNextUrl("app/rbac/operation/list.html");
		return super.REDIRECT;
	}

	public String update() throws Exception {
		s_rbac_operationDomain.update();
		super.setMsg("修改操作[" + s_rbac_operationDomain.getName() + "]成功");
		super.setNextUrl("app/rbac/operation/list.html");
		return super.REDIRECT;
	}

	public String insertOrUpdate() throws Exception {
		s_rbac_operationDomain.insertOrUpdate();
		return "编辑操作[" + s_rbac_operationDomain.getName() + "]成功";
	}

	public S_rbac_operationDomain getS_rbac_operationDomain() {
		return s_rbac_operationDomain;
	}

	public void setS_rbac_operationDomain(
			S_rbac_operationDomain s_rbac_operationDomain) {
		this.s_rbac_operationDomain = s_rbac_operationDomain;
	}

 

	public List getOperationList() {
		return operationList;
	}

	public void setOperationList(List operationList) {
		this.operationList = operationList;
	}

}
