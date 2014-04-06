package cn.edu.zjut.cfg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.sevenstar.persistent.db.ibatis.IbatisDao;

import cn.edu.zjut.cfg.domain.Zjut_newDomain;
import cn.edu.zjut.cfg.domain.Zjut_new_typeDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_newDomain;

public enum Zjut_newService {
	instance;
	private Map<String, Map<String,List<Zjut_newDomain>>> news = null;

	public List<Zjut_newDomain> getCommonNewsList(String organizationName,String typeName) {
		if (news == null || news.isEmpty()) {
			reload();
		}
		List resultList = sortWithTop(news.get(organizationName).get(typeName));
		if(resultList == null){
			return new ArrayList<Zjut_newDomain>();
		}
		return resultList;
	}
	
	public List<Zjut_newDomain> getCommonNewsList(String organizationName,String typeName,int size) { 
		List tempNewList =getCommonNewsList(organizationName, typeName);
		List newList = null;
		if (tempNewList != null && tempNewList.size() > size) {
			newList = tempNewList.subList(0, size);
		}else{
			newList = tempNewList;
		}
		return newList;
	}
	
	/**
	 * 将置顶的新闻排在前面，如果不需要此功能，直接返回newList即可.
	 * @param newList
	 * @return
	 */
	private List sortWithTop(List<Zjut_newDomain> newList){
		if(newList == null || newList.isEmpty()){
			return null;
		}
		List<Zjut_newDomain> topList = new ArrayList<Zjut_newDomain>();
		List<Zjut_newDomain> commonList = new ArrayList<Zjut_newDomain>();
		
		for(Zjut_newDomain newsDomain : newList){
			if("Y".equalsIgnoreCase(newsDomain.getIs_top())){
				topList.add(newsDomain);
			}else{
				commonList.add(newsDomain);
			}
		}
		topList.addAll(commonList);
		return topList;
	}

	public void reload() {
		news = new HashMap<String, Map<String,List<Zjut_newDomain>>>();
		
		Zjut_organizationDomain organizationDomain = new Zjut_organizationDomain();
		List organizationList = organizationDomain.selectAll();
		List<Zjut_new_typeDomain> newTypeList = (new Zjut_new_typeDomain()).selectAll();
		//加载所有组织下的所有新闻
		for (int i = 0; i < organizationList.size(); i++) {
			organizationDomain = (Zjut_organizationDomain) organizationList
					.get(i);
			Map<String,List<Zjut_newDomain>> newsMap = new HashMap<String,List<Zjut_newDomain>>();
			
			for(Zjut_new_typeDomain typeDomain : newTypeList){
				List<Zjut_newDomain> newList = new ArrayList<Zjut_newDomain>();
				Zjut_organization_newDomain organization_newDomain = new Zjut_organization_newDomain();
				organization_newDomain.setOrganizationDomain(organizationDomain);
				organization_newDomain.setNewTypeDomain(typeDomain);
				List organization_news = organization_newDomain.selectEqual();
				for (int j = 0; j < organization_news.size(); j++) {
					organization_newDomain = (Zjut_organization_newDomain) organization_news
							.get(j);
					newList.add(organization_newDomain.getNewDomain());
				}
				newsMap.put(typeDomain.getName(), newList);
			}
			news.put(organizationDomain.getName(), newsMap);
		}
	}
	
	public List searchNews(String title,Long ornazation_id){
		Map paramMap = new HashMap();
		paramMap.put("title", title);
		paramMap.put("ornazation_id", ornazation_id);
		return (List) IbatisDao.getDao().queryForList("Zjut_newDomain_search_with_title", paramMap);
	}
}
