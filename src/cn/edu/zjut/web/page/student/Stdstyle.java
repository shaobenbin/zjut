package cn.edu.zjut.web.page.student;

import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_studentDomain;

public class Stdstyle extends DefaultAction {
	private Long id;
	private Zjut_studentDomain zjut_studentDomain;

	public String detail() {
		if (id == null) {
			throw new RuntimeException("缺少参数，不能查看!");
		}
		zjut_studentDomain = new Zjut_studentDomain();

		zjut_studentDomain.setId(id);
		try {
			zjut_studentDomain.loadEqual();
		} catch (RuntimeException e) {
			throw new RuntimeException("找不到对应的内容!");
		}

		return super.SUCCESS;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Zjut_studentDomain getZjut_studentDomain() {
		return zjut_studentDomain;
	}

	public void setZjut_studentDomain(Zjut_studentDomain zjut_studentDomain) {
		this.zjut_studentDomain = zjut_studentDomain;
	}

}
