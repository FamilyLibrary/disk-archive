Ext.define('MyApp.view.authentication.AuthenticationController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.authentication',

    onLoginButton: function() {
    	authdialog.submit({
    		url   : 'login.html',
            method:'POST',
            success:function(form, action){
                Ext.Msg.alert('Success', action.result.msg);
            }
        });
    },

    onNewAccount:  function() {
    	Ext.Ajax.request({
    		url   : 'register.html',
    		params : {
    			login    : authdialog.username.value,
    			password : authdialog.password.value,
    			_csrf : authdialog._csrf.value
    		},
            method: 'POST',
            success:function(response, options){
                Ext.Msg.alert('Success', response);
            }
        });
    }
});