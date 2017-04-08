Ext.define('app.component.grid.BooksGrid', {
    extend : 'Ext.grid.Panel',
    alias : 'widget.grid.BooksGrid',

    requires : [ 'app.component.grid.BooksGridStore',
            'app.component.grid.BooksGridController',
            'Ext.grid.filters.Filters' ],

    plugins : 'gridfilters',

    controller : 'grid.BooksGridController',
    store : Ext.create('app.component.grid.BooksGridStore'),

    columns : [ {
        text : 'Id',
        dataIndex : 'id',
        hidden : true,
        filter : {type : 'number'}
    }, {
        text : 'Name',
        dataIndex : 'name',
        flex : 1,
        filter : {type : 'string'}
    }, {
        text : 'Volume',
        dataIndex : 'volume',
        filter : {type : 'number'}
    }, {
        text : 'Description',
        dataIndex : 'description',
        filter : {type : 'string'}
    }, {
        text : 'Created',
        dataIndex : 'created',
        filter : {type : 'date'}
    }, {
        text : 'Updated',
        dataIndex : 'updated',
        filter : {type : 'date'}
    } ],
    // paging bar on the bottom
    bbar : Ext.create('Ext.PagingToolbar', {
        store : this.store
    }),
    listeners : {
        render : 'onRender'
    }
});