Ext.define('app.component.categories.BookCategoriesGrid', {
    extend : 'Ext.grid.Panel',
    alias : 'widget.categories.BookCategoriesGrid',

    requires : [ 
        'app.component.categories.BookCategoriesStore',
        'app.component.books.BooksGridController'
    ],

    title: 'Book categories',

    controller : 'books.BooksGridController',
    store: Ext.create('app.component.categories.BookCategoriesStore'),

    features: [{
        ftype: 'groupingsummary',
        groupHeaderTpl: '{name}',
        startCollapsed: true
    }],

    columns: [{
        text: 'Subcategory',
        flex: 1,
        sortable: true,
        dataIndex: 'subcategory',
        hideable: false,
        summaryType: 'count',
        summaryRenderer: function(value, summaryData, dataIndex) {
            return ((value === 0 || value > 1) ? '(' + value + ' Subcategories)' : '(1 Subcategory)');
        }
    }, {
        header: 'Description',
        flex: 2,
        sortable: true,
        dataIndex: 'description'
    }],

    listeners : {
        render : 'onRender'
    }
})