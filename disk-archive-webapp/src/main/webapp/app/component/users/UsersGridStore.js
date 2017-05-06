Ext.define('app.component.users.UsersGridStore', {
    extend: 'Ext.data.Store',

    requires : [
        'app.component.users.UsersGridModel'
    ],

    model: 'users.UsersGridModel',
    storeId: 'users.UsersGridStore',

    proxy: {
        type: 'ajax',
        url : '/user/users.json',
        simpleSortMode: true,
        reader: {
            type: 'json',
            rootProperty: 'entities',
            totalProperty: 'total'
        }
    },

    autoLoad: false,
    remoteSort: true
})