package cn.edu.zjut.web.page.lxgd;

import java.util.List;

import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_questionDomain;
import cn.edu.zjut.cfg.service.Zjut_questionService;

public class Question extends DefaultAction{
	private Long id;

	private static Zjut_organizationDomain organizationDomain;
	static {
		organizationDomain = new Zjut_organizationDomain();
		organizationDomain.setName("study-abroad");
		organizationDomain.loadEqual();
	}

	private Zjut_questionDomain zjut_questionDomain;
	
	public String detail(){
		
		if(id == null){
			throw new RuntimeException("缺少参数,不能查看问答!");
		}
		List<Zjut_questionDomain> questionList = Zjut_questionService.instance.getQuestionList(organizationDomain.getName());
		
		for(Zjut_questionDomain questionDomain : questionList){
			if(id.equals(questionDomain.getId())){
				zjut_questionDomain = questionDomain;
				break;
			}
		}
		return super.SUCCESS;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Zjut_questionDomain getZjut_questionDomain() {
		return zjut_questionDomain;
	}

	public void setZjut_questionDomain(Zjut_questionDomain zjut_questionDomain) {
		this.zjut_questionDomain = zjut_questionDomain;
	}
	
	
}
