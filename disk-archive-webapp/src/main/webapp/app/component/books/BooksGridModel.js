Ext.define('app.component.books.BooksGridModel', {
    extend: 'Ext.data.Model',
    alternateClassName: 'books.BooksGridModel',

    fields: [
        {name: 'id', type: 'int'},
        {name: 'description', type: 'string'},
        {name: 'created', type: 'date'},
        {name: 'updated', type: 'date'},
        {name: 'name', type: 'string'},
        {name: 'volume', type: 'int'},
        {name: 'totalVolumes', mapping: 'completeWork.totalVolumes', type: 'int'},
        {name: 'yearOfPublication', type: 'int'},
        {name: 'authors', 
            convert: function(value, record) {
                return value.map(function(val){
                    return val.lastName;
                });
            }
        }
    ]
});