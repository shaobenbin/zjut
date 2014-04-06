package org.sevenstar.app.page.app;

import java.util.List;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.module.domain.S_moduleDomain;
import org.sevenstar.app.system.domain.S_systemDomain;
import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.web.action.java.DefaultAction;

/**
 * @author rtm 2008-5-8
 */
public class Module extends DefaultAction {

	private S_moduleDomain s_moduleDomain;

	private IbatisPage page = null;

	public String edit() {
		if (s_moduleDomain == null || s_moduleDomain.getId() == null) {
			throw new ApplicationException("请先选择模块");
		}
		s_moduleDomain.load();
		systemList = (new S_systemDomain()).selectAll();
		return super.SUCCESS;
	}

	public String list() {
		if (s_moduleDomain == null || s_moduleDomain.getSystemDomain() == null
				|| s_moduleDomain.getSystemDomain().getId() == null) {
			throw new ApplicationException("请先选择系统");
		}
		s_moduleDomain.getSystemDomain().loadEqual();
		page = s_moduleDomain.getSelectPage(page);
		return super.SUCCESS;
	}

	public String left() {
		if (s_moduleDomain == null || s_moduleDomain.getId() == null) {
			return welcome();
		}
		s_moduleDomain.load();
		return super.SUCCESS;
	}

	public String welcome() {
		return "app/module/welcome.html";
	}

	private List moduleList;

	private List systemList;

	public String tree() {
		moduleList = (new S_moduleDomain()).selectAll();
		systemList = (new S_systemDomain()).selectAll();
		return super.SUCCESS;
	}

	public String input() {
		if (s_moduleDomain != null && s_moduleDomain.getUpDomain() != null
				&& s_moduleDomain.getUpDomain().getId() != null) {
			s_moduleDomain.getUpDomain().load();
			if (s_moduleDomain.getUpDomain().getLvl() != null) {
				s_moduleDomain.setLvl(new Long(s_moduleDomain.getUpDomain().getLvl().longValue() + 1));
			} else {
				s_moduleDomain.setLvl(new Long(1));
			}
		}
		if (s_moduleDomain == null) {
			s_moduleDomain = new S_moduleDomain();
			s_moduleDomain.setLvl(new Long(1));
		}
		systemList = (new S_systemDomain()).selectAll();
		return "app/module/edit.html";
	}

	public S_moduleDomain getS_moduleDomain() {
		return s_moduleDomain;
	}

	public void setS_moduleDomain(S_moduleDomain domain) {
		s_moduleDomain = domain;
	}

	public List getModuleList() {
		return moduleList;
	}

	public void setModuleList(List moduleList) {
		this.moduleList = moduleList;
	}

	public List getSystemList() {
		return systemList;
	}

	public void setSystemList(List systemList) {
		this.systemList = systemList;
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}

}
