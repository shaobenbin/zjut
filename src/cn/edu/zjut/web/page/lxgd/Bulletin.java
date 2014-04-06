package cn.edu.zjut.web.page.lxgd;

import java.util.List;

import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_bulletinDomain;
import cn.edu.zjut.cfg.domain.Zjut_organizationDomain;
import cn.edu.zjut.cfg.service.Zjut_bulletinService;

public class Bulletin extends DefaultAction {

	private Long id;

	private Zjut_bulletinDomain zjut_bulletinDomain;

	private static Zjut_organizationDomain organizationDomain;
	static {
		organizationDomain = new Zjut_organizationDomain();
		organizationDomain.setName("study-abroad");
		organizationDomain.loadEqual();
	}

	public String detail() {
		if (id == null) {
			throw new RuntimeException("缺少公告编号，不能查看");
		}

		List<Zjut_bulletinDomain> bulletinList = Zjut_bulletinService.instance
				.getBulletinList(organizationDomain.getName());
		for (Zjut_bulletinDomain bulletinDomain : bulletinList) {
			if (id.compareTo(bulletinDomain.getId()) == 0) {
				zjut_bulletinDomain = bulletinDomain;
				try {
					zjut_bulletinDomain.setBrowse_count(zjut_bulletinDomain
							.getBrowse_count() == null ? 1
							: zjut_bulletinDomain.getBrowse_count() + 1);

					zjut_bulletinDomain.update();
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
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

	public Zjut_bulletinDomain getZjut_bulletinDomain() {
		return zjut_bulletinDomain;
	}

	public void setZjut_bulletinDomain(Zjut_bulletinDomain zjut_bulletinDomain) {
		this.zjut_bulletinDomain = zjut_bulletinDomain;
	}

}
