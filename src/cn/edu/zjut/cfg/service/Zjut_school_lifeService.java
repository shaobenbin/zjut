package cn.edu.zjut.cfg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_lifeDomain;
import cn.edu.zjut.cfg.domain.Zjut_school_lifeDomain;

public enum Zjut_school_lifeService {
	instance;
	private Map<String, List<Zjut_school_lifeDomain>> lifes = null;

	public void reload() {
		lifes = new HashMap<String, List<Zjut_school_lifeDomain>>();

		Zjut_organizationDomain organizationDomain = new Zjut_organizationDomain();
		List organizationList = organizationDomain.selectAll();

		for (int i = 0; i < organizationList.size(); i++) {
			organizationDomain = (Zjut_organizationDomain) organizationList
					.get(i);

			List<Zjut_school_lifeDomain> lifeList = new ArrayList<Zjut_school_lifeDomain>();

			Zjut_organization_lifeDomain organization_lifeDomain = new Zjut_organization_lifeDomain();
			organization_lifeDomain.setOrganizationDomain(organizationDomain);
			List organization_lifes = organization_lifeDomain.selectEqual();
			for (int j = 0; j < organization_lifes.size(); j++) {
				organization_lifeDomain = (Zjut_organization_lifeDomain) organization_lifes
						.get(j);
				lifeList.add(organization_lifeDomain.getSchoolLifeDomain());
			}
			lifes.put(organizationDomain.getName(), lifeList);
		}
	}

	public List<Zjut_school_lifeDomain> getSchoolLifeList(
			String organizationName) {
		if (lifes == null || lifes.isEmpty()) {
			reload();
		}
		return lifes.get(organizationName);
	}

	public List<Zjut_school_lifeDomain> getSchoolLifeList(
			String organizationName, int size) {
		List tempLifeList = Zjut_school_lifeService.instance
				.getSchoolLifeList(organizationName);
		List lifeList = null;
		if (tempLifeList != null && tempLifeList.size() >  size) {
			lifeList = tempLifeList.subList(0,  size);
		} else {
			lifeList = tempLifeList;
		}
		return lifeList;
	}
}
