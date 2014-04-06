package org.sevenstar.component.tree.support;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.component.tree.domain.TreeDomain;

import net.jcreate.e3.tree.Node;
import net.jcreate.e3.tree.UserDataUncoder;
import net.jcreate.e3.tree.support.AbstractWebTreeModelCreator;
import net.jcreate.e3.tree.support.WebTreeDynamicNode;

/**
 * 动态子树的节点的设置
 * @author lzm
 *
 */
public class DynamicWebTreeModelCreator extends AbstractWebTreeModelCreator{

	protected Node createNode(Object menuData, UserDataUncoder uncoder){
		if(menuData instanceof TreeDomain){
			TreeDomain domain = (TreeDomain)menuData ;
			WebTreeDynamicNode result = new WebTreeDynamicNode(domain.getName(), "n_" + domain.getId());
			result.setSubTreeURL(domain.getSubTreeURL());
			if(domain.getAction() != null){
				result.setAction(domain.getAction());
			}
			result.setValue( domain.getId());
			result.setTarget(domain.getTarget());
			result.setAttribute("e3NodeId",((TreeDomain) menuData).getId());
			if(((TreeDomain) menuData).getNodeName() != null && !"".equals(((TreeDomain) menuData).getNodeName().trim())){
				result.setAttribute("name", ((TreeDomain) menuData).getNodeName());
			}else{
				result.setAttribute("name", "nodeIds");
			}
			result.setAttribute("isDisabled", ((TreeDomain) menuData).getIsDisabled());
			if(((TreeDomain) menuData).getIcon() != null && !"".equals(((TreeDomain) menuData).getIcon())){
				result.setIcon(((TreeDomain) menuData).getIcon());
			}
			if(((TreeDomain) menuData).getOpenIcon() != null && !"".equals(((TreeDomain) menuData).getOpenIcon())){
				result.setOpenIcon(((TreeDomain) menuData).getOpenIcon());
			}
			if(((TreeDomain) menuData).getIsRadio() != null && !"".equals(((TreeDomain) menuData).getIsRadio())){
				result.setAttribute("isRadio", ((TreeDomain) menuData).getIsRadio());
				
			}
			if(((TreeDomain) menuData).isSelected()){
				result.setSelected(true);
			}
			return result ;
		}
		throw new ApplicationException("创建节点错误,树中节点的数据类型错误!");
	}
}
