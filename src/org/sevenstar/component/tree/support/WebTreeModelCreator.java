package org.sevenstar.component.tree.support;

import net.jcreate.e3.tree.Node;
import net.jcreate.e3.tree.UserDataUncoder;
import net.jcreate.e3.tree.support.WebTreeNode;
import net.jcreate.e3.tree.support.AbstractWebTreeModelCreator;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.component.tree.domain.TreeDomain;
/**
 * 树的节点的设置
 * @author lzm
 *
 */
public class WebTreeModelCreator extends AbstractWebTreeModelCreator {

	protected Node createNode(Object menuData, UserDataUncoder uncoder){
		if(menuData instanceof TreeDomain){
			WebTreeNode result = new WebTreeNode(((TreeDomain) menuData).getName(), "n_" + ((TreeDomain) menuData).getId());
			if(menuData != null && ((TreeDomain) menuData).getAction() != null && !((TreeDomain) menuData).getAction().trim().equals("")){
				result.setAction(((TreeDomain) menuData).getAction()) ;
			}
			result.setTarget(((TreeDomain) menuData).getTarget());
			result.setValue(((TreeDomain) menuData).getId());
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
