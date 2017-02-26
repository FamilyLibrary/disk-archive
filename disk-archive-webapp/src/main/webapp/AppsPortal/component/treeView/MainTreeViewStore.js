Ext.define('AppsPortal.component.treeView.MainTreeViewStore', {
    extend: 'Ext.data.TreeStore',

    proxy: {
        type: 'ajax',
        url : 'main/treeView.json'
    }

});