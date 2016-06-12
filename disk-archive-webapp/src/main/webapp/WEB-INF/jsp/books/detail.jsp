<html>
<head>
<title>My read books</title>
</head>
<body>
	<form action="" method="post">
		<table border="1" style="width: 50%;align="middle">
			<tr>
				<td><label for="month">Month: </label></td>
				<td><select style="width: 100%;">
				<option value="1">January</option>
				<option value="2">February</option>
				<option value="3">March</option>
				<option value="4">April</option>
				<option value="5">May</option>
				<option value="6">June</option>
				<option value="7">July</option>
				<option value="8">August</option>
				<option value="9">September</option>
				<option value="10">October</option>
				<option value="11">November</option>
				<option value="12">December</option>
				</select></td>
			</tr>
			<tr>
				<td><label for="year">Year: </label></td>
				<td><input type="text" id="year" style="width: 100%;"></td>
			</tr>
			<tr>
				<td><label for="bookName">Name: </label></td>
				<td><input type="text" id="bookName" style="width: 100%;"
					value="${book.name}" readonly="readonly"></td>
			</tr>
			<tr>
				<td><label for="author">Author: </label></td>
				<td><input type="text" id="author" style="width: 100%;"
					value="Sholohov" readonly="readonly"></td>
			</tr>
			<tr>
				<td><label for="yearOfPublication">Year of publication:
				</label></td>
				<td><input type="text" id="yearOfPublication"
					style="width: 100%;" value="${book.yearOfPublication}" readonly="readonly"></td>
			</tr>
			<tr>
				<td><label for="group">Group: </label></td>
				<td><select style="width: 100%;" >
						<option value="history">History</option>
						<option value="fiction">Fiction</option>
				</select></td>
			</tr>
			<tr>
				<td><label for="description">Description: </label></td>
				<td><textarea rows="20" cols="80" id="description"
						readonly="readonly">${book.description}</textarea>
				</td>
			</tr>
			<tr>
                <td colspan="2" align="middle">
                   <input type="button" value="Do it!!!" onclick="javascript: {alert('I am here!!!');}">
                   <input type="button" value="Back" onclick="javascript: {window.history.back();}">
                </td>
			</tr>
		</table>
	</form>
</body>
</html>