package org.sevenstar.component.tree.domain;

public class TreeDomain {

	private String id ;
	
	/**
	 * 树中显示的名称
	 */
	private String name ;
	
	/**
	 * radio或者checkbox的名称
	 */
	private String nodeName;
	
	private String parentId ;
	
	private String action ;
	
	private Integer orderBy ;
	
	private String subTreeURL ;
	
	private String target ;
	
	private String isSelected ;
	
	private String isDisabled;
	
	private String icon;
	
	private String openIcon;
	
	private String isRadio ;
	
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public String getSubTreeURL() {
		return subTreeURL;
	}

	public void setSubTreeURL(String subTreeURL) {
		this.subTreeURL = subTreeURL;
	}

	public String getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getOpenIcon() {
		return openIcon;
	}

	public void setOpenIcon(String openIcon) {
		this.openIcon = openIcon;
	}

	public boolean isSelected(){
		return "Y".equals(this.getIsSelected());
	}
	
	public void setSelected(){
		this.isSelected = "Y";
	}

	public String getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(String isDisabled) {
		this.isDisabled = isDisabled;
	}
	
	public void setDisabledShow(){
		this.isDisabled = "true" ;
	}
	
	public void setAbledShow(){
		this.isDisabled = "false" ;
	}
	
	public boolean isDisabledShow(){
		return "true".equals(this.getIsDisabled());
	}

	public String getIsRadio() {
		return isRadio;
	}

	public void setIsRadio(String isRadio) {
		this.isRadio = isRadio;
	}
	
	public void setRadio(){
		this.isRadio = "true" ;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
}
