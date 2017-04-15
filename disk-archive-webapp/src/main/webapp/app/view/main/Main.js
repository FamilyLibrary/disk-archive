Ext.define('app.view.main.Main', {
    extend: 'Ext.Container',
    requires: [
        'Ext.Button',
        'Ext.list.Tree',
        'Ext.app.ViewController',
        'app.view.main.Toolbar',
        'app.component.treeView.MainTreeView',
        'app.component.books.BooksGrid',
        'app.component.categories.BookCategoriesGrid'
    ],
    renderTo:Ext.getBody(),

    items: [{
        xtype: 'maintoolbar',
        docked: 'top',
        userCls: 'main-toolbar shadow'
    }, {
        xtype: 'panel',
        referenceHolder: true,
        layout: {
            type: 'hbox',
            align: 'stretch'
        },
        items: [{
            xtype: 'treeView.MainTreeView',
            width: '20%',
            reference: 'navigationTree'
        },{
            xtype: 'panel',
            flex: 1,
            reference: 'content'
        }]
    }]
});