Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.form.field.*',
    'Ext.tip.QuickTipManager'
]);

Ext.define('CategoryModel', {
    extend: 'Ext.data.Model',
    idProperty: 'categoryId',
    fields: [
        {name: 'name', type: 'string'},
        {name: 'description', type: 'string'},
    ]
});

var data = [
    {subcategoryId: 100, subcategory: '100', category: 'Ext Forms: Field Anchoring', categoryId: 112, description: 'Integrate 2.0 Forms with 2.0 Layouts'},
    {subcategoryId: 100, subcategory: '100', category: 'Ext Forms: Field Anchoring', categoryId: 113, description: 'Implement AnchorLayout'},
    {subcategoryId: 100, subcategory: '100', category: 'Ext Forms: Field Anchoring', categoryId: 114, description: 'Add support for multiple types of anchors'},
    {subcategoryId: 100, subcategory: '100', category: 'Ext Forms: Field Anchoring', categoryId: 115, description: 'Testing and debugging'},
    {subcategoryId: 101, subcategory: '101', category: 'Ext Grid: Single-level Grouping', categoryId: 101, description: 'Add required rendering "hooks" to GridView'},
    {subcategoryId: 101, subcategory: '101', category: 'Ext Grid: Single-level Grouping', categoryId: 102, description: 'Extend GridView and override rendering functions'},
    {subcategoryId: 101, subcategory: '101', category: 'Ext Grid: Single-level Grouping', categoryId: 103, description: 'Extend Store with grouping functionality'},
    {subcategoryId: 101, subcategory: '101', category: 'Ext Grid: Single-level Grouping', categoryId: 121, description: 'Default CSS Styling'},
    {subcategoryId: 101, subcategory: '101', category: 'Ext Grid: Single-level Grouping', categoryId: 104, description: 'Testing and debugging'},
    {subcategoryId: 102, subcategory: '102', category: 'Ext Grid: Summary Rows', categoryId: 105, description: 'Ext Grid plugin integration'},
    {subcategoryId: 102, subcategory: '102', category: 'Ext Grid: Summary Rows', categoryId: 106, description: 'Summary creation during rendering phase'},
    {subcategoryId: 102, subcategory: '102', category: 'Ext Grid: Summary Rows', categoryId: 107, description: 'Dynamic summary updates in editor grids'},
    {subcategoryId: 102, subcategory: '102', category: 'Ext Grid: Summary Rows', categoryId: 108, description: 'Remote summary integration'},
    {subcategoryId: 102, subcategory: '102', category: 'Ext Grid: Summary Rows', categoryId: 109, description: 'Summary renderers and calculators'},
    {subcategoryId: 102, subcategory: '102', category: 'Ext Grid: Summary Rows', categoryId: 110, description: 'Integrate summaries with GroupingView'},
    {subcategoryId: 102, subcategory: '102', category: 'Ext Grid: Summary Rows', categoryId: 111, description: 'Testing and debugging'}
];

Ext.onReady(function(){

    Ext.tip.QuickTipManager.init();

    var store = Ext.create('Ext.data.Store', {
        model: 'CategoryModel',
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
        plugins: [cellEditing],
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
            header: 'Category',
            width: 180,
            dataIndex: 'category'
        }, {
            header: 'Sub Category',
            flex: 1,
            width: 180,
            sortable: true,
            dataIndex: 'subcategory',
            hideable: false,
            summaryType: 'count',
            summaryRenderer: function(value, summaryData, dataIndex) {
                return ((value === 0 || value > 1) ? '(' + value + ' Sub categories)' : '(1 Sub category)');
            }
        }, {
            header: 'Description',
            width: 180,
            sortable: true,
            dataIndex: 'description',
            field: {
                xtype: 'textfield'
            }
        }]
    });
});