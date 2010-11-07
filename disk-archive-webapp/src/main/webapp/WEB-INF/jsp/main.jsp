<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
	<link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
	
	<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="ext/ext-all-debug.js"></script>
	
	<title>${title}</title>
</head>

<body>
<script type="text/javascript">

	var page = function() {

		var store = new Ext.data.JsonStore({
    		root: "rows",
    		id: "name",
    		data: { rows: ${rows} },
            autoLoad: true,
            fields:[
                'id', 
                'filmName', 
                'filmGroupId', 
                'description'
            ]
        })

	    var textEditor = new Ext.form.TextField();

		Ext.util.Format.comboRenderer = function(combo){
		    return function(value){
		        var record = combo.findRecord(combo.valueField, value);
		        return record ? record.get(combo.displayField) : combo.valueNotFoundText;
		    }
		};

		var comoboxEditor = new Ext.form.ComboBox({
			typeAhead: true,
			triggerAction: 'all',
			mode: 'local',
	    	store: (function() {
				var store = new Ext.data.ArrayStore({
					id: 0,
					fields: [
						'id', 'name', 'description'
					],
					data: [
						[-1, 'Выбирите категорию'],
						<c:forEach var="group" items="${filmGroups}" varStatus="status">
							[${group.id}, '${group.name}', '${group.description}']<c:if test="${not status.last}">,</c:if>
		            	</c:forEach>
					]
				});
				return store;
	    	})(),
			displayField:'name',
			valueField: 'id',
	    	tpl: '<tpl for="."><div ext:qtip="{description}" class="x-combo-list-item">{name}</div></tpl>'
		});

	    var gridPanel = new Ext.grid.EditorGridPanel({
	        columns: [
  	            {header: 'Группа', width: 140, sortable: true, dataIndex: 'filmGroupId', editor: comoboxEditor, renderer: Ext.util.Format.comboRenderer(comoboxEditor)},
	            {header: 'Фильм', width: 280, sortable: true, dataIndex: 'filmName', editor: textEditor},
	            {header: 'Описание', width: 280, sortable: true, dataIndex: 'description', editor: textEditor}/*,
	            {
	                xtype: 'actioncolumn',
	                width: 50,
	                items: [{
	                    icon   : '../shared/icons/fam/delete.gif',  // Use a URL in the icon config
	                    tooltip: 'Sell stock',
	                    handler: function(grid, rowIndex, colIndex) {
	                        var rec = store.getAt(rowIndex);
	                        alert("Sell " + rec.get('company'));
	                    }
	                }, {
	                    getClass: function(v, meta, rec) {          // Or return a class from a function
	                        if (rec.get('change') < 0) {
	                            this.items[1].tooltip = 'Do not buy!';
	                            return 'alert-col';
	                        } else {
	                            this.items[1].tooltip = 'Buy stock';
	                            return 'buy-col';
	                        }
	                    },
	                    handler: function(grid, rowIndex, colIndex) {
	                        var rec = store.getAt(rowIndex);
	                        alert("Buy " + rec.get('company'));
	                    }
	                }]
	            }*/
	        ],
	        stripeRows: true,
	        frame:true,
	        title: 'Фильмы',
	        height:500,
	        width:717,
	        clickstoEdit: 1,
	        store: store,
	        tbar: [
	   	        {text: 'Добавить',
   	        	 icon: 'images/add.gif',
   	        	 cls: 'x-btn-text-icon'},
 			    {text: 'Save',
			     icon: 'images/save.gif',
			     handler: this.onSave,
			     scope: this},
	        	{text: 'Удалить',
      	         icon: 'images/delete.gif',
      	         cls: 'x-btn-text-icon'}
	       	]
	    });

	    return {
			getPanel: function() {
				return gridPanel; 
			}
		};
	}();

	Ext.onReady(function(){
		Ext.QuickTips.init();
		page.getPanel().render('grid-data');
	});
</script>
<table border="0" style="width:100%; height: 100%;">
	<tr>
		<td style="width: 30%;">
			&nbsp;
		</td>
		<td valign="middle"  style="width: 40%;">
			<div id="grid-data"></div>
		</td>
		<td style="width: 30%;">
			&nbsp;
		</td>
	</tr>
</table>
</body>
