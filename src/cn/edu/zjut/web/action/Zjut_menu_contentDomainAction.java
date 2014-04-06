package cn.edu.zjut.web.action;

import java.util.Date;

import org.sevenstar.app.common.context.ApplicationContext;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;

import cn.edu.zjut.cfg.domain.Zjut_menu_contentDomain;
import cn.edu.zjut.cfg.service.Zjut_menu_contentService;

@SSAction(name = "zjut_menu_content")
public class Zjut_menu_contentDomainAction extends DefaultAction {

	private Zjut_menu_contentDomain zjut_menu_contentDomain;

	public String delete() {
		if (zjut_menu_contentDomain == null
				|| zjut_menu_contentDomain.getId() == null) {
			throw new RuntimeException("缺少参数，不能删除!");
		}

		zjut_menu_contentDomain.loadEqual();
		zjut_menu_contentDomain.delete();

		super.setMsg("删除菜单内容[" + zjut_menu_contentDomain.getMenu_display_name()
				+ "]成功");
		Zjut_menu_contentService.instance.reload();
		return "manager/menucontents/list.html";
	}

	public String insertOrUpdate() {
		if (zjut_menu_contentDomain == null) {
			throw new RuntimeException("缺少参数，不能编辑!");
		}

		zjut_menu_contentDomain.setModify_date(new Date());
		zjut_menu_contentDomain.setModifyUserDomain(ApplicationContext.get());
		zjut_menu_contentDomain.insertOrUpdate();
		Zjut_menu_contentService.instance.reload();
		return "编辑成功!";
	}

	public Zjut_menu_contentDomain getZjut_menu_contentDomain() {
		return zjut_menu_contentDomain;
	}

	public void setZjut_menu_contentDomain(
			Zjut_menu_contentDomain zjut_menu_contentDomain) {
		this.zjut_menu_contentDomain = zjut_menu_contentDomain;
	}

}
