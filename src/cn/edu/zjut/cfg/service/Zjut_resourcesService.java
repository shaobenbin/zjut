package cn.edu.zjut.cfg.service;

import java.util.ArrayList;
import java.util.List;

import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_resourceDomain;
import cn.edu.zjut.cfg.domain.Zjut_resourcesDomain;

public enum Zjut_resourcesService {
	instance;
	public List<Zjut_resourcesDomain> getResourceList(String organizationName){
		Zjut_organization_resourceDomain  resources_organizationDomain = new Zjut_organization_resourceDomain();
		Zjut_organizationDomain organizationDomain = new Zjut_organizationDomain();
		organizationDomain.setName(organizationName);
		organizationDomain.loadEqual();
		resources_organizationDomain.setOrganizationDomain(organizationDomain);
		List<Zjut_organization_resourceDomain> tempList = resources_organizationDomain.selectEqual();
		
		List<Zjut_resourcesDomain>  resourcesList = new ArrayList<Zjut_resourcesDomain>();
		
		for(Zjut_organization_resourceDomain domain : tempList){
			resourcesList.add(domain.getResourceDomain());
		}
		
		return resourcesList;
		
	}
}
