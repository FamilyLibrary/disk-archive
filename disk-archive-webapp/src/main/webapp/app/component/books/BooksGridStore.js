Ext.define('app.component.books.BooksGridStore', {
    extend: 'Ext.data.Store',

    requires : [
        'app.component.books.BooksGridModel'
    ],

    model: 'books.BooksGridModel',
    storeId: 'books.BooksGridStore',

    proxy: {
        type: 'ajax',
        url : '/books/books.json',
        simpleSortMode: true,
        reader: {
            type: 'json',
            rootProperty: 'entities',
            totalProperty: 'total'
        }
    },

    autoLoad: false,
    remoteSort: true
});