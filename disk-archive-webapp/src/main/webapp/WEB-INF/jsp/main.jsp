<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<jsp:include page="header.jsp"/>

<title>${title}</title>

<script type="text/javascript">
    Ext.application({
        name: 'AppsPortal',
        launch: function() {
             console.log('launch');
        }
    });
</script>
</head>
<body>
</body>
