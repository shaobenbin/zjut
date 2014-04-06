package cn.edu.zjut.web.page.gjxy;

import java.util.List;

import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_partner_universitiesDomain;
import cn.edu.zjut.cfg.service.Zjut_partner_universitiesService;

public class Unis extends DefaultAction {
	private List universitieList;

	private Zjut_partner_universitiesDomain universitiesDomain;

	public String parners() {
		
		universitieList = Zjut_partner_universitiesService.instance
				.getUniversitieList();
		if(universitiesDomain == null || universitiesDomain.getId() == null){
			universitiesDomain = (Zjut_partner_universitiesDomain) universitieList.get(0);
		}else{
			universitiesDomain.loadEqual();
		}
		return super.SUCCESS;
	}

	public List getUniversitieList() {
		return universitieList;
	}

	public void setUniversitieList(List universitieList) {
		this.universitieList = universitieList;
	}

	public Zjut_partner_universitiesDomain getUniversitiesDomain() {
		return universitiesDomain;
	}

	public void setUniversitiesDomain(
			Zjut_partner_universitiesDomain universitiesDomain) {
		this.universitiesDomain = universitiesDomain;
	}

}
