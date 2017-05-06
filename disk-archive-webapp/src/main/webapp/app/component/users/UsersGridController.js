Ext.define('app.component.users.UsersGridController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.users.UsersGridController',

    onRender: function() {
        this.getView().getStore().loadPage(1);
    }
})