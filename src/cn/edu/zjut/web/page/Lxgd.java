package cn.edu.zjut.web.page;

import java.util.List;

import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.service.Zjut_bulletinService;
import cn.edu.zjut.cfg.service.Zjut_faqsService;
import cn.edu.zjut.cfg.service.Zjut_newService;
import cn.edu.zjut.cfg.service.Zjut_questionService;
import cn.edu.zjut.cfg.service.Zjut_resourcesService;

public class Lxgd extends DefaultAction {

	private static Zjut_organizationDomain organizationDomain;
	private Zjut_organizationDomain zjut_organizationDomain;
	private List newList;
	private List bulletinList;
	private List faqList;
	private List questionList;

	static {
		organizationDomain = new Zjut_organizationDomain();
		organizationDomain.setName("study-abroad");
		organizationDomain.loadEqual();
	}

	public String home() {
		// 公告
		bulletinList = Zjut_bulletinService.instance.getBulletinList(
				organizationDomain.getName(), 10);
		// 新闻
		newList = Zjut_newService.instance.getCommonNewsList(
				organizationDomain.getName(), "common", 5);

		return super.SUCCESS;
	}

	public String bulletins() {
		bulletinList = Zjut_bulletinService.instance
				.getBulletinList(organizationDomain.getName());
		newList = Zjut_newService.instance.getCommonNewsList(
				organizationDomain.getName(), "common");
		return super.SUCCESS;
	}

	public String zxwd() {
		faqList = Zjut_faqsService.instance
				.getFaqsListUseTop(organizationDomain.getName());
		questionList = Zjut_questionService.instance
				.getQuestionList(organizationDomain.getName());
		zjut_organizationDomain = organizationDomain;
		return super.SUCCESS;
	}

	public String news() {
		bulletinList = Zjut_bulletinService.instance
				.getBulletinList(organizationDomain.getName());
		newList = Zjut_newService.instance.getCommonNewsList(
				organizationDomain.getName(), "common");
		return "lxgd/bulletins.html";
	}

	private List resourceList;

	public String download() {
		resourceList = Zjut_resourcesService.instance
				.getResourceList(organizationDomain.getName());
		return super.SUCCESS;
	}

	public List getResourceList() {
		return resourceList;
	}

	public void setResourceList(List resourceList) {
		this.resourceList = resourceList;
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

	public List getFaqList() {
		return faqList;
	}

	public void setFaqList(List faqList) {
		this.faqList = faqList;
	}

	public Zjut_organizationDomain getZjut_organizationDomain() {
		return zjut_organizationDomain;
	}

	public void setZjut_organizationDomain(
			Zjut_organizationDomain zjut_organizationDomain) {
		this.zjut_organizationDomain = zjut_organizationDomain;
	}

	public List getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List questionList) {
		this.questionList = questionList;
	}

}
