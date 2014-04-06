package org.sevenstar.app.page.app.manager;

import java.util.ArrayList;
import java.util.List;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.manager.domain.S_orgDomain;
import org.sevenstar.app.manager.domain.S_org_positionDomain;
import org.sevenstar.app.manager.domain.S_userDomain;
import org.sevenstar.app.manager.domain.S_user_typeDomain;
import org.sevenstar.app.manager.service.S_orgService;
import org.sevenstar.app.rbac.domain.S_rbac_groupDomain;
import org.sevenstar.app.rbac.domain.S_rbac_roleDomain;
 
import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.util.BeanHelper;
import org.sevenstar.web.action.java.DefaultAction;
/**
 * @author rtm 2008-5-8
 */
public class User extends DefaultAction {

	private S_userDomain s_userDomain;

	private IbatisPage page;

	private List groupList;

	private List roleList;

	private List positionList;

	private S_orgService s_orgService = new S_orgService();
 
	private String inputID ;
	
	private String inputName ;
	
	private List userTypeList;
	
	public String list() {
		if(s_userDomain == null){
			s_userDomain = new S_userDomain();
		}
		page = s_userDomain.getSelectPage(page);
 		return super.SUCCESS;
	}
	
 

	public String edit(){
		if(s_userDomain == null || s_userDomain.getId() == null){
			throw new ApplicationException("没有选中用户");
		}
		s_userDomain.load();
		userTypeList = (new S_user_typeDomain()).selectAll();
		groupList = BeanHelper.copyNewList((new S_rbac_groupDomain()).selectAll());
		roleList = BeanHelper.copyNewList((new S_rbac_roleDomain()).selectAll());
		positionList = new ArrayList();
		List firstLevelOrgList =s_orgService.selectFirstLevelList();
 		List hasSelectedGroupList = s_userDomain.getGroupList();
		List hasSelectedRoleList = s_userDomain.getRoleList();
 		for(int i=0;i<groupList.size();i++){
			S_rbac_groupDomain s_rbac_groupDomain = (S_rbac_groupDomain)groupList.get(i);
			for(int j=0;j<hasSelectedGroupList.size();j++){
				S_rbac_groupDomain domain = (S_rbac_groupDomain)hasSelectedGroupList.get(j);
				if(domain.getId().equals(s_rbac_groupDomain.getId())){
					groupList.remove(i);
					i--;
					continue;
				}
			}
		}
		for(int i=0;i<roleList.size();i++){
			S_rbac_roleDomain s_rbac_roleDomain = (S_rbac_roleDomain)roleList.get(i);
			for(int j=0;j<hasSelectedRoleList.size();j++){
				S_rbac_roleDomain domain = (S_rbac_roleDomain)hasSelectedRoleList.get(j);
				if(domain.getId().equals(s_rbac_roleDomain.getId())){
					roleList.remove(i);
					i--;
					continue;
				}
			}
		}
		for(int i=0;i<firstLevelOrgList.size();i++){
			S_orgDomain s_orgDomain = (S_orgDomain)firstLevelOrgList.get(i);
			addPosition(s_orgDomain);
		}
		return super.SUCCESS;
	}


	public String view(){
		if(s_userDomain == null){
			throw new ApplicationException("没有选中用户");
		}
		s_userDomain.load();
		userTypeList = (new S_user_typeDomain()).selectAll();
		return super.SUCCESS;
	}
	
	private void addPosition(S_orgDomain s_orgDomain){
		List orgPositionList = s_orgDomain.getPositionList();
		List hasSelectPositionList = new ArrayList();
		if(s_userDomain != null && s_userDomain.getPositionList() != null ){
			hasSelectPositionList = s_userDomain.getPositionList();
		}
	    for(int i=0;i<orgPositionList.size();i++){
	    	S_org_positionDomain positionDomain = (S_org_positionDomain)orgPositionList.get(i);
	    	boolean hasSelected = false;
	    	for(int j=0;j<hasSelectPositionList.size();j++){
	    		S_org_positionDomain selectedPositionDomain = (S_org_positionDomain)hasSelectPositionList.get(j);
	    		if(positionDomain.getId().equals(selectedPositionDomain.getId())){
	    			hasSelected = true;
	    		}
	    	}
	    	if(!hasSelected){
	    		positionList.add(positionDomain);
	    	}
	    }
	    List childList = s_orgDomain.getChildList();
	    for(int i=0;i<childList.size();i++){
	    	addPosition((S_orgDomain)childList.get(i));
	    }
	}


	public String input(){
		groupList = (new S_rbac_groupDomain()).selectAll();
		roleList = (new S_rbac_roleDomain()).selectAll();
		positionList = new ArrayList();
		userTypeList = (new S_user_typeDomain()).selectAll();
		List firstLevelOrgList =s_orgService.selectFirstLevelList();
		for(int i=0;i<firstLevelOrgList.size();i++){
			S_orgDomain s_orgDomain = (S_orgDomain)firstLevelOrgList.get(i);
			addPosition(s_orgDomain);
		}
		return "app/manager/user/edit.html";
	}

	public String userinfo() throws Exception{
		return list();
	}
	
	public List getGroupList() {
		return groupList;
	}

	public void setGroupList(List groupList) {
		this.groupList = groupList;
	}

	public List getRoleList() {
		return roleList;
	}

	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}

	public S_userDomain getS_userDomain() {
		return s_userDomain;
	}

	public void setS_userDomain(S_userDomain domain) {
		s_userDomain = domain;
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}

	public List getPositionList() {
		return positionList;
	}

	public void setPositionList(List positionList) {
		this.positionList = positionList;
	}



	public List getUserTypeList() {
		return userTypeList;
	}



	public void setUserTypeList(List userTypeList) {
		this.userTypeList = userTypeList;
	}



}
