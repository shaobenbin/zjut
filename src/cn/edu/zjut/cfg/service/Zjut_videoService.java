package cn.edu.zjut.cfg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_videoDomain;
import cn.edu.zjut.cfg.domain.Zjut_videosDomain;

public enum Zjut_videoService {
	instance;
	private Map<String, List<Zjut_videosDomain>> videos = null;

	public void reload() {
		videos = new HashMap<String, List<Zjut_videosDomain>>();
		Zjut_organizationDomain organizationDomain = new Zjut_organizationDomain();
		List organizationList = organizationDomain.selectAll();
		for (int i = 0; i < organizationList.size(); i++) {
			organizationDomain = (Zjut_organizationDomain) organizationList.get(i);
			List<Zjut_videosDomain> videosList = new ArrayList<Zjut_videosDomain>();

			Zjut_organization_videoDomain zjut_organization_videoDomain = new Zjut_organization_videoDomain();
			zjut_organization_videoDomain.setOrganizationDomain(organizationDomain);
			List<Zjut_organization_videoDomain> organization_videoList = zjut_organization_videoDomain.selectEqual();
			for (int j = 0; j < organization_videoList.size(); j++) {
				videosList.add(organization_videoList.get(j).getVideosDomain());
			}
			videos.put(organizationDomain.getName(), videosList);
		}
	}

	public List<Zjut_videosDomain> getVideosList(String organizationName, int size) {
		if (videos == null) {
			reload();
		}
		List tempVideoList = null;
		List videoList = videos.get(organizationName);
		if (videoList.size() > 10) {
			tempVideoList = videoList.subList(0, 10);
		} else {
			tempVideoList = videoList;
		}
		return tempVideoList;
	}
}
