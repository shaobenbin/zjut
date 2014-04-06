package cn.edu.zjut.web.page.student;

import java.util.List;

import org.json.JSONArray;
import org.sevenstar.util.DateHelper;
import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_newDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.service.Zjut_newService;
import cn.edu.zjut.util.JsonFormatUtil;
import cn.edu.zjut.util.SummaryUtil;

public class New extends DefaultAction {
	private static Zjut_organizationDomain organizationDomain;

	static {
		organizationDomain = new Zjut_organizationDomain();
		organizationDomain.setName("student");
		organizationDomain.loadEqual();
	}

	private String newsJson;
	private Long id;
	private Zjut_newDomain zjut_newDomain;
	private String keywords;

	public String list() {
		List newList = Zjut_newService.instance.getCommonNewsList(
				organizationDomain.getName(), "common");
		JSONArray json = new JSONArray();
		for (int i = 0; i < newList.size(); i++) {
			java.util.Map map = new java.util.HashMap();
			Zjut_newDomain newDomain = (Zjut_newDomain) newList.get(i);
			map.put("id", newDomain.getId());
			map.put("title", newDomain.getTitle());
			map.put("create_date", DateHelper.dateToString(newDomain
					.getCreate_date(), "yyyy-MM-dd"));
			json.put(map);
		}
		newsJson = JsonFormatUtil.instance.format(json.toString());
		return super.SUCCESS;
	}

	public String detail() {
		if (id == null) {
			throw new RuntimeException("缺少参数，不能查看新闻详情!");
		}
		zjut_newDomain = new Zjut_newDomain();
		zjut_newDomain.setId(id);
		try {
			zjut_newDomain.loadEqual();
			zjut_newDomain
					.setBrowse_count(zjut_newDomain.getBrowse_count() == null ? 1
							: zjut_newDomain.getBrowse_count() + 1);
			zjut_newDomain.update();
		} catch (RuntimeException e) {
			throw new RuntimeException("找不到该新闻详情!");
		}
		return super.SUCCESS;
	}

	public String search() {
		List newList = Zjut_newService.instance.searchNews(keywords,
				organizationDomain.getId());
		JSONArray json = new JSONArray();
		for (int i = 0; i < newList.size(); i++) {
			java.util.Map map = new java.util.HashMap();
			Zjut_newDomain newDomain = (Zjut_newDomain) newList.get(i);
			map.put("id", newDomain.getId());
			map.put("title", newDomain.getTitle());
			map.put("create_date", DateHelper.dateToString(newDomain
					.getCreate_date(), "yyyy-MM-dd"));
			json.put(map);
		}
		newsJson = JsonFormatUtil.instance.format(json.toString());
		return "student/new/list.html";
	}

	public String imglist() {
		List newList = Zjut_newService.instance.getCommonNewsList(
				organizationDomain.getName(), "image");
		JSONArray json = new JSONArray();
		for (int i = 0; i < newList.size(); i++) {
			java.util.Map map = new java.util.HashMap();
			Zjut_newDomain newDomain = (Zjut_newDomain) newList.get(i);
			map.put("id", newDomain.getId());
			map.put("title", newDomain.getTitle());
			map.put("image2index", newDomain.getImage2index());
			String summary = SummaryUtil.instance.removeHtmlTag(newDomain
					.getContext(), 88);
			map.put("summary", summary);
			map.put("create_date", DateHelper.dateToString(newDomain
					.getCreate_date(), "yyyy-MM-dd"));
			json.put(map);
		}
		newsJson = JsonFormatUtil.instance.format(json.toString());
		return super.SUCCESS;
	}

	public String getNewsJson() {
		return newsJson;
	}

	public void setNewsJson(String newsJson) {
		this.newsJson = newsJson;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Zjut_newDomain getZjut_newDomain() {
		return zjut_newDomain;
	}

	public void setZjut_newDomain(Zjut_newDomain zjut_newDomain) {
		this.zjut_newDomain = zjut_newDomain;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
