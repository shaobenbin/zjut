package org.sevenstar.app.common.interceptor;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.common.exception.UnLoginException;
import org.sevenstar.app.monitor.service.S_monitor_exceptionService;
import org.sevenstar.persistent.db.exception.PersistentException;
import org.sevenstar.web.action.Action;
import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.cfg.SwebConfigure;
import org.sevenstar.web.cfg.model.ResultTypeModel;
import org.sevenstar.web.cfg.model.RuleModel;
import org.sevenstar.web.context.WebContext;
import org.sevenstar.web.interceptor.Interceptor;
import org.sevenstar.web.invocation.Invocation;
import org.sevenstar.web.view.AjaxResult;

/**
 * @author rtm 2008-5-8
 */
public class ExceptionInterceptor implements Interceptor {

	private Log LOG = LogFactory.getLog(ExceptionInterceptor.class);

	private String getAjaxResultName() {
		List resultList = SwebConfigure.getSwebModel().getResultTypesModel()
				.getResultTypesList();
		for (int i = 0; i < resultList.size(); i++) {
			ResultTypeModel rtm = (ResultTypeModel) resultList.get(i);
			if (AjaxResult.class.getName().equals(rtm.getClassName())) {
				return rtm.getName();
			}
		}
		return null;
	}

	private boolean isAjaxCall() {
		String url = WebContext.getUrl();
		if (url.indexOf("?") != -1) {
			url = url.substring(0, url.indexOf("?"));
		}
		RuleModel ruleModel = SwebConfigure.getUrlModel(url);
		if (ruleModel.getResultType().equals(getAjaxResultName())) {
			return true;
		}
		return false;
	}

	public Object intercept(Invocation invocation) {
		LOG.debug("interceptor[ExceptionInterceptor]:before call ");
		Object result = "";
		try {
			result = invocation.invoke();
		} catch (UnLoginException e) {
			if (isAjaxCall()) {
				return "提示:超时,请到首页登录";
			} else {
				if (invocation.getAction() instanceof DefaultAction) {
					((DefaultAction) invocation.getAction())
							.setMsg("超时,请到首页登录");
				}
				return Action.ERROR;
			}
		} catch (SecurityException e) {
			if (isAjaxCall()) {
				return "提示:无权限";
			} else {
				if (invocation.getAction() instanceof DefaultAction) {
					if (e.getMessage() != null) {
						((DefaultAction) invocation.getAction()).setMsg(e
								.getMessage());
					} else {
						((DefaultAction) invocation.getAction()).setMsg("无权限");
					}
				}
				return Action.ERROR;
			}
		} catch (Throwable e) {
			Throwable trowable = e;
			while (trowable.getCause() != null && trowable.getCause().getMessage() != null && !"".equals(trowable.getCause().getMessage())) {
				if(trowable instanceof ApplicationException){
					break;
				}
				if (trowable instanceof InvocationTargetException) {
					trowable = ((InvocationTargetException) trowable)
							.getTargetException();
				}
				if (trowable.getCause() != null) {
					trowable = trowable.getCause();
				}
				 
			}
			String msg = trowable.getMessage();
			if (trowable instanceof PersistentException
					|| (msg != null && msg.indexOf("PersistentException") != -1)) {
				msg = "数据库操作错误";
			}
		 
			if (msg != null) {
				msg = msg.replaceAll("\r", "");
				msg = msg.replaceAll("\n", "");
				msg = msg.replaceAll("\t", "");
				msg = msg.replaceAll("\"", "'");
			/*	if(msg.startsWith("org.sevenstar.app.exception.ApplicationException:")){
					msg = msg.substring("org.sevenstar.app.exception.ApplicationException:".length());
				}*/
			}
			if(msg == null || "".equals(msg)){
				msg = "出错了";
			}
			LOG.error(e);
			e.printStackTrace();
			if (e instanceof ApplicationException) {
				if (((ApplicationException) e).isNeedLog()) {
					S_monitor_exceptionService.instance.log(e);
				}
			}else if(e.getCause() != null && (e.getCause() instanceof ApplicationException)){
				if (((ApplicationException) (e.getCause())).isNeedLog()) {
					S_monitor_exceptionService.instance.log(e);
				}
			}else {
				S_monitor_exceptionService.instance.log(e);
			}
			if (isAjaxCall()) {
				return "提示:" + msg;
			} else {
				if (invocation.getAction() instanceof DefaultAction) {
					((DefaultAction) invocation.getAction()).setMsg(msg);
				}
				return Action.ERROR;
			}
		}
		LOG.debug("interceptor[ExceptionInterceptor]:after call ");
		return result;
	}
	
	 

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

}
