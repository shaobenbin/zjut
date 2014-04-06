package org.sevenstar.app.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    private CookieUtil(){}
    public static String getCookieValue(HttpServletRequest request, String cookieName,
            String defaultValue) {
        Cookie cookieList[] = request.getCookies();
        if (cookieList == null || cookieName == null)
            return defaultValue;
        for (int i = 0; i < cookieList.length; i++) {
            try {
                if (cookieList[i].getName().equals(cookieName)) {
                	if (cookieList[i].getValue()==null || "null".equals(cookieList[i].getValue())) {
                		return null;
                	} else {
                		return URLDecoder.decode(cookieList[i].getValue(), "UTF8");
                	}
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return defaultValue;
    }

    public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue)
            throws UnsupportedEncodingException {
        Cookie theCookie = new Cookie(URLEncoder.encode(cookieName, "UTF8"),
                URLEncoder.encode(cookieValue, "UTF8"));
        theCookie.setPath("/");
        response.addCookie(theCookie);
    }

    public static void setCookie(HttpServletResponse response, String cookieName,
            String cookieValue, int cookieMaxage){
        Cookie theCookie;
		try {
			theCookie = new Cookie(URLEncoder.encode(cookieName, "UTF8"),
			cookieValue==null?null:URLEncoder.encode(cookieValue, "UTF8"));
			theCookie.setMaxAge(cookieMaxage);
		    response.addCookie(theCookie);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
    
}
