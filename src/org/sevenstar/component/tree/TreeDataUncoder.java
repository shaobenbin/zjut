package org.sevenstar.component.tree;

import net.jcreate.e3.tree.UncodeException;
import net.jcreate.e3.tree.UserDataUncoder;

import org.sevenstar.app.common.exception.ApplicationException;
import org.sevenstar.component.tree.domain.TreeDomain;

public class TreeDataUncoder implements UserDataUncoder{

	public Object getID(Object menuData) throws UncodeException{
		if(menuData instanceof TreeDomain){
			return ((TreeDomain)menuData).getId();
		}
		throw new ApplicationException("业务编码id错误,树中节点的数据类型错误!");
	}
	
	public Object getParentID(Object menuData) throws UncodeException{
		if(menuData instanceof TreeDomain){
			return ((TreeDomain)menuData).getParentId();
		}
		throw new ApplicationException("业务编码ParentId错误,树中节点的数据类型错误!");
	} 
}
