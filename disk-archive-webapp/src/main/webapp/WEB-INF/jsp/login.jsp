<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
	
	<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="ext/ext-all-debug.js"></script>

	<title>${title}</title>
</head>
<script type="text/javascript">
	Ext.onReady(function(){
		Ext.QuickTips.init();	
	});
</script>

<body>
	<div style="margin-left: 40%; margin-top: 10%;">
	<table>
		<tr>
			<td colspan="2" style="text-align: center">
				<h2>Please input password</h2>
			</td>
		</tr>
		<tr>
			<td>
				<form action="j_acegi_security_check">
					<input name="j_username"/><br/>
					<input type="password" name="j_password"/><br/>
					<input type="submit" value="OK"/>
				</form>
			</td>
			<td>
				<img src="images/login-scaled.jpg"/>
			</td>
		</tr>
	</table>
	</div>
</body>

</html>