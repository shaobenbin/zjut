package cn.edu.zjut.cfg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import cn.edu.zjut.cfg.domain.Zjut_faqsDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_faqDomain;

public enum Zjut_faqsService {
	instance;
	public Map<String,List<Zjut_faqsDomain>> faqsMap = null;
	
	public void reload(){
		faqsMap = new HashMap<String,List<Zjut_faqsDomain>>();
		
		List<Zjut_organizationDomain> organizationList = (new Zjut_organizationDomain()).selectAll();
		for(Zjut_organizationDomain organizationDomain : organizationList){
			List<Zjut_faqsDomain> temps = new ArrayList<Zjut_faqsDomain>();
			Zjut_organization_faqDomain zjut_organization_faqDomain = new Zjut_organization_faqDomain();
			zjut_organization_faqDomain.setOrganizationDomain(organizationDomain);
			List<Zjut_organization_faqDomain> organization_faqList = zjut_organization_faqDomain.selectEqual();
			for(Zjut_organization_faqDomain organizationFaqDomain : organization_faqList){
				temps.add(organizationFaqDomain.getFaqsDomain());
			}
			
			faqsMap.put(organizationDomain.getName(), temps);
		}
	}
	
	public List<Zjut_faqsDomain> getFaqsList(String organizatioName){
		if(faqsMap == null){
			reload();
		}
		
		return faqsMap.get(organizatioName);
	}
	
	public List<Zjut_faqsDomain> getFaqsListUseTop(String organizatioName){
		List<Zjut_faqsDomain> list = getFaqsList(organizatioName);
		List<Zjut_faqsDomain> topList = new ArrayList<Zjut_faqsDomain>();
		List<Zjut_faqsDomain> unTopList = new ArrayList<Zjut_faqsDomain>();
		for(Zjut_faqsDomain faqDomain : list){
			if("Y".equalsIgnoreCase(faqDomain.getIs_top())){
				topList.add(faqDomain);
			}else{
				unTopList.add(faqDomain);
			}
		}
		
		topList.addAll(unTopList);
		return topList;
	}
	
}
