Ext.define('app.component.users.UsersGridModel', {
    extend: 'Ext.data.Model',
    alternateClassName: 'users.UsersGridModel',

    fields: [
        {name: 'id', type: 'int'},
        {name: 'login', type: 'string'},
        {name: 'password', type: 'string'},
        {name: 'enabled', type: 'bool'},
        {name: 'userGroups', 
            convert: function(value, record) {
                return value.map(function(val){
                    return val.name;
                });
            }
        }
    ]
})