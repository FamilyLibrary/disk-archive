package com.alextim.diskarchive.treeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 19.02.2017.
 */
public class Root extends TreeNode{
    private boolean success;

    public Root(String name){
        this(name, false);
        this.name = name;
    }

    public Root(String name, boolean success){
        this(name, success, new ArrayList<>());
        this.name = name;
        this.success = success;
    }

    public Root(String name, boolean success, List<TreeNode> children){
        this(name, success, children, false);
        this.name = name;
        this.success = success;
        this.children = children;
    }
    public Root(String name, boolean success, List<TreeNode> children, boolean leaf){
        super(name, leaf, children);
        this.name = name;
        this.success = success;
        this.children = children;
        this.leaf = leaf;
    }
}
