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

	public FolderTreeNode(TFolder root, List<TFolder> allFolders) {
		this.parentId = root.getParentId();
		this.id = root.getId();
		this.name = root.getName();
		
		this.setChildren(new ArrayList<FolderTreeNode>());
		for (TFolder f : allFolders) {
			if(this.id.equals(f.getParentId())) {
				this.getChildren().add(new FolderTreeNode(f, allFolders));
			}
		}

	}

	public List<FolderTreeNode> loadFormatedList() {
		return format(this, 0);
	}

	private List<FolderTreeNode> format(FolderTreeNode node, int level) {
		List<FolderTreeNode> result = new ArrayList<FolderTreeNode>();
		String prefix = "";
		if(level != 0) {
			for (int i = 0; i < level; i++) {
				prefix += "&nbsp;&nbsp;&nbsp;&nbsp;";
			}
			prefix += "├─";
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

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(obj instanceof FolderTreeNode) {
			if(this.getId() != null) {
				return this.getId().equals(((FolderTreeNode)obj).getId());
			}
		}
		return  false;
	}
}
