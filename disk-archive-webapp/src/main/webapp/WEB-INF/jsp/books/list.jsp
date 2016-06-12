<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Books</title>
</head>
<body>
  <table border="1">
   <caption>Books</caption>
   <tr>
    <th>Id</th>
    <th>Name</th>
    <th>Volume</th>
    <th>Year of publication</th>
    <th>Detail</th>
   </tr>
   <c:forEach var="book" items="${books}">
    <tr>
    <td>${book.id}</td><td>${book.name}</td><td>${book.volume}</td><td>${book.yearOfPublication}</td>
    <td><a href="detail.html?id=${book.id}">Detail</a></td>
    </tr>
   </c:forEach>
   
  </table>
</body>
</html>