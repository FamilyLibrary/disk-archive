Ext.define('app.component.treeView.MainTreeViewStore', {
    extend: 'Ext.data.TreeStore',

    autoLoad: true,

    proxy: {
        type: 'ajax',
        url : 'main/treeView.json'
    }

});