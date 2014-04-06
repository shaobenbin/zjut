package test.cn.edu.zjut.cfg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.sevenstar.persistent.db.ibatis.IbatisDao;

public class Test {
	public static void main(String[] ben){
		Map paramMap = new HashMap();
		paramMap.put("title", "留学生");
		paramMap.put("ornazation_name", "international-college");
		List list = (List) IbatisDao.getDao().queryForList("Zjut_newDomain_search_with_title", paramMap);
		System.out.println(list.size());
	}
}
