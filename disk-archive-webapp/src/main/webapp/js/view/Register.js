Ext.define('MyApp.view.Register', {
    extend : 'Ext.form.Panel',
    alias : 'MyApp.view.Register',

    cancelUrl: "#",

    height : 280,
    width : 448,
    bodyPadding : 6,
    defaultType : 'textfield',
    defaults : {
        anchor : '-18',
        labelWidth : 90,
        labelAlign : 'right'
    },
    title : 'Customer ( .... )',
    items : [ {
        xtype : 'numberfield',
        fieldLabel : 'Customer ID'
    }, {
        fieldLabel : 'Name'
    }, {
        fieldLabel : 'Phone'
    }, {
        xtype : 'datefield',
        fieldLabel : 'Client since',
    }, {
        xtype : 'combobox',
        fieldLabel : 'Status'
    } ],
    dockedItems : [ {
        xtype : 'toolbar',
        dock : 'bottom',
        items : [ {
            xtype : 'tbfill'
        }, {
            xtype : 'button',
            text : 'Save...'
        }, {
            xtype : 'button',
            text : 'Cancel',
            handler : function() {
                location.href = this.cancelUrl;
            }
        } ]
    } ]
});