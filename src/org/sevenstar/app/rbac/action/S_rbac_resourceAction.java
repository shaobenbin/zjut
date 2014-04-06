package org.sevenstar.app.rbac.action;

import java.util.List;

import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;
import org.sevenstar.web.annotation.SSList;
import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.rbac.domain.S_rbac_resourceDomain;
 @SSAction(name="s_rbac_resource")
public class S_rbac_resourceAction extends DefaultAction   {
	private S_rbac_resourceDomain s_rbac_resourceDomain;
 
	@SSList(type = "org.sevenstar.app.rbac.domain.S_rbac_resourceDomain")
	private List resourceList;

	public String deleteList() throws Exception {
		if (resourceList != null && resourceList.size() > 0) {
			for (int i = 0; i < resourceList.size(); i++) {
				S_rbac_resourceDomain s_rbac_resourceDomain = (S_rbac_resourceDomain) resourceList
						.get(i);
				s_rbac_resourceDomain.delete();
			}
		} else {
			throw new ApplicationException("请先选择资源");
		}
		return "批量删除资源成功";
	}

	public String delete() throws Exception {
		super.setMsg("删除资源[" + s_rbac_resourceDomain.getName() + "]成功");
		s_rbac_resourceDomain.delete();
		super.setNextUrl("app/rbac/resource/list.html");
		return super.REDIRECT;
	}

	public String insert() throws Exception {
		s_rbac_resourceDomain.insert();
		super.setMsg("添加资源[" + s_rbac_resourceDomain.getName() + "]成功");
		super.setNextUrl("app/rbac/resource/list.html");
		return super.REDIRECT;
	}

	public String update() throws Exception {
		s_rbac_resourceDomain.update();
		super.setMsg("修改资源[" + s_rbac_resourceDomain.getName() + "]成功");
		super.setNextUrl("app/rbac/resource/list.html");
		return super.REDIRECT;
	}

	public String insertOrUpdate() throws Exception {
		s_rbac_resourceDomain.insertOrUpdate();
		return "编辑资源[" + s_rbac_resourceDomain.getName() + "]成功";
	}
	public S_rbac_resourceDomain getS_rbac_resourceDomain() {
		return s_rbac_resourceDomain;
	}
	public void setS_rbac_resourceDomain(S_rbac_resourceDomain s_rbac_resourceDomain) {
		this.s_rbac_resourceDomain = s_rbac_resourceDomain;
	}

 
	public List getResourceList() {
		return resourceList;
	}

	public void setResourceList(List resourceList) {
		this.resourceList = resourceList;
	}


}
