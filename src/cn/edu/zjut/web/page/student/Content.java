package cn.edu.zjut.web.page.student;

import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_contentDomain;

public class Content extends DefaultAction {
	private Long id;

	private Zjut_contentDomain zjut_contentDomain;

	public String detail() {
		if (id == null) {
			throw new RuntimeException("缺少参数，不能查看!");
		}

		zjut_contentDomain = new Zjut_contentDomain();
		zjut_contentDomain.setId(id);
		try {
			zjut_contentDomain.loadEqual();
		} catch (RuntimeException e) {
			throw new RuntimeException("没有指定的内容可以查看!");
		}
		return super.SUCCESS;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Zjut_contentDomain getZjut_contentDomain() {
		return zjut_contentDomain;
	}

	public void setZjut_contentDomain(Zjut_contentDomain zjut_contentDomain) {
		this.zjut_contentDomain = zjut_contentDomain;
	}

}
