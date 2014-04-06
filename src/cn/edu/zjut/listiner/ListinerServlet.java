package cn.edu.zjut.listiner;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.edu.zjut.cfg.Configure;

public class ListinerServlet implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		Configure.instance.reload();
	}

}
