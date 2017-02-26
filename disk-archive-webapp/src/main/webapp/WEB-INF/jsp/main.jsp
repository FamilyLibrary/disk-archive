<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<jsp:include page="header.jsp"/>

<title>${title}</title>

<script type="text/javascript">
    Ext.require([
        'AppsPortal.component.treeView.MainTreeView',
        'AppsPortal.component.treeView.MainTreeViewStore'
    ]);

    Ext.application({
        name: 'AppsPortal',

        launch: function() {
             Ext.create('AppsPortal.component.treeView.MainTreeView');
        }
    });
</script>
</head>
<body>
</body>
