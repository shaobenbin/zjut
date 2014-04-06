package cn.edu.zjut.web.action;

import java.io.IOException;
import java.io.StringWriter;

import java.util.Map;

import org.sevenstar.web.action.java.DefaultAction;
import org.sevenstar.web.annotation.SSAction;

import cn.edu.zjut.cfg.bean.ApplicationDegreeBean;
import cn.edu.zjut.cfg.bean.ApplicationProgramBean;
import cn.edu.zjut.util.MailUtil;
import cn.edu.zjut.util.ObjectFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@SSAction(name = "zjut_application")
public class Zjut_applicationAction extends DefaultAction {

	private ApplicationProgramBean appProgramBean;

	private ApplicationDegreeBean appDegreeBean;

	public String send2Email() {
		if (appProgramBean == null) {
			throw new RuntimeException("缺少参数！");
		}
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(MailUtil.class, "");
		try {
			Template temp = cfg.getTemplate("application_program.ftl", "utf-8");
			StringWriter sw = new StringWriter();
			Map map = ObjectFormat.object2Map(appProgramBean);
			temp.process(map, sw);

			MailUtil mailUtil = new MailUtil("smtp.zjut.edu.cn",
					"iec@zjut.edu.cn", "zjut", "iec", "gjxy1514",
					"iec@zjut.edu.cn", "工大教育文件", sw.toString());
			mailUtil.send();
			return "send success!";
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "send fail!";
	}
	
	public String sendDegree2Email() {
		if (appDegreeBean == null) {
			throw new RuntimeException("缺少参数！");
		}
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(MailUtil.class, "");
		try {
			Template temp = cfg.getTemplate("application_degree.ftl", "utf-8");
			StringWriter sw = new StringWriter();
			Map map = ObjectFormat.object2Map(appDegreeBean);
			temp.process(map, sw);

			MailUtil mailUtil = new MailUtil("smtp.zjut.edu.cn",
					"iec@zjut.edu.cn", "zjut", "iec", "gjxy1514",
					"iec@zjut.edu.cn", "工大教育文件", sw.toString());
			mailUtil.send();
			return "send success!";
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "send fail!";
	}

	public ApplicationProgramBean getAppProgramBean() {
		return appProgramBean;
	}

	public void setAppProgramBean(ApplicationProgramBean appProgramBean) {
		this.appProgramBean = appProgramBean;
	}

	public ApplicationDegreeBean getAppDegreeBean() {
		return appDegreeBean;
	}

	public void setAppDegreeBean(ApplicationDegreeBean appDegreeBean) {
		this.appDegreeBean = appDegreeBean;
	}

}
