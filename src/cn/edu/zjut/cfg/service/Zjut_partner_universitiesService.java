package cn.edu.zjut.cfg.service;

import java.util.List;

import cn.edu.zjut.cfg.domain.Zjut_partner_universitiesDomain;

public enum Zjut_partner_universitiesService {
	instance;
	private List<Zjut_partner_universitiesDomain> universitieList;
	
	public void load(){
		universitieList = (new Zjut_partner_universitiesDomain()).selectAll();
	}
	
	public List<Zjut_partner_universitiesDomain> getUniversitieList(){
		if(universitieList == null || universitieList.isEmpty()){
			load();
		}
		return universitieList;
	}
}
