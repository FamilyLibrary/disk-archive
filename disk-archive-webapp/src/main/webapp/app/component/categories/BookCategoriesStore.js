Ext.define('app.component.categories.BookCategoriesStore', {
    extend: 'Ext.data.Store',

    requires : [
        'app.component.categories.BookCategoriesModel'
    ],

    model: 'categories.BookCategoriesModel',
    storeId: 'categories.BookCategoriesStore',

    proxy: {
        type: 'ajax',
        url : '/books/categories/categories.json',
        simpleSortMode: true,
        reader: {
            type: 'json',
            rootProperty: 'entities',
            totalProperty: 'total'
        }
    },

    groupField: 'category'
})