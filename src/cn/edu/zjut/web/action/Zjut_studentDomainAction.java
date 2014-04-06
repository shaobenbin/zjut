package cn.edu.zjut.web.action;

import java.util.Date;

import org.sevenstar.app.common.context.ApplicationContext;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;

import cn.edu.zjut.cfg.domain.Zjut_studentDomain;
import cn.edu.zjut.cfg.service.Zjut_studentService;

@SSAction(name = "zjut_student")
public class Zjut_studentDomainAction extends DefaultAction {
	private Zjut_studentDomain zjut_studentDomain;

	public String delete() {
		if (zjut_studentDomain == null || zjut_studentDomain.getId() == null) {
			throw new RuntimeException("缺少必要的参数，不能删除学生风采内容!");
		}

		zjut_studentDomain.loadEqual();
		zjut_studentDomain.delete();
		Zjut_studentService.instance.load();
		return "删除[" + zjut_studentDomain.getName() + "]成功!";
	}

	public String insertOrUpdate() {
		if (zjut_studentDomain == null) {
			throw new RuntimeException("缺少必要的参数，不能编辑学生风采内容!");
		}

		zjut_studentDomain.setModify_date(new Date());
		zjut_studentDomain.setModifyUserDomain(ApplicationContext.get());
		zjut_studentDomain.insertOrUpdate();
		Zjut_studentService.instance.load();
		return "编辑[" + zjut_studentDomain.getName() + "]成功!";
	}

	public Zjut_studentDomain getZjut_studentDomain() {
		return zjut_studentDomain;
	}

	public void setZjut_studentDomain(Zjut_studentDomain zjut_studentDomain) {
		this.zjut_studentDomain = zjut_studentDomain;
	}

}
