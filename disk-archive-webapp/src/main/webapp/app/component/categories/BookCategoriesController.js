Ext.define('app.component.categories.BooksCategoriesController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.categories.BooksCategoriesController',

    onRender: function() {
        this.getView().getStore().load();
    }
});