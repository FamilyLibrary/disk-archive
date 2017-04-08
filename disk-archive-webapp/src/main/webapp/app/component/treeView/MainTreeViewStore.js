Ext.define('app.component.treeView.MainTreeViewStore', {
    extend: 'Ext.data.TreeStore',

    requires : [
        'app.component.treeView.MainTreeViewModel'
    ],

    model: 'treeView.MainTreeViewModel',

    autoLoad: false
});