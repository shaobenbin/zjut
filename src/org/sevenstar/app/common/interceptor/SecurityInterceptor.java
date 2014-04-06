package org.sevenstar.app.common.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sevenstar.app.common.context.ApplicationContext;
import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.manager.domain.S_userDomain;
import org.sevenstar.app.module.domain.S_moduleDomain;
import org.sevenstar.util.RegexpHelper;
import org.sevenstar.web.Constants;
import org.sevenstar.web.context.WebContext;
import org.sevenstar.web.interceptor.Interceptor;
import org.sevenstar.web.invocation.Invocation;

/**
 * 权限控制
 * @author rtm 2008-5-10
 */
public class SecurityInterceptor implements Interceptor {
	private Map paramMap;

	public Map getParamMap() {
		if (paramMap == null) {
			paramMap = new HashMap();
		}

		return paramMap;
	}

	public void setParamMap(Map map) {
		this.paramMap = map;
	}

	public Object intercept(Invocation invocation) {
		String excludeUrls = (String) getParamMap().get("excludeUrls");
		String url = WebContext.getUrl();
		if(url.indexOf("?") != -1){
			url = url.substring(0,url.indexOf("?"));
		}
		if (excludeUrls == null || "".equals(excludeUrls)) {
			return checkPermission(invocation,url);
		} else {
			if (excludeUrls.indexOf(Constants.separator) == -1) {
				if (!RegexpHelper.isGlobmatches(url, excludeUrls)) {
					return checkPermission(invocation,url);
				} else {
					return invocation.invoke();
				}
			} else {
				String[] excludeUrlss = excludeUrls.split(Constants.separator);
				boolean shouldCheck = true;
				for (int i = 0; i < excludeUrlss.length; i++) {
					if (RegexpHelper.isGlobmatches(url, excludeUrlss[i])) {
						shouldCheck = false;
					}
				}
				if (shouldCheck) {
					return checkPermission(invocation,url);
				} else {
					return invocation.invoke();
				}
			}
		}
	}

	private Object checkPermission(Invocation invocation,String url) {
		if (ApplicationContext.get() == null) {
			throw new ApplicationException("超时,请到首页登录");
		} else {
			if(!hasPermission(url)){
				throw new ApplicationException("没有权限");
			}
			return invocation.invoke();
		}
	}

	private boolean hasPermission(String url) {
		S_userDomain userDomain = ApplicationContext.get();
		S_moduleDomain paramModuleDomain = new S_moduleDomain();
		List moduleList = paramModuleDomain.selectAll();
		S_moduleDomain moduleDomain = null;
		for (int i = 0; i < moduleList.size(); i++) {
			S_moduleDomain domain = (S_moduleDomain) moduleList.get(i);
            if(domain.getPattern() != null && !"".equals(domain.getPattern()) && RegexpHelper.isGlobmatches(url, domain.getPattern())){
            	if(moduleDomain == null){
            		moduleDomain = domain;
            	}else{
            		if(moduleDomain.getLvl().longValue() < domain.getLvl().longValue()){
            			/**
            			 * 取最符合的模块
            			 */
            			moduleDomain = domain;
            		}
            	}
            }
		}
		if(moduleDomain == null){
			/**
			 * 如果没有找到模块，则为没有配置，默认返回有权限
			 */
			return true;
		}
		return userDomain.hasPermission(moduleDomain);
	}
}
