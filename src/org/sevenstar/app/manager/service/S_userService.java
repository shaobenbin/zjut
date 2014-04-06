package org.sevenstar.app.manager.service;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.app.manager.domain.S_userDomain;
import org.sevenstar.component.security.MD5;
import org.sevenstar.persistent.db.exception.PersistentException;
import org.sevenstar.persistent.db.id.IDGeneratorFactory;
import org.sevenstar.util.BeanHelper;

/**
 * @author rtm 2008-5-8
 */
public class S_userService {

	public boolean login(S_userDomain userDomain) {
		S_userDomain paramDomain = new S_userDomain();
		paramDomain.setName(userDomain.getName());
		try {
			paramDomain.loadEqual();
		} catch (PersistentException e) {
			e.printStackTrace();
			throw new ApplicationException("此用户名[" + userDomain.getName()
					+ "]不存在");
		} catch (Exception e) {
			e.printStackTrace();
            throw new ApplicationException(e);
		}
		if (paramDomain.getPasswd() == null
				|| "".equals(paramDomain.getPasswd().trim())) {
			if (userDomain.getPasswd() == null
					|| "".equals(userDomain.getPasswd())) {
				BeanHelper.copy(paramDomain, userDomain);
				userDomain.setLoginid(String.valueOf(IDGeneratorFactory.generateUUidHex()));
				userDomain.updateLogindate();
				return true;
			}
		} else {
			MD5 md5 = new MD5();
			if (paramDomain.getPasswd().equals(md5.getMD5ofStr(userDomain.getPasswd()))) {
				BeanHelper.copy(paramDomain, userDomain);
				userDomain.setLoginid(String.valueOf(IDGeneratorFactory.generateUUidHex()));
				userDomain.updateLogindate();
				return true;
			}
		}
		throw new ApplicationException("密码不正确");
	}
	
	public static void main(String[] ben){
		MD5 md5 = new MD5();
		System.out.println(md5.getMD5ofStr("123"));
	}
	
}
