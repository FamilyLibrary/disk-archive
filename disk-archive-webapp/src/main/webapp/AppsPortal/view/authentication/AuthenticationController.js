Ext.define('AppsPortal.view.authentication.AuthenticationController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.authentication',

    onLoginButton: function() {
    	authdialog.submit();
    },

    onNewAccount:  function() {
    	var loginObj = Ext.ComponentQuery.query('login')[0];

    	var userName = authdialog.j_username.value;
    	var password = authdialog.j_password.value;

    	var errorMessage = Ext.getCmp('errorMessage');
    	Ext.Ajax.request({
    		url   : 'register/register.html',
    		params : {
    			login    : userName,
    			password : password/*,
    			_csrf : authdialog._csrf.value*/
    		},
            method: 'POST',
            success:function(response, options){
            	loginObj.showMessage(errorMessage, userName + ' user was registered!', {color: 'blue'});
            },
            failure: function() {
            	loginObj.showMessage(errorMessage, 'Could not register ' + userName + ' user', {color: 'red'});
            }
        });
    }
});