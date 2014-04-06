package cn.edu.zjut.cfg.service;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

import org.json.JSONArray;
import org.sevenstar.util.DateHelper;

import cn.edu.zjut.cfg.domain.Zjut_contentDomain;
import cn.edu.zjut.cfg.domain.Zjut_content_typeDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.util.JsonFormatUtil;

public enum Zjut_contentService {
	instance;
	private Map<String,Map<String,List<Zjut_contentDomain>>> contentMap = new HashMap<String,Map<String,List<Zjut_contentDomain>>>();
	
	public void load(){
		Zjut_contentDomain contentDomain = null;
		
		List<Zjut_organizationDomain> organizationList = (new Zjut_organizationDomain()).selectAll();
		for(Zjut_organizationDomain organizationDomain : organizationList){
			Map<String,List<Zjut_contentDomain>> typeMap = new HashMap<String,List<Zjut_contentDomain>>(); 
			List<Zjut_content_typeDomain> typeList = (new Zjut_content_typeDomain()).selectAll();
			for(Zjut_content_typeDomain typeDomain : typeList){
				contentDomain = new Zjut_contentDomain();
				contentDomain.setTypeDomain(typeDomain);
				contentDomain.setOrganizationDomain(organizationDomain);
				List<Zjut_contentDomain> contentList = contentDomain.selectEqual();
				typeMap.put(typeDomain.getName(), contentList);
			}
			contentMap.put(organizationDomain.getName(),typeMap);
		}
	}
	
	public List<Zjut_contentDomain> getContentList(String organizationName,String contentName){
		if(contentMap == null || contentMap.isEmpty()){
			load();
		}
		return contentMap.get(organizationName) == null ? new ArrayList<Zjut_contentDomain>() : contentMap.get(organizationName).get(contentName);
	}
	
	public List<Zjut_contentDomain> getContentList(String organizationName,String contentName,int size){
		List tempRecruitList = Zjut_contentService.instance.getContentList(organizationName, contentName);
		List recruitList = null;
		if(tempRecruitList !=null && tempRecruitList.size()<=size){
			recruitList = tempRecruitList;
		}else{
			recruitList = tempRecruitList.subList(0, size);
		}
		return recruitList;
	}
	
	public String contentList2json(List contentList) {
		if (contentList == null || contentList.isEmpty())
			contentList = new ArrayList();
		JSONArray json = new JSONArray();
		for (int i = 0; i < contentList.size(); i++) {
			java.util.Map map = new java.util.HashMap();
			Zjut_contentDomain contentDomain = (Zjut_contentDomain) contentList
					.get(i);
			map.put("id", contentDomain.getId());
			map.put("title", contentDomain.getTitle());
			map.put("create_date", DateHelper.dateToString(contentDomain
					.getCreate_date(), "yyyy-MM-dd"));
			json.put(map);
		}
		return JsonFormatUtil.instance.format(json.toString());
	}
}
