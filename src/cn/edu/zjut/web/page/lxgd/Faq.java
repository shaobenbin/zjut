package cn.edu.zjut.web.page.lxgd;

import java.util.List;

import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_faqsDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.service.Zjut_faqsService;

public class Faq extends DefaultAction {

	private Long id;

	private static Zjut_organizationDomain organizationDomain;
	static {
		organizationDomain = new Zjut_organizationDomain();
		organizationDomain.setName("study-abroad");
		organizationDomain.loadEqual();
	}

	private Zjut_faqsDomain zjut_faqsDomain;

	public String detail() {

		if (id == null) {
			throw new RuntimeException("缺少公告编号，不能查看");
		}

		List<Zjut_faqsDomain> faqList = Zjut_faqsService.instance
				.getFaqsList(organizationDomain.getName());
		for (Zjut_faqsDomain faqDomain : faqList) {
			if (id.equals(faqDomain.getId())) {
				zjut_faqsDomain = faqDomain;
				break;
			}
		}
		return super.SUCCESS;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Zjut_faqsDomain getZjut_faqsDomain() {
		return zjut_faqsDomain;
	}

	public void setZjut_faqsDomain(Zjut_faqsDomain zjut_faqsDomain) {
		this.zjut_faqsDomain = zjut_faqsDomain;
	}

}
