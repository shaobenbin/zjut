package org.sevenstar.component.tree.impl;

import java.util.List;

import net.jcreate.e3.tree.Node;
import net.jcreate.e3.tree.NodeVisitor;
import net.jcreate.e3.tree.TreeDirector;
import net.jcreate.e3.tree.TreeModel;
import net.jcreate.e3.tree.UserDataUncoder;
import net.jcreate.e3.tree.support.AbstractNodeComparator;
import net.jcreate.e3.tree.support.AbstractWebTreeModelCreator;
import net.jcreate.e3.tree.support.DefaultTreeDirector;
import net.jcreate.e3.tree.support.WebTreeBuilder;
import net.jcreate.e3.tree.support.WebTreeNode;

import org.sevenstar.component.tree.Tree;
import org.sevenstar.component.tree.TreeDataUncoder;
import org.sevenstar.component.tree.domain.TreeDomain;
import org.sevenstar.component.tree.support.WebTreeModelCreator;
import org.sevenstar.web.context.WebContext;


public class PlainTreeImpl implements Tree{
	/**
	 * 获取树形目录 
	 */
	public String getNodeScript(List nodeList, WebTreeBuilder builder){
		String script = "" ;
		if(nodeList != null && nodeList.size() > 0 && builder != null){
			UserDataUncoder uncoder = new TreeDataUncoder() ;
			AbstractWebTreeModelCreator creator = new WebTreeModelCreator() ;
			TreeModel model = creator.create(nodeList, uncoder);
			TreeDirector director = new DefaultTreeDirector();
			director.setComparator(new AbstractNodeComparator(){
				protected Comparable getComparableProperty(Node pNode){
					Object data = pNode.getUserData();
					TreeDomain domain = (TreeDomain) data ;
					return domain.getOrderBy();
				}
			});
			
			director.setNodeVisitor(new NodeVisitor(){
				public boolean visit(Node pNode) {
					WebTreeNode webNode = (WebTreeNode)pNode;
					if("true".equals(webNode.getAttribute("isDisabled"))){
						webNode.setNodeProperty(WebTreeNode.NONE);
						webNode.setDisabled(true);
					}else{
						webNode.setNodeProperty(WebTreeNode.CHECKBOX);
					}
					
					if("true".equals(webNode.getAttribute("isRadio"))){
						webNode.setNodeProperty(WebTreeNode.RADIO);
					}
					if(webNode.getAttribute("icon")!=null && !"".equals(webNode.getAttribute("icon"))){
						webNode.setIcon(webNode.getAttribute("icon"));
					}
					if(webNode.getAttribute("openIcon")!=null && !"".equals(webNode.getAttribute("openIcon"))){
						webNode.setOpenIcon(webNode.getAttribute("openIcon"));
					}
					return true;
				}
			});
			
			creator.init(WebContext.getRequest());
			builder.init(WebContext.getRequest());
			director.build(model, builder) ;
			script = builder.getTreeScript();
		}
		return script ;
	}
	
	public String getRootScript(WebTreeNode node, WebTreeBuilder builder){
		return "";
	}
}
