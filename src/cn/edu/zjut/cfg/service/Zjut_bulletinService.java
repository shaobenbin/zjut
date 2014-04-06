package cn.edu.zjut.cfg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.zjut.cfg.domain.Zjut_bulletinDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_bulletinDomain;

public enum Zjut_bulletinService {
	instance;
	private Map<String, List<Zjut_bulletinDomain>> bulletins = null;
	private Map<String,List<Zjut_bulletinDomain>> en_bulletins = null;

	public void reload() {
		bulletins = new HashMap<String, List<Zjut_bulletinDomain>>();
		en_bulletins = new HashMap<String, List<Zjut_bulletinDomain>>();
		Zjut_organizationDomain organizationDomain = new Zjut_organizationDomain();
		List organizationList = organizationDomain.selectAll();
		for (int i = 0; i < organizationList.size(); i++) {
			organizationDomain = (Zjut_organizationDomain) organizationList
					.get(i);

			List<Zjut_bulletinDomain> bulletinList = new ArrayList<Zjut_bulletinDomain>();
			List<Zjut_bulletinDomain> en_bulletinList = new ArrayList<Zjut_bulletinDomain>();

			Zjut_organization_bulletinDomain organization_bulletinDomain = new Zjut_organization_bulletinDomain();
			organization_bulletinDomain
					.setOrganizationDomain(organizationDomain);
			List organization_bulletins = organization_bulletinDomain
					.selectEqual();

			for (int j = 0; j < organization_bulletins.size(); j++) {
				organization_bulletinDomain = (Zjut_organization_bulletinDomain) organization_bulletins
						.get(j);
				if("Y".equalsIgnoreCase(organization_bulletinDomain
						.getBulletinDomain().getIs_en())){
					en_bulletinList.add(organization_bulletinDomain
							.getBulletinDomain());
				}else{
					bulletinList.add(organization_bulletinDomain
							.getBulletinDomain());
				}
				
			}
			bulletins.put(organizationDomain.getName(), bulletinList);
			en_bulletins.put(organizationDomain.getName(), en_bulletinList);
		}
	}

	/**
	 * 获取公告
	 * @param organizationName
	 * @return
	 */
	public List<Zjut_bulletinDomain> getBulletinList(String organizationName) {
		if (bulletins == null || bulletins.isEmpty()) {
			reload();
		}
		return sortByTop(bulletins.get(organizationName));
	}
	
	/**
	 * 获取英文公告
	 * @param organizationName
	 * @return
	 */
	public List<Zjut_bulletinDomain> getEnBulletinList(String organizationName) {
		if (en_bulletins == null || en_bulletins.isEmpty()) {
			reload();
		}
		return sortByTop(en_bulletins.get(organizationName));
	}
	
	public List<Zjut_bulletinDomain> getEnBulletinList(String organizationName,
			int size) {
		List tempBulletinList = this.getEnBulletinList(organizationName);
		List bulletinList = null;
		if (tempBulletinList != null && tempBulletinList.size() > size) {
			bulletinList = tempBulletinList.subList(0, size);
		} else {
			bulletinList = tempBulletinList;
		}
		return bulletinList;
	}

	public List<Zjut_bulletinDomain> getBulletinList(String organizationName,
			int size) {

		List tempBulletinList = this.getBulletinList(organizationName);
		List bulletinList = null;
		if (tempBulletinList != null && tempBulletinList.size() > size) {
			bulletinList = tempBulletinList.subList(0, size);
		} else {
			bulletinList = tempBulletinList;
		}
		return bulletinList;
	}

	/**
	 * 根据排序将置顶的对象放在前面
	 * 
	 * @param bulletinList
	 * @return
	 */
	private List<Zjut_bulletinDomain> sortByTop(
			List<Zjut_bulletinDomain> bulletinList) {
//		if (bulletinList == null || bulletinList.isEmpty()) {
//			return null;
//		}
		List<Zjut_bulletinDomain> topList = new ArrayList<Zjut_bulletinDomain>();
		List<Zjut_bulletinDomain> commonList = new ArrayList<Zjut_bulletinDomain>();
		for (Zjut_bulletinDomain domain : bulletinList) {
			if ("Y".equalsIgnoreCase(domain.getIs_top())) {
				topList.add(domain);
			} else {
				commonList.add(domain);
			}
		}
		topList.addAll(commonList);
		return topList;
	}
}
