package cn.edu.zjut.cfg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_questionDomain;
import cn.edu.zjut.cfg.domain.Zjut_questionDomain;

public enum Zjut_questionService {
	instance;
	private static Map<String,List<Zjut_questionDomain>> questionMap = null;
	
	public void reload(){
		
		List<Zjut_organizationDomain> organizationList = (new Zjut_organizationDomain()).selectAll();
		
		questionMap = new HashMap<String,List<Zjut_questionDomain>>();
		for(Zjut_organizationDomain organizationDomain : organizationList){
			List<Zjut_questionDomain> temps = new ArrayList<Zjut_questionDomain>();
			Zjut_organization_questionDomain organization_questionDomain = new Zjut_organization_questionDomain();
			organization_questionDomain.setOrganizationDomain(organizationDomain);
			List<Zjut_organization_questionDomain> list = organization_questionDomain.selectEqual();
			for(Zjut_organization_questionDomain domain : list){
				temps.add(domain.getQuestionDomain());
			}
			questionMap.put(organizationDomain.getName(), temps);
		}
		
	}
	
	public List<Zjut_questionDomain> getQuestionList(String organizationName){
		
		if(questionMap == null){
			reload();
		}
		
		return questionMap.get(organizationName);
	}

}
