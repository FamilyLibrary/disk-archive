	Ext.Loader.setConfig({
		  enabled: true,
		  paths:{MyApp:'js'}
	});
	Ext.require([
	  'Ext.form.*',
	  'Ext.toolbar.*',
	  'Ext.button.*',
	  'MyApp.view.Register'
	]);

Ext.onReady(function(){
  var mypanel = Ext.create('MyApp.view.Register',{
    title:'Register',
    renderTo: Ext.getBody(),
items: [{
xtype: 'numberfield',
anchor: '60%',
  fieldLabel: 'Customer ID'
},{
xtype: 'textfield',
anchor: '-18',
fieldLabel: 'Name'
},{
xtype: 'textfield',
fieldLabel: 'Phone'
}]
  });

  console.log ('Ok');
});