package org.sevenstar.app.manager.service;

import java.util.List;


import org.sevenstar.persistent.db.ibatis.IbatisDao;

/**
 * @author rtm 2008-5-8
 */
public class S_orgService {

  public  static S_orgService instance = new S_orgService();

	public  List selectFirstLevelList() {
		return IbatisDao.getDao().queryForList(
				"S_orgDomain_exp_select_firstlevel", null);
	}
	
}
