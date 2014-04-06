package org.sevenstar.app.common.context;

import org.sevenstar.app.common.exception.UnLoginException;
import org.sevenstar.app.manager.domain.S_userDomain;
import org.sevenstar.web.context.WebContext;
import org.sevenstar.component.cache.ehcache.EHCacheHelper;
import org.sevenstar.persistent.db.ibatis.IbatisDao;
import org.sevenstar.util.CookieHelper;
import org.sevenstar.component.security.AES;

/**
 * @author rtm 2008-5-8 封装对session操作
 */
public class ApplicationContext {
	public static void put(S_userDomain userDomain) {
 
		if (!EHCacheHelper.exists("login")) {
			EHCacheHelper.addCache("login");
			EHCacheHelper.getCache("login").getCacheConfiguration()
					.setTimeToIdleSeconds(1800);
			EHCacheHelper.getCache("login").getCacheConfiguration()
					.setTimeToLiveSeconds(1800);
			EHCacheHelper.getCache("login").getCacheConfiguration()
					.setMaxElementsInMemory(500000);
			EHCacheHelper.getCache("login").getCacheConfiguration()
					.setDiskPersistent(false);

		}
 		EHCacheHelper.put("login", userDomain.getId(), userDomain);
 		/**
		 * 第一次以数据库为准
		 */
		long currentTime = IbatisDao.getCurrentDBTimeInMillis();
		String value = userDomain.getId() + "___" + currentTime;
		value = AES.Encrypt(value);
		String path = WebContext.getContextPath();
		if("".equals(path) || !path.endsWith("/")){
			path = path + "/";
		}
	//	CookieHelper.setPath(path);
		CookieHelper.setAttribute("login", value, WebContext.getResponse());
	}

	/*
	 * 登出操作
	 */
	public static void remove() {
		String path = WebContext.getContextPath();
		if("".equals(path) || !path.endsWith("/")){
			path = path + "/";
		}
	//	CookieHelper.setPath(path);
		CookieHelper.setAttribute("login", "", WebContext.getResponse());
	}

	public static S_userDomain get() {
		if (hasLogin()) {
			String value = CookieHelper.getAttribute("login", WebContext
					.getRequest());
			value = AES.Decrypt(value);
			String loginid = value.substring(0, value.indexOf("___"));
			if (EHCacheHelper.get("login", loginid) != null) {
				return (S_userDomain) EHCacheHelper.get("login", loginid);
			} else {
				/**
				 * 根据id到数据库load
				 */
				S_userDomain param = new S_userDomain();
				param.setId(Long.valueOf(loginid));
				try {
					param.loadEqual();
				} catch (Exception e) {
 					throw new UnLoginException();
				}
				EHCacheHelper.put("login", param.getId(), param);
				return param;
			}
		}
		throw new UnLoginException();
	}

	public static boolean hasLogin() {
		if (WebContext.getRequest() == null) {
 			return false;
		}
		String value = CookieHelper.getAttribute("login", WebContext
				.getRequest());
		if (value == null || "".equals(value)
				|| value.toLowerCase().startsWith("null")) {
 			return false;
		}
		try {
			value = AES.Decrypt(value);
		} catch (Exception e) {
 			return false;
		}
		if (value == null || "".equals(value) || value.indexOf("___") == -1) {
 			return false;
		}
		String loginid = value.substring(0, value.indexOf("___"));
		String logintime = value.substring(value.indexOf("___")
				+ "___".length());
		if (loginid == null || "".equals(loginid) || logintime == null
				|| "".equals(logintime)
				|| loginid.toLowerCase().startsWith("null")) {
 			return false;
		}
		try {
			long logintimel = Long.parseLong(logintime);
			long currenttime = System.currentTimeMillis();
			/**
			 * 判断登录时间，第一次判断本机时间，误差2分钟之内，算通过
			 */
			if ((currenttime < logintimel && (logintimel - currenttime) > 2 * 60 * 1000)
					|| currenttime - logintimel > 1800 * 1000) {
				currenttime = IbatisDao.getCurrentDBTimeInMillis();
				if (currenttime < logintimel
						|| currenttime - logintimel > 1800 * 1000) {
					 
					return false;
				}
			}
			String newValue = loginid + "___" + currenttime;
			/**
			 * 刷新登录时间
			 */
			if (WebContext.getRequest()
					.getAttribute("HASSETCOOKIE_APPLICATION") == null) {
				String path = WebContext.getContextPath();
				if("".equals(path) || !path.endsWith("/")){
					path = path + "/";
				}
			//	CookieHelper.setPath(path);
				CookieHelper.setAttribute("login", AES.Encrypt(newValue),
						WebContext.getResponse());
				WebContext.getRequest().setAttribute(
						"HASSETCOOKIE_APPLICATION", "yes");
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}

