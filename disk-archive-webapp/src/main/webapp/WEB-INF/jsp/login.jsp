<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="login.html" var="spring_security_check"/>

<head>
    <title>${title}</title>
    <jsp:include page="header.jsp"/>

    <script type='text/javascript'>
        Ext.onReady(function() {
            Ext.create('app.view.authentication.Login', {
                hasError  :
                    <c:if test="${param.error != null}">${param.error}</c:if>
                    <c:if test="${param.error == null}">false</c:if>
            });
        });
    </script>
</head>
<body>
</body>