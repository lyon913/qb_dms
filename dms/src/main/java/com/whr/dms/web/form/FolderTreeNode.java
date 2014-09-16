package com.whr.dms.web.form;

import java.util.ArrayList;
import java.util.List;

import com.whr.dms.models.TFolder;

public class FolderTreeNode {
	private Long id;
	private String name;
	private Long parentId;
	private List<FolderTreeNode> children = new ArrayList<FolderTreeNode>();

	public FolderTreeNode() {
	}

	public FolderTreeNode(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public FolderTreeNode(long id, String name, long parentId) {
		this.id = id;
		this.name = name;
		this.parentId = parentId;
	}

	private Long getParentId() {
		return this.parentId;
	}

	private void findSelfChildren(List<FolderTreeNode> allNodes) {
		for (FolderTreeNode node : allNodes) {
			if (node.getParentId() == this.getId()) {
				this.getChildren().add(node);
			}
		}
	}

	public List<FolderTreeNode> loadFormatedList() {

		return format(this,0);
	}

	private List<FolderTreeNode> format(FolderTreeNode node, int level) {
		List<FolderTreeNode> result = new ArrayList<FolderTreeNode>();
		String prefix = "";
		for (int i = 0; i < level; i++) {
			prefix += "&nbsp;&nbsp;";
		}
		node.setName(prefix + node.getName());
		result.add(node);
		
		if (node.children != null && node.children.size() > 0) {
			for (FolderTreeNode n : node.children) {
				result.addAll(format(n, level + 1));
			}
		}


		return result;
	}

	public FolderTreeNode(List<TFolder> allFolders) {
		List<FolderTreeNode> nodes = new ArrayList<FolderTreeNode>();
		for (TFolder tfolder : allFolders) {
			FolderTreeNode n = new FolderTreeNode(tfolder.getId(), tfolder.getName(), tfolder.getParentId());
			if (n.getParentId() == -1) {
				// root
				this.id = n.getId();
				this.name = n.getName();
				this.parentId = n.getParentId();
				nodes.add(this);
			} else {
				nodes.add(n);
			}
		}
		for (int i = 0; i < nodes.size(); i++) {
			FolderTreeNode f = nodes.get(i);
			f.findSelfChildren(nodes);
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

	public void setChildrenTFolder(List<TFolder> children) {
		if (children != null) {
			for (TFolder child : children) {
				FolderTreeNode cNode = new FolderTreeNode(child.getId(), child.getName());
				this.children.add(cNode);
			}
		}
	}
}
