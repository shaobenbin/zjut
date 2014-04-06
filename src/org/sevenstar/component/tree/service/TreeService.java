package org.sevenstar.component.tree.service;

import java.util.List;
import org.sevenstar.persistent.db.ibatis.IbatisDao;

public class TreeService {
	public final static TreeService instance = new TreeService();
	public List selectAllResource(String name){
		if(name == null){
			name = "" ;
		}
		return IbatisDao.getDao().queryForList("TreeDomain_exp_select_resourceTree", name);
	}
}
