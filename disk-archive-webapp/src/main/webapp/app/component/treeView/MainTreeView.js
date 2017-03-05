Ext.define('app.component.treeView.MainTreeView', {
    extend: 'Ext.tree.Panel',

    require : [
        'app.component.treeView.MainTreeViewStore'
    ],

    store: Ext.create('app.component.treeView.MainTreeViewStore')
});