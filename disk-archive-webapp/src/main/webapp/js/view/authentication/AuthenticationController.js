Ext.define('MyApp.view.authentication.AuthenticationController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.authentication',

    onLoginButton: function() {
    	authdialog.submit();
    },

    onNewAccount:  function() {
    	Ext.Ajax.request({
    		url   : 'register.html',
    		params : {
    			login    : authdialog.j_username.value,
    			password : authdialog.j_password.value,
    			_csrf : authdialog._csrf.value
    		},
            method: 'POST',
            success:function(response, options){
            	Ext.getCmp('errorMessage').show();
            },
            failure: function() {
            	Ext.getCmp('errorMessage').show();
            }
        });
    }
});