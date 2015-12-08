package com.util;

import java.util.List;

public class TreeNode {
	private String id;
	private String text;
	private List<TreeNode> children;
	private String iconCls;
	private String state;
	private String attributes;
	private String checked;

	public TreeNode() {
		super();
	}

	public TreeNode(String id, String text, List<TreeNode> children) {
		super();
		this.id = id;
		this.text = text;
		this.children = children;
	}

	public TreeNode(String id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}
}
