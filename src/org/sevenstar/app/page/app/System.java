package org.sevenstar.app.page.app;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.system.domain.S_systemDomain;
import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.web.action.java.DefaultAction;

public class System extends DefaultAction {
	
	private S_systemDomain s_systemDomain;

	private IbatisPage page;

	public String list() {
		if(s_systemDomain == null){
			s_systemDomain = new S_systemDomain();
		}
		page = s_systemDomain.getSelectPage(page);
		return super.SUCCESS;
	}

	public String edit(){
		if(s_systemDomain == null){
			throw new ApplicationException("没有选中资源");
		}
		s_systemDomain.load();
		return super.SUCCESS;
	}

	public String view(){
		if(s_systemDomain == null){
			throw new ApplicationException("没有选中资源");
		}
		s_systemDomain.load();
		return super.SUCCESS;
	}

	public String input(){
		return "app/system/edit.html";
	}

	public S_systemDomain getS_systemDomain() {
		return s_systemDomain;
	}

	public void setS_systemDomain(S_systemDomain domain) {
		s_systemDomain = domain;
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}
}
