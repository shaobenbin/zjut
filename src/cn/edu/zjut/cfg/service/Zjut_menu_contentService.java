package cn.edu.zjut.cfg.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.zjut.cfg.domain.Zjut_menu_contentDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;

public enum Zjut_menu_contentService {
	instance;
	private Map<String, List<Zjut_menu_contentDomain>> menus;

	public void reload() {
		menus = new HashMap<String, List<Zjut_menu_contentDomain>>();

		Zjut_organizationDomain zjut_organizationDomain = new Zjut_organizationDomain();
		List organizationList = zjut_organizationDomain.selectAll();

		for (int i = 0; i < organizationList.size(); i++) {
			zjut_organizationDomain = (Zjut_organizationDomain) organizationList
					.get(i);
			Zjut_menu_contentDomain menuDomain = new Zjut_menu_contentDomain();
			menuDomain.setOrganizationDomain(zjut_organizationDomain);
			menus.put(zjut_organizationDomain.getName(), menuDomain
					.selectEqual());
		}
	}

	public List<Zjut_menu_contentDomain> getMenus(String organizationName) {
		if (menus == null || menus.isEmpty()) {
			this.reload();
		}

		return menus.get(organizationName);
	}

	public Zjut_menu_contentDomain getMenuDomain(String menuName,
			String organizationName) {
		if (menuName == null || "".equals(menuName)) {
			return null;
		}
		List<Zjut_menu_contentDomain> menuList = getMenus(organizationName);
		for (Zjut_menu_contentDomain menuDomain : menuList) {
			if (menuName.equals(menuDomain.getMenu_name())) {
				return menuDomain;
			}
		}

		return null;
	}
}
