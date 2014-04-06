package cn.edu.zjut.web.page.lxgd;

import java.util.List;

import org.json.JSONArray;
import org.sevenstar.util.DateHelper;
import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_newDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.service.Zjut_newService;
import cn.edu.zjut.util.JsonFormatUtil;

public class New extends DefaultAction {
	private Zjut_newDomain zjut_newDomain;
	private Long id;
	private String jsonNews;
	
	private List newsList;

	private static Zjut_organizationDomain organizationDomain;
	static {
		organizationDomain = new Zjut_organizationDomain();
		organizationDomain.setName("study-abroad");
		organizationDomain.loadEqual();
	}

	public String detail() {
		if (id == null) {
			throw new RuntimeException("缺少新闻编号，不能查看");
		}

		List<Zjut_newDomain> newsList = Zjut_newService.instance
				.getCommonNewsList(organizationDomain.getName(),"common");
		List<Zjut_newDomain> detailNewsList = Zjut_newService.instance
			.getCommonNewsList(organizationDomain.getName(),"image");
		newsList.addAll(detailNewsList);
		for (Zjut_newDomain newDomain : newsList) {
			if (id.compareTo(newDomain.getId()) == 0) {
				zjut_newDomain = newDomain;
				try {
					zjut_newDomain.setBrowse_count(zjut_newDomain
							.getBrowse_count() == null ? 1 : zjut_newDomain
							.getBrowse_count() + 1);

					zjut_newDomain.update();
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
		}
		return super.SUCCESS;
	}

	public String search() {
		if (zjut_newDomain == null || zjut_newDomain.getTitle() == null) {
			throw new RuntimeException("缺少关键词!");
		}

		newsList = Zjut_newService.instance.searchNews(zjut_newDomain
				.getTitle(), organizationDomain.getId());
//		news2Json(newsList);
		return super.SUCCESS;
	}

	private void news2Json(List<Zjut_newDomain> newList) {
		JSONArray json = new JSONArray();
		for (Zjut_newDomain newDomain : newList) {
			java.util.Map map = new java.util.HashMap();
			map.put("id", newDomain.getId());
			map.put("title", newDomain.getTitle());
			map.put("create_date", DateHelper.dateToString(newDomain
					.getCreate_date(), "yyyy-MM-dd"));
			json.put(map);
		}
		jsonNews = JsonFormatUtil.instance.format(json.toString());
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

	public String getJsonNews() {
		return jsonNews;
	}

	public void setJsonNews(String jsonNews) {
		this.jsonNews = jsonNews;
	}

	public List getNewsList() {
		return newsList;
	}

	public void setNewsList(List newsList) {
		this.newsList = newsList;
	}
	
	

}
