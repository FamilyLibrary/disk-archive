Ext.define('MyApp.view.authentication.Login', {
    extend: 'MyApp.view.authentication.LockingWindow',
    xtype: 'login',

    requires: [
       'MyApp.view.authentication.Dialog',
       'Ext.container.Container',
       'Ext.form.field.Text',
       'Ext.form.field.Checkbox',
       'Ext.button.Button'
    ],

    title: 'Let\'s Log In',
    defaultFocus: 'authdialog', // Focus the Auth Form to force field focus as well,

    items: [{
        xtype: 'authdialog',
        referenceHolder: true,
        reference: 'authdialogForm',
        defaultButton : 'loginButton',
        autoComplete: true,
        bodyPadding: '20 20',
        cls: 'auth-dialog-login',
        header: false,
        width: 415,
        layout: {
            type: 'vbox',
            align: 'stretch'
        },

        defaults : {
            margin : '5 0'
        },

        items: [
            {
            	id: 'errorMessage',
                tag: 'div',
                fontSize: 22,
                hidden: false
            },
            {
                xtype: 'label',
                text: 'Sign into your account'
            },
            {
                xtype: 'textfield',
                cls: 'auth-textbox',
                name: 'j_username',
                reference: 'loginTextbox',
                height: 55,
                hideLabel: true,
                allowBlank : false,
                emptyText: 'Login',
                triggers: {
                    glyphed: {
                        cls: 'trigger-glyph-noop auth-email-trigger'
                    }
                }
            },
            {
                xtype: 'textfield',
                cls: 'auth-textbox',
                height: 55,
                hideLabel: true,
                emptyText: 'Password',
                inputType: 'password',
                name: 'j_password',
                reference: 'passwordTextbox',
                allowBlank : false,
                triggers: {
                    glyphed: {
                        cls: 'trigger-glyph-noop auth-password-trigger'
                    }
                }
            },
            {
                xtype: 'container',
                layout: 'hbox',
                items: [
                    {
                        xtype: 'checkboxfield',
                        flex : 1,
                        cls: 'form-panel-font-color rememberMeCheckbox',
                        height: 30,
                        boxLabel: 'Remember me'
                    },
                    {
                        xtype: 'box',
                        html: '<a href="#passwordreset" class="link-forgot-password"> Forgot Password ?</a>'
                    }
                ]
            },
            {
                xtype: 'button',
                reference: 'loginButton',
                scale: 'large',
                iconAlign: 'right',
                iconCls: 'x-fa fa-angle-right',
                text: 'Login',
                listeners: {
                    click: 'onLoginButton'
                }
            },
            {
                xtype: 'button',
                scale: 'large',
                iconAlign: 'right',
                iconCls: 'x-fa fa-user-plus',
                text: 'Create Account',
                listeners: {
                    click: 'onNewAccount'
                }
            }
        ]
    }],

    constructor: function(config) {
    	this.callParent();
    	this.addCls('user-login-register-container');

    	var form = this.lookupReference('authdialogForm');

    	var errorMessage = Ext.getCmp('errorMessage');
    	errorMessage.hide();
    	if (config.hasError) {
    		this.showMessage(errorMessage, 'Login failed, please try again.', {color: 'red'});
    	}

    	var csrfField = this.createCsrfField(config.csrfName, config.csrfValue);
    	form.add(csrfField);
    },

    createCsrfField: function(name, value) {
    	return Ext.form.Field({
            xtype:'hidden',
            name: name,
            value: value
    	});
    },

    showMessage: function(element, message, style) {
    	element.update(message);
    	element.body.setStyle(style);

    	element.show();
    }
});