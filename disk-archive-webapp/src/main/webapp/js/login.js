Ext.require(['MyApp.view.authentication.Login']);

Ext.onReady(function() {
	var csrfParamName = Ext.get('csrfParamName');
	var csrfParamValue = Ext.get('csrfParamValue');
	
    Ext.create('MyApp.view.authentication.Login', {
    	csrfName : csrfParamName.getValue(),
    	csrfValue : csrfParamValue.getValue()
    }).show();

    console.log('Ok');
});