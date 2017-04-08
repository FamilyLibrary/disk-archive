Ext.define('app.component.grid.BooksGridStore', {
    extend: 'Ext.data.Store',

    requires : [
        'app.component.grid.BooksGridModel'
    ],

    model: 'grid.BooksGridModel',
    storeId: 'grid.BooksGridStore',

    autoLoad: false,
    remoteSort: true
});