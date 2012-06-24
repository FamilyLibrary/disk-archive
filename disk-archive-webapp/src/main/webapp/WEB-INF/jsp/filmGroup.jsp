<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<head>
<title>${title}</title>

<jsp:include page="header.jsp"/>

<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/interface/FilmGroupService.js'></script>

<script type='text/javascript' src='js/dataGrid.js'></script>

<script type="text/javascript">
var page = function(){
		var panelHeight = 500;

		var textEditor = new Ext.form.TextField();

		var store = new Ext.data.JsonStore({
	   		root: "rows",
	   		id: "id",
	   		data: { rows: ${rows} },
	           autoLoad: true,
	           fields:[
	   	        "id",
	   	        "name",
	            "description", 
	           ]
	    });
		
	    var gridPanel = new Ext.grid.EditorGridPanel({
	        columns: [
  	            {header: 'Группа', width: 210, sortable: true, dataIndex: 'name', editor: textEditor},
	            {header: 'Описание', width: 280, sortable: true, dataIndex: 'description', editor: textEditor},
	            {
	                xtype: 'actioncolumn',
	                sortable: false,
	                width: 50
	            }
	        ],
	        stripeRows: true,
	        height: panelHeight,
	        width:800,
	        clickstoEdit: 1,
	        store: store,
	        split: true,
			region: 'north',
			viewConfig: {
				forceFit: true
			},
	        tbar: [
	            {
		   	        text: 'Добавить',
   	        	 	icon: 'images/add.gif',
   	        	 	cls: 'x-btn-text-icon',
   	        	 	handler: function(){
	   	        		FilmGroupService.addGroup(function() {
		   	        		document.location.reload();
	   	        		});	
	   	        	}
   	        	}, {
   	 			    text: 'Сохранить',
			     	icon: 'images/save.gif',
			     	handler: function() {
                        jsonResult = DataGrid.save(store);
                        FilmGroupService.save(jsonResult, function() {
                            store.commitChanges();
                        });
   	        		}
			    }, {
                    text: 'Фильмы',
                    icon: 'images/films.gif',
                    handler: function() {
                        document.location = 'main.html';
                    }
                }
	       	]
	    });

	var panel = new Ext.Panel({
		frame: true,
		title: 'Группы фильмов',
		width: 540,
		height: panelHeight,
		layout: 'border',
		items: [
			gridPanel
		]
	});

    return {
		getPanel: function() {
			return panel; 
		}
	};
}();

Ext.onReady(function(){
	Ext.QuickTips.init();
	page.getPanel().render('grid-data');
});
</script>
</head>
<body>
	<table border="0" style="width: 100%; height: 100%;">
		<tr>
			<td style="width: 30%;">&nbsp;</td>
			<td valign="middle" style="width: 40%;">
				<div id="grid-data"></div>
			</td>
			<td style="width: 30%;">&nbsp;</td>
		</tr>
	</table>
</body>