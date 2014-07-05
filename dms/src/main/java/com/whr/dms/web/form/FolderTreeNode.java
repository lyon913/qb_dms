package com.whr.dms.web.form;

import java.util.ArrayList;
import java.util.List;

import com.whr.dms.models.TFolder;

public class FolderTreeNode {
	private Long id;
	private String name;
	private List<FolderTreeNode> children = new ArrayList<FolderTreeNode>();
	
	public FolderTreeNode(Long id,String name){
		this.id = id;
		this.name = name;
	}
	
	public FolderTreeNode(List<TFolder> children){
		if(children != null){
			for(TFolder child : children){
				FolderTreeNode cNode = new FolderTreeNode(child.getId(),child.getName());
				this.children.add(cNode);
			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FolderTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<FolderTreeNode> children) {
		this.children = children;
	}
}
