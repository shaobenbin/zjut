package org.sevenstar.component.tree;

import java.util.List;
import net.jcreate.e3.tree.support.WebTreeBuilder;
import net.jcreate.e3.tree.support.WebTreeNode;

public interface Tree {
	public String getRootScript(WebTreeNode node, WebTreeBuilder builder) ;
	public String getNodeScript(List nodeList, WebTreeBuilder builder) ;
}
