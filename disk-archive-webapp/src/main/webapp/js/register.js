Ext.Loader.setConfig({
    enabled : true,
    paths : {
        MyApp : 'js'
    }
});
Ext.require([ 'Ext.form.*', 'Ext.toolbar.*', 'Ext.button.*',
        'MyApp.view.Register' ]);

Ext.onReady(function() {
    var mypanel = Ext.create('MyApp.view.Register', {
        title : 'Register',
        renderTo : Ext.getBody(),
        cancelUrl: "/login.html"
    });

    console.log('Ok');
});