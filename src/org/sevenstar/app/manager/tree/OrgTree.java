package org.sevenstar.app.manager.tree;

import java.util.List;

import org.sevenstar.app.manager.domain.S_orgDomain;
import org.sevenstar.app.manager.service.S_orgService;
import org.sevenstar.component.tag.tree.Tree;
/**
 * @author rtm 2008-5-8
 */
public class OrgTree extends Tree{

	public void getChildren(String id) {
        S_orgService s_orgService = new S_orgService();
        if("null".equals(id) || id == null || "".equals(id) || "-1".equals(id) || "0".equals(id)){
        	List firstLevelList = s_orgService.selectFirstLevelList();
            for(int i=0;i<firstLevelList.size();i++){
            	S_orgDomain domain = (S_orgDomain)firstLevelList.get(i);
                if(domain.getChildList().size() > 0){
                	entity.addBranh(String.valueOf(domain.getId()),domain.getName(),null);
                }else{
                	entity.addLeaf(String.valueOf(domain.getId()),domain.getName(),null);
                }
            }
        }else{
        	S_orgDomain orgDomain = new S_orgDomain();
        	orgDomain.setId(Long.valueOf(id));
        	orgDomain.load();
        	List childList = orgDomain.getChildList();
        	for(int i=0;i<childList.size();i++){
        		S_orgDomain domain = (S_orgDomain)childList.get(i);
                if(domain.getChildList().size() > 0){
                	entity.addBranh(String.valueOf(domain.getId()),domain.getName(),null);
                }else{
                	entity.addLeaf(String.valueOf(domain.getId()),domain.getName(),null);
                }
        	}
        }
	}

}
