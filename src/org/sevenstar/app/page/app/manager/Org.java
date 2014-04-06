package org.sevenstar.app.page.app.manager;

import java.util.List;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.manager.domain.S_orgDomain;
import org.sevenstar.web.action.java.DefaultAction;
/**
 * @author rtm 2008-5-8
 */
public class Org extends DefaultAction {

	private S_orgDomain s_orgDomain;

	public String view(){
		if(s_orgDomain == null){
			throw new ApplicationException("请先选择部门");
		}
		s_orgDomain.load();
		return super.SUCCESS;
	}

  

	public String edit(){
		if(s_orgDomain == null){
			throw new ApplicationException("请先选择部门");
		}
		s_orgDomain.load();
		return super.SUCCESS;
	}
	
	private List orgList;
	
	public String tree(){
		orgList = (new S_orgDomain()).selectAll();
		return super.SUCCESS;
	}

 
	
	public String left() {
		if(s_orgDomain == null || s_orgDomain.getId() == null){
			return welcome();
		}
		s_orgDomain.load();
		return super.SUCCESS;
	}
	
	public String welcome(){
		return "app/manager/org/welcome.html";
	}

	public String input(){
		if(s_orgDomain != null && s_orgDomain.getUpDomain() != null && s_orgDomain.getUpDomain().getId() != null){
			s_orgDomain.getUpDomain().load();
			if(s_orgDomain.getUpDomain().getLvl() != null){
				s_orgDomain.setLvl(new Long(s_orgDomain.getUpDomain().getLvl().longValue() + 1));
			}else{
				s_orgDomain.setLvl(new Long(1));
			}
		}
		return "app/manager/org/edit.html";
	}

	public S_orgDomain getS_orgDomain() {
		return s_orgDomain;
	}

	public void setS_orgDomain(S_orgDomain domain) {
		s_orgDomain = domain;
	}

	public List getOrgList() {
		return orgList;
	}

	public void setOrgList(List orgList) {
		this.orgList = orgList;
	}
}
