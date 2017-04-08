<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<jsp:include page="header.jsp"/>

<title>Home</title>

<script type="text/javascript">
    Ext.require([
        'app.view.main.Main'
    ]);

    Ext.application({
        name: 'app',

        launch: function() {
             Ext.create('app.view.main.Main');
        }
    });
</script>
</head>
<body>
</body>
