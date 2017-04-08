Ext.define('app.component.treeView.MainTreeViewModel', {
    extend: 'Ext.data.Model',
    alternateClassName: 'treeView.MainTreeViewModel',

    fields: [
        { name: 'leaf', type: 'bool' },
        { name: 'text', type: 'string' },
        { name: 'children'}
    ],
    proxy: {
        type: 'ajax',
        url : 'main/treeView.json'
    }
});