package org.sevenstar.app.module.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.sevenstar.persistent.db.ibatis.IbatisDao;
/**
 * @author rtm 2008-5-8
 */
public class S_moduleService  {

 
	
	
	public List selectFirstMenu(String system_name) {
		Map map = new HashMap();
		map.put("system_name", system_name);
		return IbatisDao.getDao().queryForList(
				"S_moduleDomain_exp_select_firstmenu", map);
	}

	public List selectFirstLevel(String system_name) {
		Map map = new HashMap();
		map.put("system_name",system_name);
		return IbatisDao.getDao().queryForList(
				"S_moduleDomain_exp_select_firstlevel", map);
	}
	
	
	 

	


}
