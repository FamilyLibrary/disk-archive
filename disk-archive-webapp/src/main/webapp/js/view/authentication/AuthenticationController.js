Ext.define('MyApp.view.authentication.AuthenticationController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.authentication',

    onLoginButton: function() {
    	authdialog.submit({
            method:'POST',
            success:function(){
            	
            }
        });
    },

    onNewAccount:  function() {
        this.redirectTo('register.html', true);
    }
});