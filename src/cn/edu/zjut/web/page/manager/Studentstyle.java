package cn.edu.zjut.web.page.manager;

import org.sevenstar.persistent.db.ibatis.IbatisPage;
import org.sevenstar.web.action.java.DefaultAction;

import cn.edu.zjut.cfg.domain.Zjut_studentDomain;

public class Studentstyle extends DefaultAction {
	private Zjut_studentDomain zjut_studentDomain;
	private IbatisPage page;
	
	public String list(){
		page = (new Zjut_studentDomain()).getSelectPage(page);
		return super.SUCCESS;
	}
	
	public String detail(){
		if(zjut_studentDomain == null || zjut_studentDomain.getId() == null){
			throw new RuntimeException("缺少必要的参数，不能查看学生风采内容!");
		}
		
		zjut_studentDomain.loadEqual();
		return super.SUCCESS;
	}
	
	public String edit(){
		if(zjut_studentDomain == null || zjut_studentDomain.getId() == null){
			throw new RuntimeException("缺少必要的参数，不能修改学生风采内容!");
		}
		
		zjut_studentDomain.loadEqual();
		return super.SUCCESS;
	}
	
	public String input(){
		return "manager/studentstyle/edit.html";
	}

	public Zjut_studentDomain getZjut_studentDomain() {
		return zjut_studentDomain;
	}

	public void setZjut_studentDomain(Zjut_studentDomain zjut_studentDomain) {
		this.zjut_studentDomain = zjut_studentDomain;
	}

	public IbatisPage getPage() {
		return page;
	}

	public void setPage(IbatisPage page) {
		this.page = page;
	}

}
