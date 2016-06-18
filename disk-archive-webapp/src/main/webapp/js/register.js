Ext.require([ 'Ext.form.*', 'Ext.toolbar.*', 'Ext.button.*',
        'MyApp.view.Register' ]);

Ext.onReady(function() {
    var mypanel = Ext.create('MyApp.view.Register', {
        title : 'Register',
        collapsible: false,
        region: 'center',
        margin: '5 0 0 0',
        cancelUrl: "/login.html"
    });

    var layoutContainer = Ext.create('MyApp.view.layout.Border', {
        items: [{
                    title: 'Footer',
                    region: 'south',
                    height: 100,
                    minHeight: 75,
                    maxHeight: 150,
                    html: '<p>Footer content</p>'
        },{
                    title: 'Navigation',
                    region:'west',
                    floatable: false,
                    margin: '5 0 0 0',
                    width: 125,
                    minWidth: 100,
                    maxWidth: 250,
                    html: '<p>Secondary content like navigation links could go here</p>'
        }, mypanel]
    });

    var container = Ext.create('Ext.container.Viewport', {
        layout: {
            type: 'vbox',
            align: 'center',
            pack: 'center'
        },
        items:[layoutContainer]
    });

    console.log('Ok');
});