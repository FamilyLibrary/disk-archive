Ext.define('app.view.main.Toolbar', {
    extend: 'Ext.Toolbar',
    xtype: 'maintoolbar',

    requires : [ 
        'app.view.main.ToolbarController'
    ],

    controller: 'main.ToolbarController',

    items: [{
            // This component is moved to the floating nav container by the phone profile
            xtype: 'component',
            reference: 'logo',
            userCls: 'main-logo',
            html: 'Sencha'
        }, '->', {
            xtype: 'component',
            html: 'Unknown user',
            reference: 'userNameComponent',
            margin: '0 12 0 4',
            userCls: 'main-user-name',
            listeners: {
                render: 'onUserRender'
            }
        }, {
            xtype: 'image',
            userCls: 'main-user-image small-image circular',
            alt: 'Current user image',
            src: '/main/renderGeneralImage.html',
            width: '32px',
            height: '32px'
        }, '', {
            xtype: 'box',
            autoEl: {tag: 'a', href: '/logout', html: 'Logout'}
        }
    ]
});
