package cn.edu.zjut.web.page;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.sevenstar.util.DateHelper;
import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_newDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_studentDomain;
import cn.edu.zjut.cfg.service.Zjut_bulletinService;
import cn.edu.zjut.cfg.service.Zjut_contentService;
import cn.edu.zjut.cfg.service.Zjut_newService;
import cn.edu.zjut.cfg.service.Zjut_resourcesService;
import cn.edu.zjut.cfg.service.Zjut_studentService;
import cn.edu.zjut.util.JsonFormatUtil;
import cn.edu.zjut.util.SummaryUtil;

public class Student extends DefaultAction {
	private final static String STUDENT_STYLE_TYPENAME = "students_style";
	private final static String RECRUIT_TYPENAME = "recruitment_info";

	// 首页显示的内容
	private List imgNewList;
	private List newList;
	private List bulletinList;
	private List stdStyleList;
	private List recruitList;

	private static Zjut_organizationDomain organizationDomain;
	static {
		organizationDomain = new Zjut_organizationDomain();
		organizationDomain.setName("student");
		organizationDomain.loadEqual();
	}

	public String index() {
		// 图片新闻
		imgNewList = Zjut_newService.instance.getCommonNewsList(
				organizationDomain.getName(), "image", 2);
		imgNewList = imgNewSummary(imgNewList);
		for (int i = 0; i < imgNewList.size(); i++) {
			Zjut_newDomain newDomain = (Zjut_newDomain) imgNewList.get(i);
			// newDomain.setContext(CutHtml.subStringHTML(newDomain.getContext(),100,
			// "..."));
			newDomain.setContext(SummaryUtil.instance.removeHtmlTag(newDomain.getContext()));
		}
		// 公告
		bulletinList = Zjut_bulletinService.instance.getBulletinList(
				organizationDomain.getName(), 10);
		// 新闻
		newList = Zjut_newService.instance.getCommonNewsList(organizationDomain
				.getName(), "common", 5);
		// 招聘信息
		recruitList = Zjut_contentService.instance.getContentList(
				organizationDomain.getName(), RECRUIT_TYPENAME, 10);

		// 学子风采
		List tempStdStyleList = Zjut_studentService.instance.getStudentList();
		if (tempStdStyleList != null && tempStdStyleList.size() > 0
				&& tempStdStyleList.size() < 3) {
			stdStyleList = new ArrayList();
			for (int i = 0; i < 3; i++) {
				stdStyleList.add(tempStdStyleList.get(i
						% tempStdStyleList.size()));
			}
		} else if (tempStdStyleList.size() > 10) {
			stdStyleList = tempStdStyleList.subList(0, 10);
		} else {
			stdStyleList = tempStdStyleList;
		}
		for (int i = 0; i < stdStyleList.size(); i++) {
			Zjut_studentDomain studentDomain = (Zjut_studentDomain) stdStyleList
					.get(i);
			studentDomain.setSummary(SummaryUtil.instance.removeHtmlTag(studentDomain.getSummary()));
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
		tempDomain.setContext(SummaryUtil.instance.summary(zjut_newDomain
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

	// =======================================科技创新================================
	private String technology_announcementJson;
	private String eventsJson;
	private String rewardsolutionJson;
	private String winhistoryJson;

	public String tzgg() {
		kjcx();
		return "student/kjcx.html";
	}

	public String lnhj() {
		kjcx();
		return "student/kjcx.html";
	}

	public String ssjj() {
		kjcx();
		return "student/kjcx.html";
	}

	public String jlbf() {
		kjcx();
		return "student/kjcx.html";
	}

	public void kjcx() {
		List tongzhiList = Zjut_contentService.instance.getContentList(
				organizationDomain.getName(), "technology_announcement");
		this.technology_announcementJson = Zjut_contentService.instance
				.contentList2json(tongzhiList);
		List eventList = Zjut_contentService.instance.getContentList(
				organizationDomain.getName(), "events");
		this.eventsJson = Zjut_contentService.instance
				.contentList2json(eventList);
		List rewardsolutionList = Zjut_contentService.instance.getContentList(
				organizationDomain.getName(), "reward_solution");
		this.rewardsolutionJson = Zjut_contentService.instance
				.contentList2json(rewardsolutionList);
		
		List winHistory = Zjut_contentService.instance.getContentList(organizationDomain.getName(), "win_prize_history");
		this.winhistoryJson =  Zjut_contentService.instance
		.contentList2json(winHistory);
	}

	// =======================================学子风采================================

	private String studentStyleJson;

	public String xzfc() {
		List studentList = Zjut_studentService.instance.getStudentList();
		JSONArray json = new JSONArray();
		for (int i = 0; i < studentList.size(); i++) {
			java.util.Map map = new java.util.HashMap();
			Zjut_studentDomain studentDomain = (Zjut_studentDomain) studentList
					.get(i);
			map.put("id", studentDomain.getId());
			map.put("name", studentDomain.getName());
			map.put("motto", studentDomain.getMotto());
			map.put("create_date", DateHelper.dateToString(studentDomain
					.getCreate_date(), "yyyy-MM-dd"));
			json.put(map);
		}

		studentStyleJson = JsonFormatUtil.instance.format(json.toString());
		return super.SUCCESS;
	}

	// =======================================奖勤助贷====================================
	private String jiangJson;
	private String qinJson;
	private String zhuJson;
	private String daiJson;

	public String jqzd() {
		List jiangList = Zjut_contentService.instance.getContentList(
				organizationDomain.getName(), "jiang");
		this.jiangJson = Zjut_contentService.instance
				.contentList2json(jiangList);

		List qinList = Zjut_contentService.instance.getContentList(
				organizationDomain.getName(), "qin");
		this.qinJson = Zjut_contentService.instance.contentList2json(qinList);

		List zhuList = Zjut_contentService.instance.getContentList(
				organizationDomain.getName(), "zhu");
		this.zhuJson = Zjut_contentService.instance.contentList2json(zhuList);

		List daiList = Zjut_contentService.instance.getContentList(
				organizationDomain.getName(), "dai");
		this.daiJson = Zjut_contentService.instance.contentList2json(daiList);
		return super.SUCCESS;
	}
	
	private List resourceList;
	public String download(){
		resourceList = Zjut_resourcesService.instance.getResourceList(organizationDomain.getName());
		return super.SUCCESS;
	}
	
	public List getImgNewList() {
		return imgNewList;
	}

	public void setImgNewList(List imgNewList) {
		this.imgNewList = imgNewList;
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

	public List getStdStyleList() {
		return stdStyleList;
	}

	public void setStdStyleList(List stdStyleList) {
		this.stdStyleList = stdStyleList;
	}

	public List getRecruitList() {
		return recruitList;
	}

	public void setRecruitList(List recruitList) {
		this.recruitList = recruitList;
	}

	public static String getSTUDENT_STYLE_TYPENAME() {
		return STUDENT_STYLE_TYPENAME;
	}

	public static String getRECRUIT_TYPENAME() {
		return RECRUIT_TYPENAME;
	}

	public String getTechnology_announcementJson() {
		return technology_announcementJson;
	}

	public void setTechnology_announcementJson(
			String technology_announcementJson) {
		this.technology_announcementJson = technology_announcementJson;
	}

	public String getEventsJson() {
		return eventsJson;
	}

	public void setEventsJson(String eventsJson) {
		this.eventsJson = eventsJson;
	}

	public String getRewardsolutionJson() {
		return rewardsolutionJson;
	}

	public void setRewardsolutionJson(String rewardsolutionJson) {
		this.rewardsolutionJson = rewardsolutionJson;
	}

	public String getStudentStyleJson() {
		return studentStyleJson;
	}

	public void setStudentStyleJson(String studentStyleJson) {
		this.studentStyleJson = studentStyleJson;
	}

	public String getJiangJson() {
		return jiangJson;
	}

	public void setJiangJson(String jiangJson) {
		this.jiangJson = jiangJson;
	}

	public String getQinJson() {
		return qinJson;
	}

	public void setQinJson(String qinJson) {
		this.qinJson = qinJson;
	}

	public String getZhuJson() {
		return zhuJson;
	}

	public void setZhuJson(String zhuJson) {
		this.zhuJson = zhuJson;
	}

	public String getDaiJson() {
		return daiJson;
	}

	public void setDaiJson(String daiJson) {
		this.daiJson = daiJson;
	}

	public String getWinhistoryJson() {
		return winhistoryJson;
	}

	public void setWinhistoryJson(String winhistoryJson) {
		this.winhistoryJson = winhistoryJson;
	}

	public List getResourceList() {
		return resourceList;
	}

	public void setResourceList(List resourceList) {
		this.resourceList = resourceList;
	}

	
}
