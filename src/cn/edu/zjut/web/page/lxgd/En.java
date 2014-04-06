package cn.edu.zjut.web.page.lxgd;

import java.util.List;

import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.service.Zjut_bulletinService;
import cn.edu.zjut.cfg.service.Zjut_newService;

public class En extends DefaultAction{
	
	private static Zjut_organizationDomain organizationDomain;
	
	private List newList;
	private List bulletinList;
	
	static {
		organizationDomain = new Zjut_organizationDomain();
		organizationDomain.setName("study-abroad");
		organizationDomain.loadEqual();
	}
	
	public String home() {
		newList = Zjut_newService.instance.getCommonNewsList(
				organizationDomain.getName(), "english", 5);
		bulletinList = Zjut_bulletinService.instance.getEnBulletinList(organizationDomain.getName(),10);
		return super.SUCCESS;
	}
	
	public List getNewList() {
		return newList;
	}

	public void setNewList(List newList) {
		this.newList = newList;
	}

	public List getBulletinList() {
		return bulletinList;
	}

	public void setBulletinList(List bulletinList) {
		this.bulletinList = bulletinList;
	}
	
	
}
