<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<head>
	<link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
	
	<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="ext/ext-all-debug.js"></script>
	
	<script type='text/javascript' src='ext/ux/fileuploadfield/FileUploadField.js'></script>
	<link rel="stylesheet" type="text/css" href="ext/ux/fileuploadfield/css/fileuploadfield.css">
	
	<script type="text/javascript">
	var page = function() {
		var msg = function(title, msg){
	        Ext.Msg.show({
	            title: title,
	            msg: msg,
	            minWidth: 200,
	            modal: true,
	            icon: Ext.Msg.INFO,
	            buttons: Ext.Msg.OK
	        });
	    };
		var panel = new Ext.FormPanel({
			fileUpload: true,
			frame: true,
	        title: 'File Upload Form',
	        autoHeight: true,
	        items: [{
	            xtype: 'fileuploadfield',
	            id: 'form-file',
	            emptyText: 'Select a File to import',
	            fieldLabel: 'File',
	            name: 'file',
	            buttonCfg: {
	                text: '',
	                iconCls: 'upload-icon'
	            }
	        }],
        	buttons: [{
                text: 'Upload',
                handler: function(){
                    if(panel.getForm().isValid()){
                    	panel.getForm().submit({
    	                    url: 'uploadFile.html?filmId=${filmId}&upload',
    	                    waitMsg: 'Uploading file...',
    	                    success: function(fp, o){
    	                        msg('Success', 'Processed file "'+o.result.file+'" on the server');
    	                    }
    	                });
                    }
                }
            },
            {
                text: 'Reset',
                handler: function(){
            	panel.getForm().reset();
            }
            }]
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
	<div id="grid-data"></div>
</body>
