Ext.define('app.component.treeView.MainTreeView', {
    extend: 'Ext.tree.Panel',
    alias:'widget.treeView.MainTreeView',

    requires : [
        'app.component.treeView.MainTreeViewStore',
        'app.component.treeView.MainTreeViewController'
    ],

    controller: 'treeView.MainTreeViewController',

    store: Ext.create('app.component.treeView.MainTreeViewStore'),

    rootVisible: false,
    root: {
        expanded: true
    },

    listeners: {
        itemclick: 'onItemClick'
    }
});