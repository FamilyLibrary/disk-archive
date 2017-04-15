Ext.define('app.component.books.BooksGridModel', {
    extend: 'Ext.data.Model',
    alternateClassName: 'grid.BooksGridModel',

    fields: [
        {name: 'id', type: 'int'},
        {name: 'description', type: 'string'},
        {name: 'created', type: 'date'},
        {name: 'updated', type: 'date'},
        {name: 'name', type: 'string'},
        {name: 'volume', type: 'int'}
    ]
});