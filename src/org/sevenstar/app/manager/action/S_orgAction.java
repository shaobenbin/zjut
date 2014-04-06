package org.sevenstar.app.manager.action;

import java.util.List;

import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.util.BeanHelper;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;
import org.sevenstar.web.annotation.SSList;
import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.manager.domain.S_orgDomain;
import org.sevenstar.app.manager.service.S_orgService;

@SSAction(name = "s_org")
public class S_orgAction extends DefaultAction {
	private S_orgDomain s_orgDomain;
	private S_orgService s_orgService = new S_orgService();

	@SSList(type = "org.sevenstar.app.manager.domain.S_orgDomain")
	private List childOrgList;

	private IbatisPage page;

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}

	public IbatisPage query() {
		if (s_orgDomain == null) {
			s_orgDomain = new S_orgDomain();
		}
		page = s_orgDomain.getSelectPage(page);
		List dataList = page.getDataList();
		if (dataList != null && dataList.size() > 0) {
			for (int i = 0; i < dataList.size(); i++) {
				S_orgDomain orgDomain = (S_orgDomain) dataList.get(i);
				S_orgDomain newOrgDomain = new S_orgDomain();
				BeanHelper.copyNotNullPrimitiveProperties(orgDomain,
						newOrgDomain);
				dataList.set(i, newOrgDomain);
			}
		}
		return page;
	}

	public String deleteChildList() throws Exception {
		if (childOrgList == null || childOrgList.size() == 0) {
			throw new ApplicationException("请选择部门");
		}
 		for (int i = 0; i < childOrgList.size(); i++) {
			S_orgDomain domain = (S_orgDomain) childOrgList.get(i);
 			domain.delete();
		}
		return "批量删除成功";
	}
 

	public String insert() throws Exception {
		s_orgDomain.insert();
		super.setMsg("添加部门[" + s_orgDomain.getName() + "]成功");
		if (s_orgDomain.getUpDomain() != null
				&& s_orgDomain.getUpDomain().getId() != null) {
			super.setNextUrl("app/manager/org/list.html?s_orgDomain.id="
					+ s_orgDomain.getUpDomain().getId());
		} else {
			super.setNextUrl("app/manager/org/list.html?s_orgDomain.id="
					+ s_orgDomain.getId());
		}
		return super.REDIRECT;
	}

	public String update() throws Exception {
		s_orgDomain.update();
		super.setMsg("修改部门[" + s_orgDomain.getName() + "]成功");
		super.setNextUrl("app/manager/org/view.html?s_orgDomain.id="
				+ s_orgDomain.getId());
		return super.REDIRECT;
	}

	public String updatechild() throws Exception {
		s_orgDomain.update();
		super.setMsg("修改部门[" + s_orgDomain.getName() + "]成功");
		super.setNextUrl("app/manager/org/list.html?s_orgDomain.id="
				+ s_orgDomain.getUpDomain().getId());
		return super.REDIRECT;
	}

	public String insertOrUpdate() throws Exception {
		s_orgDomain.insertOrUpdate();
		return "编辑部门[" + s_orgDomain.getName() + "]成功";
	}

	public S_orgDomain getS_orgDomain() {
		return s_orgDomain;
	}

	public void setS_orgDomain(S_orgDomain s_orgDomain) {
		this.s_orgDomain = s_orgDomain;
	}

	public S_orgService getS_orgService() {
		return s_orgService;
	}

	public void setS_orgService(S_orgService s_orgService) {
		this.s_orgService = s_orgService;
	}

	public List getChildOrgList() {
		return childOrgList;
	}

	public void setChildOrgList(List childOrgList) {
		this.childOrgList = childOrgList;
	}

}
