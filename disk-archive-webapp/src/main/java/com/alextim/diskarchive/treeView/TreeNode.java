package com.alextim.diskarchive.treeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 19.02.2017.
 */
public class TreeNode {
    protected List<TreeNode> children;
    protected boolean leaf;
    protected String name;
    public TreeNode(String name){
        this(name, false);
        this.name = name;
    }
    public TreeNode(String name, boolean leaf){
        this(name, leaf, new ArrayList<>());
        this.name = name;
        this.leaf = leaf;
    }
    public TreeNode(String name, boolean leaf, List children){
        this.name = name;
        this.leaf = leaf;
        this.children = children;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

}
