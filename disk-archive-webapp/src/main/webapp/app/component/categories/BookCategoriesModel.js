Ext.define('app.component.categories.BookCategoriesModel', {
    extend: 'Ext.data.Model',
    alternateClassName: 'categories.BookCategoriesModel',

    fields: [
        {name: 'subcategoryId', type: 'int'},
        {name: 'subcategory', type: 'string'},
        {name: 'categoryId', type: 'int'},
        {name: 'category', type: 'string'}
    ]
})