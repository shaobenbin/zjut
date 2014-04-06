package org.sevenstar.app.config.service;

 
import java.util.List;

import org.sevenstar.app.config.domain.S_configureDomain;
import org.sevenstar.app.config.domain.S_configure_typeDomain;
import org.sevenstar.component.cache.ehcache.EHCacheHelper;

public class S_configureService {
	
	public static S_configureService instance = new S_configureService();
	
	public String get(String key) {
		if (!EHCacheHelper.exists("S_configureDomain_map")) {
			EHCacheHelper.addCache("S_configureDomain_map");
			EHCacheHelper.getCache("S_configureDomain_map")
					.getCacheConfiguration().setTimeToIdleSeconds(60 * 60 * 24);
			EHCacheHelper.getCache("S_configureDomain_map")
					.getCacheConfiguration().setTimeToLiveSeconds(60 * 60 * 24);
			EHCacheHelper.getCache("S_configureDomain_map")
					.getCacheConfiguration().setMaxElementsInMemory(1000);
			EHCacheHelper.getCache("S_configureDomain_map")
					.getCacheConfiguration().setDiskPersistent(false);
		}
		if (EHCacheHelper.get("S_configureDomain_map", key) != null) {
			return (String) EHCacheHelper.get("S_configureDomain_map", key);
		} else {
			List list = (new S_configureDomain()).selectAll();
			for (int i = 0; i < list.size(); i++) {
				S_configureDomain domain = (S_configureDomain) list.get(i);
				EHCacheHelper.put("S_configureDomain_map", domain.getCode(),
						domain.getName());
			}
			return (String) EHCacheHelper.get("S_configureDomain_map", key);
		}
	}  
    
	public List getConfigureList(String key){
		S_configureDomain s_configureDomain = new S_configureDomain();
		S_configure_typeDomain s_configure_tyeDomain = new S_configure_typeDomain();
		s_configure_tyeDomain.setName(key);
		s_configure_tyeDomain.loadEqual();
		s_configureDomain.setTypeDomain(s_configure_tyeDomain);
		return s_configureDomain.selectEqual();
	}
	
}
