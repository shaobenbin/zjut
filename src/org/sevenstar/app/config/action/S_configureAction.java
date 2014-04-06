package org.sevenstar.app.config.action;

import java.util.List;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.config.domain.S_configureDomain;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;
import org.sevenstar.web.annotation.SSList;

@SSAction(name = "s_configure")
public class S_configureAction extends DefaultAction {

	private S_configureDomain s_configureDomain;

	@SSList(type = "org.sevenstar.app.config.action.S_configureAction")
	private List configureList;

	public String deleteList() {
		if (configureList == null) {
			throw new ApplicationException("请先选中一项");
		}
		for (int i = 0; i < configureList.size(); i++) {
			S_configureDomain domain = (S_configureDomain) configureList.get(i);
			domain.delete();
		}
		return "批量删除成功";
	}

	public String insertOrUpdate() {
		if (s_configureDomain == null) {
			throw new ApplicationException("没有录入信息");
		}
		s_configureDomain.insertOrUpdate();
		return "编辑配置类型[" + s_configureDomain.getName() + "]成功";
	}

	public S_configureDomain getS_configureDomain() {
		return s_configureDomain;
	}

	public void setS_configureDomain(S_configureDomain domain) {
		s_configureDomain = domain;
	}

	public List getConfigureList() {
		return configureList;
	}

	public void setConfigureList(List configureList) {
		this.configureList = configureList;
	}

}
