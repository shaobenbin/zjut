package org.sevenstar.app.monitor.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sevenstar.app.common.context.ApplicationContext;
import org.sevenstar.app.config.service.S_configureService;
import org.sevenstar.app.module.domain.S_moduleDomain;
import org.sevenstar.app.monitor.domain.S_monitor_exceptionDomain;
import org.sevenstar.app.monitor.domain.S_monitor_exception_detailDomain;
import org.sevenstar.persistent.db.ibatis.MyAppSqlConfig;
import org.sevenstar.web.context.WebContext;
import org.stringtree.json.JSONWriter;

public class S_monitor_exceptionService {
	private static Log LOG = LogFactory
			.getLog(S_monitor_exceptionService.class);
	public static S_monitor_exceptionService instance = new S_monitor_exceptionService();

	public static void main(String[] args) {
		String num = null;
		try {
			num.indexOf("d");
		} catch (Exception e) {
			S_monitor_exceptionService.instance.log(e);
		}
	}

	public void log(String message) {
		if ("true".equalsIgnoreCase(S_configureService.instance
				.get("monitor_login_expire"))
				|| "Y".equalsIgnoreCase(S_configureService.instance
						.get("monitor_login_expire"))) {
			String sql = "insert into s_monitor_exception (id,message,url,create_date)"
					+ "values(seq_monitor.nextval,sysdate,?,?,sysdate)";
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				conn = MyAppSqlConfig.getSqlMapInstance().getDataSource()
						.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, message);
				ps.setString(2, WebContext.getUrl());
				ps.execute();
				if (!conn.getAutoCommit()) {
					conn.commit();
				}
			} catch (RuntimeException e) {
				LOG.error(e);
			} catch (Exception e) {
				LOG.error(e);
			} finally {
				try {
					if (ps != null) {
						ps.close();
						ps = null;
					}
				} catch (Exception e1) {
					LOG.error(e1);
				}
				try {
					if (conn != null && !conn.isClosed()) {
						conn.close();
						conn = null;
					}
				} catch (Exception e1) {
					LOG.error(e1);
				}
			}
		}
	}

	public void log(Throwable exception) {
		if (exception == null || exception.getCause() == null) {
			// pass
			return;
		}
		try {
			if ("true".equalsIgnoreCase(S_configureService.instance
					.get("monitor_error"))
					|| "Y".equalsIgnoreCase(S_configureService.instance
							.get("monitor_error"))) {
				S_monitor_exceptionDomain domain = new S_monitor_exceptionDomain();
				domain.setMessage(exception.getMessage());
				domain.setUrl(WebContext.getUrl());
				if (ApplicationContext.hasLogin()) {
					domain.setUserDomain(ApplicationContext.get());
				}
				if (WebContext.getUrl() != null) {
					S_moduleDomain moduleDomain = (new S_moduleDomain())
							.getS_moduleDomain(WebContext.getUrl());
					domain.setModuleDomain(moduleDomain);
				}
				domain.insert();
				S_monitor_exception_detailDomain detailDomain = new S_monitor_exception_detailDomain();
				StringBuffer sb = new StringBuffer();
				Throwable trowable = exception;
				while (trowable.getCause() != null) {
					if (trowable instanceof InvocationTargetException) {
						trowable = ((InvocationTargetException) trowable)
								.getTargetException();
					}
					trowable = trowable.getCause();
				}
				StackTraceElement stack[] = trowable.getStackTrace();
				if (stack != null) {
					for (int i = 0; i < stack.length; i++) {
						StackTraceElement ste = (StackTraceElement) stack[i];
						String str = ste.getClassName() + ","
								+ ste.getMethodName() + "[" + ste.getFileName()
								+ ":" + ste.getLineNumber() + "]<br>";
						sb.append(str);
					}
				}
				detailDomain.setStacktrace(sb.toString());
				detailDomain.setMainDomain(domain);
				/**
				 * 取请求parameter
				 */
				if (WebContext.getRequestParameterMap() != null
						&& WebContext.getRequestParameterMap().size() > 0) {
					JSONWriter jsonWriter = new JSONWriter();
					detailDomain.setRequest(jsonWriter.write(WebContext
							.getRequestParameterMap()));
				}
				detailDomain.insert();
			}
		} catch (Throwable e) {
			LOG.error(e);
		}
	}
}
