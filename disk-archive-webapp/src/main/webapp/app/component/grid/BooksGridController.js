Ext.define('app.component.grid.BooksGridController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.grid.BooksGridController',

    onRender: function() {
        this.getView().getStore().loadPage(1);
    }
});