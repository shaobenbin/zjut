package cn.edu.zjut.web.page.lxgd.en;

import java.util.List;

import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_newDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.service.Zjut_newService;

public class New extends DefaultAction {
	private Zjut_newDomain zjut_newDomain;
	private Long id;


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
			.getCommonNewsList(organizationDomain.getName(),"english");
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
}
