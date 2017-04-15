Ext.define('app.component.books.BooksGridController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.books.BooksGridController',

    onRender: function() {
        this.getView().getStore().loadPage(1);
    }
});