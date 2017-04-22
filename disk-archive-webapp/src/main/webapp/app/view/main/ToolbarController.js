Ext.define('app.view.main.ToolbarController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.main.ToolbarController',

    onUserRender: function() {
        var me = this;
        var toolbar = this.getView();
        var userNameComponent = toolbar.getReferences().userNameComponent;

        Ext.Ajax.request({
            url   : 'main/currentUser.json',
            success:function(response, options){
                var jsonResponse = Ext.util.JSON.decode(response.responseText);
                userNameComponent.setHtml(jsonResponse);
            },
            failure: function() {
                console.log('Could not get information about current logged user');
            }
        });
    }
});