Ext.define('app.view.main.Main', {
    extend: 'Ext.Container',
    requires: [
        'Ext.Button',
        'Ext.list.Tree',
        'Ext.app.ViewController',
        'app.component.treeView.MainTreeView',
        'app.component.grid.BooksGrid',
        'app.view.main.Toolbar'
    ],
    renderTo:Ext.getBody(),

    items: [{
        xtype: 'maintoolbar',
        docked: 'top',
        userCls: 'main-toolbar shadow'
    }, {
        xtype: 'panel',
        scrollable: true,
        layout: {
            type: 'hbox',
            align: 'stretch'
        },
        items: [{
            xtype: 'treeView.MainTreeView',
            width: '20%',
            reference: 'navigationTree'
        },{
            xtype: 'grid.BooksGrid',
            flex: 1,
            reference: 'booksGrid'
        }]
    }]
});