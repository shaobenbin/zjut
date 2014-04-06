package org.sevenstar.app.config.action;

import java.util.List;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.config.domain.S_configure_typeDomain;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;
import org.sevenstar.web.annotation.SSList;
@SSAction(name = "s_configure_type")
public class S_configure_typeAction extends DefaultAction {
	
	private S_configure_typeDomain s_configure_typeDomain;
	
	@SSList(type = "org.sevenstar.app.config.action.S_configure_typeAction")
	private List configure_typeList;
	
	public String deleteList(){
		if(configure_typeList == null){
			throw new ApplicationException("请先选中一项");
		}
		for(int i=0;i<configure_typeList.size();i++){
			S_configure_typeDomain domain = (S_configure_typeDomain)configure_typeList.get(i);
			domain.delete();
		}
		return "批量删除成功";
	}
	
    public String insertOrUpdate(){
    	if(s_configure_typeDomain == null){
    		throw new ApplicationException("没有录入信息");
    	}
    	s_configure_typeDomain.insertOrUpdate();
    	return "编辑配置类型["+s_configure_typeDomain.getName()+"]成功";
    }

	public S_configure_typeDomain getS_configure_typeDomain() {
		return s_configure_typeDomain;
	}

	public void setS_configure_typeDomain(S_configure_typeDomain domain) {
		s_configure_typeDomain = domain;
	}

	public List getConfigure_typeList() {
		return configure_typeList;
	}

	public void setConfigure_typeList(List configure_typeList) {
		this.configure_typeList = configure_typeList;
	}
    
	
    
}
