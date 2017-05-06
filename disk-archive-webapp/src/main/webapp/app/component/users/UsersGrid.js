Ext.define('app.component.users.UsersGrid', {
    extend : 'Ext.grid.Panel',
    alias : 'widget.users.UsersGrid',

    requires : [ 
        'app.component.users.UsersGridStore',
        'app.component.users.UsersGridController',
        'Ext.grid.filters.Filters',
        'Ext.grid.plugin.RowEditing'
    ],

    title: 'Users',

    plugins : ['gridfilters', {
        ptype: 'cellediting',
        clicksToEdit: 1
    }],

    controller : 'users.UsersGridController',
    store : Ext.create('app.component.users.UsersGridStore'),

    columns : [ {
        text : 'Id',
        dataIndex : 'id',
        hidden : true,
        filter : {type : 'number'}
    }, {
        text : 'Login',
        dataIndex : 'login',
        filter : {type : 'string'},
        flex : 1
    }, {
        text : 'Password',
        dataIndex : 'password',
        filter : {type : 'string'}
    }, {
        xtype: 'checkcolumn',
        text : 'Enabled',
        dataIndex : 'enabled', 
        editor: 'checkbox',
        disabled: true,
        disabledCls : '',
        filter : {type : 'boolean'}
    }, {
        text : 'Groups',
        dataIndex : 'userGroups',
        sortable: false
    }, {
        xtype: 'actioncolumn',
        items: [{
            icon: '../images/edit.png',
            tooltip: 'Edit'
        }],
        flex : 2,
        sortable: false,
        align: 'center',
        handler: function(grid, rowIndex, colIndex) {
            alert('Here'); 
        }
    }],
    // paging bar on the bottom
    bbar : Ext.create('Ext.PagingToolbar', {
        store : this.store
    }),
    listeners : {
        render : 'onRender'
    }
});