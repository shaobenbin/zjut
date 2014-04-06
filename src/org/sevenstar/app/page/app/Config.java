package org.sevenstar.app.page.app;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.config.domain.S_configureDomain;
import org.sevenstar.web.action.java.DefaultAction;

public class Config extends DefaultAction {
	private S_configureDomain s_configureDomain;

	public String input() {
		return "app/config/edit.html";
	}

	public String edit() {
		if (s_configureDomain == null || s_configureDomain.getId() == null) {
			throw new ApplicationException("请先选择一项[配置]修改");
		}
		s_configureDomain.load();
		return super.SUCCESS;
	}

	public S_configureDomain getS_configureDomain() {
		return s_configureDomain;
	}

	public void setS_configureDomain(S_configureDomain domain) {
		s_configureDomain = domain;
	}

}
