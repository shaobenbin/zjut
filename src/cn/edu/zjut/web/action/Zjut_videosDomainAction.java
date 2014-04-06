package cn.edu.zjut.web.action;

import java.util.Date;

import org.sevenstar.app.common.context.ApplicationContext;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;

import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.domain.Zjut_organization_videoDomain;
import cn.edu.zjut.cfg.domain.Zjut_videosDomain;
import cn.edu.zjut.cfg.service.Zjut_studentService;
import cn.edu.zjut.cfg.service.Zjut_videoService;

@SSAction(name = "zjut_videos")
public class Zjut_videosDomainAction extends DefaultAction {

	private Zjut_videosDomain zjut_videosDomain;
	private Zjut_organizationDomain zjut_organizationDomain;

	public String delete() {
		if (zjut_videosDomain == null || zjut_videosDomain.getId() == null) {
			throw new RuntimeException("缺少必要的参数，不能删除学生风采内容!");
		}

		zjut_videosDomain.loadEqual();
		
		Zjut_organization_videoDomain organization_videoDomain = new Zjut_organization_videoDomain();
		organization_videoDomain.setVideosDomain(zjut_videosDomain);
		organization_videoDomain.loadEqual();
		organization_videoDomain.delete();
		zjut_videosDomain.delete();
		
		Zjut_videoService.instance.reload();
		return "删除[" + zjut_videosDomain.getTitle() + "]成功!";
	}

	public String insertOrUpdate() {
		if (zjut_videosDomain == null || zjut_organizationDomain == null || zjut_organizationDomain.getId() == null) {
			throw new RuntimeException("缺少必要的参数，不能编辑学生风采内容!");
		}

		Zjut_organization_videoDomain zjut_organization_videoDomain = null;
		if (zjut_videosDomain.getId() == null) {
			zjut_organizationDomain.loadEqual();
			zjut_organization_videoDomain = new Zjut_organization_videoDomain();
			zjut_organization_videoDomain.setOrganizationDomain(zjut_organizationDomain);
		}

		zjut_videosDomain.setModify_date(new Date());
		zjut_videosDomain.setModifyUserDomain(ApplicationContext.get());
		zjut_videosDomain.insertOrUpdate();
		Zjut_studentService.instance.load();

		if (zjut_organization_videoDomain != null) {
			zjut_organization_videoDomain.setVideosDomain(zjut_videosDomain);
			zjut_organization_videoDomain.setModify_date(new Date());
			zjut_organization_videoDomain.setModifyUserDomain(ApplicationContext.get());
			zjut_organization_videoDomain.insert();
		}
		
		Zjut_videoService.instance.reload();
		return "编辑[" + zjut_videosDomain.getTitle() + "]成功!";
	}

	public Zjut_videosDomain getZjut_videosDomain() {
		return zjut_videosDomain;
	}

	public void setZjut_videosDomain(Zjut_videosDomain zjut_videosDomain) {
		this.zjut_videosDomain = zjut_videosDomain;
	}

	public Zjut_organizationDomain getZjut_organizationDomain() {
		return zjut_organizationDomain;
	}

	public void setZjut_organizationDomain(Zjut_organizationDomain zjut_organizationDomain) {
		this.zjut_organizationDomain = zjut_organizationDomain;
	}

}
