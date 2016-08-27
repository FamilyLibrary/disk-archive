Ext.define('MyApp.view.authentication.AuthenticationController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.authentication',

    onLoginButton: function() {
    	authdialog.submit({
    		url   : 'login.html',
            method:'POST',
            success:function(){
            	
            }
        });
    },

    onNewAccount:  function() {
    	Ext.Ajax.request({
    		url   : 'register.html',
    		params : {
    			login    : authdialog.login.value,
    			password : authdialog.password.value,
    			_csrf : authdialog._csrf.value
    		},
            method: 'POST',
            success:function(){
            	
            }
        });
    }
});