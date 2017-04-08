package com.alextim.diskarchive.treeView;

import java.util.List;

/**
 * Created by admin on 19.02.2017.
 */
public class Root extends TreeNode{
    private boolean success;

    public Root(String text, boolean success, List<TreeNode> children){
        super(text, false, children);
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
