Ext.define('MyApp.view.layout.Border', {
    extend: 'Ext.panel.Panel',
    xtype: 'layout-border',
    requires: [
        'Ext.layout.container.Border'
    ],
    layout: 'border',
    width: 500,
    height: 400,

    bodyBorder: false,
    
    defaults: {
        collapsible: true,
        split: true,
        bodyPadding: 10
    },

    items: []

});