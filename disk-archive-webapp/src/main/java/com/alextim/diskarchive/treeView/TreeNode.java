package com.alextim.diskarchive.treeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 19.02.2017.
 */
public class TreeNode {
    private List<TreeNode> children;
    private boolean leaf;
    private String name;

    public TreeNode(String name){
        this(name, false, new ArrayList<>());
    }

    protected TreeNode(String name, boolean leaf, List<TreeNode> children){
        this.name = name;
        this.leaf = leaf;
        this.children = children;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    public boolean isLeaf() {
        return leaf;
    }
}
