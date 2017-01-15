/**
 * This class provides the modal Ext.Window support for all Authentication forms.
 * It's layout is structured to center any Authentication dialog within it's center,
 * and provides a backGround image during such operations.
 */
Ext.define('AppsPortal.view.authentication.LockingWindow', {
    extend: 'Ext.window.Window',
    xtype: 'lockingwindow',

    requires: [
        'AppsPortal.view.authentication.AuthenticationController',
        'Ext.layout.container.VBox'
    ],

    layout: {
        type: 'center',
        align: 'stretch'
    },

    controller: 'authentication',

    closable: false,
    resizable: false,
    autoShow: true,
    titleAlign: 'center',
    maximized: true,
    modal: true
});
