package cn.edu.zjut.web.page;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.sevenstar.util.DateHelper;
import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_bulletinDomain;
import cn.edu.zjut.cfg.domain.Zjut_newDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_school_lifeDomain;
import cn.edu.zjut.cfg.service.Zjut_bulletinService;
import cn.edu.zjut.cfg.service.Zjut_newService;
import cn.edu.zjut.cfg.service.Zjut_partner_universitiesService;
import cn.edu.zjut.cfg.service.Zjut_school_lifeService;
import cn.edu.zjut.util.JsonFormatUtil;
import cn.edu.zjut.util.SummaryUtil;

public class Gjxy extends DefaultAction {
	private List newList;
	private List imgNewList;
	private List bulletinList;
	private List lifeList;
	private List universitiyList;

	private String jsonNews;
	private String jsonBulletins;
	private String jsonLifes;

	private static Zjut_organizationDomain organizationDomain;
	static {
		organizationDomain = new Zjut_organizationDomain();
		organizationDomain.setName("international-college");
		organizationDomain.loadEqual();
	}

	public String index() {
		newList = Zjut_newService.instance.getCommonNewsList(organizationDomain
				.getName(), "common", 12);

		imgNewList = Zjut_newService.instance.getCommonNewsList(
				organizationDomain.getName(), "image", 1);
		imgNewList = imgNewSummary(imgNewList);

		bulletinList = Zjut_bulletinService.instance.getBulletinList(
				organizationDomain.getName(), 12);

		lifeList = Zjut_school_lifeService.instance.getSchoolLifeList(
				organizationDomain.getName(), 2);

		List tempUniversitiyList = Zjut_partner_universitiesService.instance
				.getUniversitieList();

		if (tempUniversitiyList.size() < 3) {
			// universitiyList = new ArrayList();
			// for (int i = 0; i < 3; i++) {
			// universitiyList.add(tempUniversitiyList.get(i%tempUniversitiyList.size()));
			// }
			universitiyList = tempUniversitiyList;
		} else {
			universitiyList = tempUniversitiyList.subList(0, 3);
		}
		return super.SUCCESS;
	}

	private List imgNewSummary(List imgNewList) {
		if (imgNewList == null || imgNewList.isEmpty()) {
			return null;
		}

		List resultList = new ArrayList();
		for (int i = 0; i < imgNewList.size(); i++) {
			Zjut_newDomain zjut_newDomain = (Zjut_newDomain) imgNewList.get(i);
			resultList.add(summary(zjut_newDomain));
		}
		return resultList;
	}

	private Zjut_newDomain summary(Zjut_newDomain zjut_newDomain) {
		Zjut_newDomain tempDomain = new Zjut_newDomain();
		tempDomain.setContext(SummaryUtil.instance.removeHtmlTag(zjut_newDomain
				.getContext()));
		tempDomain.setId(zjut_newDomain.getId());
		tempDomain.setAuthor(zjut_newDomain.getAuthor());
		tempDomain.setBrowse_count(zjut_newDomain.getBrowse_count());
		tempDomain.setImage2index(zjut_newDomain.getImage2index());
		tempDomain.setIs_top(zjut_newDomain.getIs_top());
		tempDomain.setIs_valid(zjut_newDomain.getIs_valid());
		tempDomain.setExpire_time(zjut_newDomain.getExpire_time());
		return tempDomain;
	}

	public String news() {
		news_bulletins_lifes();
		return "gjxy/news_life.html";
	}

	public String bulletins() {
		news_bulletins_lifes();
		return "gjxy/news_life.html";
	}

	public String lifes() {
		news_bulletins_lifes();
		return "gjxy/news_life.html";
	}

	private void news2Json() {
		List<Zjut_newDomain> newList = Zjut_newService.instance
				.getCommonNewsList(organizationDomain.getName(), "common");
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

	private void bulletins2Json() {
		List<Zjut_bulletinDomain> bulletinList = Zjut_bulletinService.instance
				.getBulletinList(organizationDomain.getName());
		JSONArray json = new JSONArray();
		for (Zjut_bulletinDomain bulletinDomain : bulletinList) {
			java.util.Map map = new java.util.HashMap();
			map.put("id", bulletinDomain.getId());
			map.put("title", bulletinDomain.getTitle());
			map.put("create_date", DateHelper.dateToString(bulletinDomain
					.getCreate_date(), "yyyy-MM-dd"));
			json.put(map);
		}
		jsonBulletins = JsonFormatUtil.instance.format(json.toString());
	}

	private void lifes2Json() {
		List<Zjut_school_lifeDomain> lifeList = Zjut_school_lifeService.instance
				.getSchoolLifeList(organizationDomain.getName());
		JSONArray json = new JSONArray();
		for (Zjut_school_lifeDomain lifeDomain : lifeList) {
			java.util.Map map = new java.util.HashMap();
			map.put("id", lifeDomain.getId());
			map.put("image", lifeDomain.getImage());
			map.put("title", lifeDomain.getTitle());
			map.put("summary", lifeDomain.getSummary());
			//map.put("content", lifeDomain.getContent());
			map.put("create_date", DateHelper.dateToString(lifeDomain
					.getCreate_date(), "yyyy-MM-dd"));
			json.put(map);
		}

		jsonLifes = JsonFormatUtil.instance.format(json.toString());
	}

	private void news_bulletins_lifes() {
		this.news2Json();
		this.bulletins2Json();
		this.lifes2Json();
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

	public List getLifeList() {
		return lifeList;
	}

	public void setLifeList(List lifeList) {
		this.lifeList = lifeList;
	}

	public String getJsonNews() {
		return jsonNews;
	}

	public void setJsonNews(String jsonNews) {
		this.jsonNews = jsonNews;
	}

	public String getJsonBulletins() {
		return jsonBulletins;
	}

	public void setJsonBulletins(String jsonBulletins) {
		this.jsonBulletins = jsonBulletins;
	}

	public String getJsonLifes() {
		return jsonLifes;
	}

	public void setJsonLifes(String jsonLifes) {
		this.jsonLifes = jsonLifes;
	}

	public List getImgNewList() {
		return imgNewList;
	}

	public void setImgNewList(List imgNewList) {
		this.imgNewList = imgNewList;
	}

	public List getUniversitiyList() {
		return universitiyList;
	}

	public void setUniversitiyList(List universitiyList) {
		this.universitiyList = universitiyList;
	}

}
