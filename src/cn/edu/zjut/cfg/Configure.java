package cn.edu.zjut.cfg;

import cn.edu.zjut.cfg.service.Zjut_bulletinService;
import cn.edu.zjut.cfg.service.Zjut_menu_contentService;
import cn.edu.zjut.cfg.service.Zjut_newService;
import cn.edu.zjut.cfg.service.Zjut_school_lifeService;

public enum Configure {
	instance;
	public void reload() {
		Zjut_bulletinService.instance.reload();
		Zjut_newService.instance.reload();
		Zjut_school_lifeService.instance.reload();
		Zjut_menu_contentService.instance.reload();
	}
}
