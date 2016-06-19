<%@page import="org.apache.taglibs.standard.lang.jstl.test.PageContextImpl"%>
<%@page import="com.alextim.bookshelf.entity.BookGroup"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <jsp:include page="../header.jsp"/>

    <script type='text/javascript'>
       var data = ${json};
    </script>

    <script type='text/javascript' src='<c:url value="/js/categories/list.js"/>'></script>
	<title>Categories</title>
</head>
<body>
     <div id="gridDiv"></div>
</body>