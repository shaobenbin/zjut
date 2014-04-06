package cn.edu.zjut.web.page.student;

import java.util.List;

import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_contentDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.service.Zjut_contentService;

public class Dtjs extends DefaultAction {
	private String partydynamicJson;
	private Zjut_contentDomain zjut_contentDomain;
	private Long id;
	
	private static Zjut_organizationDomain organizationDomain;
	

	static {
		organizationDomain = new Zjut_organizationDomain();
		organizationDomain.setName("student");
		organizationDomain.loadEqual();
	}

	public String djdt() {
		List partydynamicList = Zjut_contentService.instance.getContentList(
				organizationDomain.getName(), "party_dynamic");
		this.partydynamicJson = Zjut_contentService.instance
				.contentList2json(partydynamicList);
		return "student/dtjs.html";
	}
	
	public String detail(){
		if(id == null){
			throw new RuntimeException("缺少参数，不能查看党建动态!");
		}
		zjut_contentDomain = new Zjut_contentDomain();
		zjut_contentDomain.setId(id);
		try{
			zjut_contentDomain.loadEqual();
		}catch(RuntimeException e){
			throw new RuntimeException("查找不到指定的党建动态内容!");
		}
		
		djdt();
		return "student/dtjs.html";
	}

	public String getPartydynamicJson() {
		return partydynamicJson;
	}

	public void setPartydynamicJson(String partydynamicJson) {
		this.partydynamicJson = partydynamicJson;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
	
}
