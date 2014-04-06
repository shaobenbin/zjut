package org.sevenstar.app.resource.service;

import java.util.Map;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.resource.domain.S_resourceDomain;
import org.sevenstar.app.resource.domain.S_resource_typeDomain;
import org.sevenstar.component.freemarker.FreemarkerHelper;
import org.sevenstar.persistent.db.exception.PersistentException;
import org.sevenstar.persistent.db.ibatis.IbatisDao;
import org.sevenstar.persistent.db.ibatis.IbatisPage;

public class S_resourceService {

	public static S_resourceService instance = new S_resourceService();

	public IbatisPage getMaxVersionPage(IbatisPage page, S_resourceDomain domain) {
		if (page == null) {
			page = new IbatisPage();
		}
		page
				.setCountSqlMapId("S_resourceDomain_exp_maxversion_page_select_count");
		page.setQuerySqlMapId("S_resourceDomain_exp_maxversion_page_select");
		page.setQueryParam(S_resourceDomain.class);
		page.getPageDataList(domain);
		return page;
	}

	public S_resourceDomain getSecondVersionDomain(S_resourceDomain domain) {
		if (domain == null) {
			throw new ApplicationException("必须指定一个资源");
		}
		if (domain.getName() == null || "".equals(domain.getName())) {
			if (domain.getId() != null) {
				domain.load();
			} else {
				throw new ApplicationException("必须指定一个资源名称");
			}
		}
		return (S_resourceDomain) IbatisDao.getDao().queryForObject(
				"S_resourceDomain_exp_secondversion_select", domain);
	}

	public S_resourceDomain getMaxVersionDomain(String type_name, String name) {
		S_resource_typeDomain param = new S_resource_typeDomain();
		param.setName(type_name);
		try {
			param.loadEqual();
		} catch (PersistentException pe) {
			throw new ApplicationException("未找到资源类型[" + type_name + "]");
		}
		return getMaxVersionDomain(param, name);
	}

	public S_resourceDomain getMaxVersionDomain(String name) {
		S_resource_typeDomain param = new S_resource_typeDomain();
		return getMaxVersionDomain(param, name);
	}

	public S_resourceDomain getMaxVersionDomain(
			S_resource_typeDomain typeDomain, String name) {
		if (name == null || "".equals(name)) {
			throw new ApplicationException("必须指定一个资源名称");
		}
		S_resourceDomain param = new S_resourceDomain();
		param.setName(name);
		param.setTypeDomain(typeDomain);
		param.setIs_top("Y");
		try {
			param.loadEqual();
		} catch (PersistentException e) {
			throw new ApplicationException("未找到最高版本的资源[" + name + "]");
		}
		return param;
	}

	public Long getMaxVersion(S_resource_typeDomain typeDomain, String name) {
		return this.getMaxVersionDomain(typeDomain, name).getVersion();
	}

	public String include(String templateName, Map root) {
		S_resourceDomain templateResource = getMaxVersionDomain(templateName);
		if (templateResource.isFile()) {
			return FreemarkerHelper.process(templateResource.getPath(), root);
		} else {
			return FreemarkerHelper.process(templateResource.getContent(),
					templateResource.getContent(), root);
		}
	}
}
