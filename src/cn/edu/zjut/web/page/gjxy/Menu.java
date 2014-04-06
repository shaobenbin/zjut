package cn.edu.zjut.web.page.gjxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_menu_contentDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_resourceDomain;
import cn.edu.zjut.cfg.domain.Zjut_resource_typeDomain;
import cn.edu.zjut.cfg.service.Zjut_menu_contentService;

public class Menu extends DefaultAction {
	private Zjut_menu_contentDomain zjut_menu_contentDomain;

	private IbatisPage page = new IbatisPage();

	private static Zjut_organizationDomain organizationDomain;
	private static Zjut_resource_typeDomain resource_typeDomain;
	static {
		organizationDomain = new Zjut_organizationDomain();
		organizationDomain.setName("international-college");
		organizationDomain.loadEqual();

		resource_typeDomain = new Zjut_resource_typeDomain();
		resource_typeDomain.setName("course");
		resource_typeDomain.loadEqual();
	}

	public String about_college() {
		getMenuDomain("about_college");
		return super.SUCCESS;
	}

	public String major() {
		getMenuDomain("major");
		return super.SUCCESS;
	}

	public String recruit_students() {
		Pattern ptn = Pattern.compile("recruit_stu_(\\d{4})", Pattern.CASE_INSENSITIVE);
		getMenuDomain("recruit_students");
		Map yearMap = new HashMap();
		List childs = this.zjut_menu_contentDomain.getChildList();
		for (int i = 0; i < childs.size(); i++) {
			Zjut_menu_contentDomain domain = (Zjut_menu_contentDomain) childs.get(i);
			String menu_name = domain.getMenu_name();
			Matcher match = ptn.matcher(menu_name);
			if( match.matches()){
				String year = match.group(1);
				yearMap.put(year, domain);
			}
		}
		
		Object[] keys = yearMap.keySet().toArray();  
		Arrays.sort(keys);
		
		List finalChilds = new ArrayList();
		for(int	i = keys.length-1;i >=0;i--){
			if(finalChilds.size()>=3){
				break;
			}
			finalChilds.add(yearMap.get(keys[i]));   
		}   
		zjut_menu_contentDomain.setChildList(finalChilds);
		return super.SUCCESS;
	}

	public String teach() {
		Zjut_organization_resourceDomain organization_resourceDomain = new Zjut_organization_resourceDomain();
		organization_resourceDomain.setOrganizationDomain(organizationDomain);
		organization_resourceDomain.setResourceTypeDomain(resource_typeDomain);
		page.setPagesize(10);
		page = organization_resourceDomain.getSelectPage(page);

		getMenuDomain("teach");
		return super.SUCCESS;
	}

	public String research() {
		getMenuDomain("research");
		return super.SUCCESS;
	}

	public String schoolfellow() {
		getMenuDomain("schoolfellow");
		return super.SUCCESS;
	}

	private void getMenuDomain(String menuName) {
		zjut_menu_contentDomain = Zjut_menu_contentService.instance
				.getMenuDomain(menuName, organizationDomain.getName());

	}

	public Zjut_menu_contentDomain getZjut_menu_contentDomain() {
		return zjut_menu_contentDomain;
	}

	public void setZjut_menu_contentDomain(
			Zjut_menu_contentDomain zjut_menu_contentDomain) {
		this.zjut_menu_contentDomain = zjut_menu_contentDomain;
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}
	
//	public static void main(String[] ben){
//		Pattern ptn = Pattern.compile("recruit_stu_(\\d{4})", Pattern.CASE_INSENSITIVE);
//		Zjut_menu_contentDomain zjut_menu_contentDomain = Zjut_menu_contentService.instance
//				.getMenuDomain("recruit_students", organizationDomain.getName());
//		Map yearMap = new HashMap();
//		List childs = zjut_menu_contentDomain.getChildList();
//		System.out.println(childs.size());
//		for (int i = 0; i < childs.size(); i++) {
//			Zjut_menu_contentDomain domain = (Zjut_menu_contentDomain) childs.get(i);
//			String menu_name = domain.getMenu_name();
//			Matcher match = ptn.matcher(menu_name);
//			if( match.matches()){
//				String year = match.group(1);
//				yearMap.put(year, domain);
//			}
//		}
//		
//		Object[] keys = yearMap.keySet().toArray();  
//		Arrays.sort(keys);
//		
//		List finalChilds = new ArrayList();
//		for(int	i = 0;i < keys.length;i++){   
//			finalChilds.add(yearMap.get(keys[i]));   
//		}   
//		System.out.println(finalChilds.size());
//	}
}
