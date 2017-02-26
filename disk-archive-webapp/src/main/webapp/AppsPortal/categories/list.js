Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.form.field.Number',
    'Ext.form.field.Date',
    'Ext.tip.QuickTipManager'
]);

Ext.define('Task', {
    extend: 'Ext.data.Model',
    idProperty: 'taskId',
    fields: [
        {name: 'subcategoryId', type: 'int'},
        {name: 'subcategory', type: 'string'},
        {name: 'categoryId', type: 'int'},
        {name: 'category', type: 'string'}
    ]
});

Ext.onReady(function(){

    Ext.tip.QuickTipManager.init();

    var store = Ext.create('Ext.data.Store', {
        model: 'Task',
        data: data,
        groupField: 'category'
    });

    var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
        clicksToEdit: 1
    });

    var grid = Ext.create('Ext.grid.Panel', {
        width: 840,
        height: 450,
        frame: true,
        title: 'Categories',
        iconCls: 'icon-grid',
        renderTo: document.body,
        store: store,
        tbar: [{
            text: 'Add Category',
            handler : function() {

            }
        }, {
            itemId: 'removeCategory',
            text: 'Remove Category',
            handler: function() {
            },
            disabled: true
        }],
        plugins: [cellEditing],
        listeners: {
            'selectionchange': function(view, records) {
                grid.down('#removeEmployee').setDisabled(!records.length);
            }
        },
        dockedItems: [{
            dock: 'top',
            xtype: 'toolbar',
            items: [{
                tooltip: 'Toggle the visibility of the summary row',
                text: 'Toggle Summary',
                enableToggle: true,
                pressed: true,
                handler: function() {
                    grid.getView().getFeature('group').toggleSummaryRow();
                }
            }]
        }],
        features: [{
            id: 'group',
            ftype: 'groupingsummary',
            groupHeaderTpl: '{name}',
            hideGroupedHeader: true,
            enableGroupingMenu: false
        }],
        columns: [{
            text: 'Subcategory',
            flex: 1,
            sortable: true,
            dataIndex: 'subcategory',
            hideable: false,
            summaryType: 'count',
            summaryRenderer: function(value, summaryData, dataIndex) {
                return ((value === 0 || value > 1) ? '(' + value + ' Subcategories)' : '(1 Subcategory)');
            }
        }, {
            header: 'Description',
            width: 180,
            sortable: true,
            dataIndex: 'description'
        }]
    });
});
