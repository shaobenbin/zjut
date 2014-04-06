package org.sevenstar.component.tree.impl;

import java.util.List;

import net.jcreate.e3.tree.Node;
import net.jcreate.e3.tree.TreeModel;
import net.jcreate.e3.tree.TreeDirector;
import net.jcreate.e3.tree.UserDataUncoder;
import net.jcreate.e3.tree.support.AbstractWebTreeModelCreator;
import net.jcreate.e3.tree.support.AbstractNodeComparator;
import net.jcreate.e3.tree.support.DefaultTreeDirector;
import net.jcreate.e3.tree.support.DefaultTreeModel;
import net.jcreate.e3.tree.support.WebTreeBuilder;
import net.jcreate.e3.tree.support.WebTreeNode;

import org.sevenstar.component.tree.Tree;
import org.sevenstar.component.tree.TreeDataUncoder;
import org.sevenstar.component.tree.domain.TreeDomain;
import org.sevenstar.component.tree.support.DynamicWebTreeModelCreator;
import org.sevenstar.component.tree.support.WebTreeModelCreator;
import org.sevenstar.web.context.WebContext;


public class DynamicTreeImpl implements Tree{
	/**
	 * 获取动态树的根节点
	 * @param rootNode
	 * @param builder
	 * @return
	 */
	public String getRootScript(WebTreeNode rootNode, WebTreeBuilder builder){
		String script = "" ;
		if(rootNode != null && builder != null){
			UserDataUncoder uncoder = new TreeDataUncoder() ;
			AbstractWebTreeModelCreator creator = new WebTreeModelCreator() ;
			TreeDirector director = new DefaultTreeDirector();
			DefaultTreeModel model = new DefaultTreeModel();
			model.addRootNode(rootNode);
				
			creator.init(WebContext.getRequest());
			builder.init(WebContext.getRequest());
			director.build(model, builder) ;
			script = builder.getTreeScript();
		}
		return script ;
	}
	
	/**
	 * 获取动态子树
	 * @param nodeList
	 * @param builder
	 * @return
	 */
	public String getNodeScript(List nodeList, WebTreeBuilder builder){
		String script = "" ;
		if(nodeList != null && builder != null){
			UserDataUncoder uncoder = new TreeDataUncoder() ;
			AbstractWebTreeModelCreator creator = new DynamicWebTreeModelCreator() ;
			TreeModel model = creator.create(nodeList, uncoder) ;
			TreeDirector director = new DefaultTreeDirector();
			director.setComparator(new AbstractNodeComparator(){
				protected Comparable getComparableProperty(Node pNode){
					Object data = pNode.getUserData();
					TreeDomain domain = (TreeDomain) data ;
					return domain.getOrderBy();
				}
			});
			
			creator.init(WebContext.getRequest());
			builder.init(WebContext.getRequest());
			director.build(model, builder) ;
			script = builder.getTreeScript();
		}
		return script ;
	}
}
