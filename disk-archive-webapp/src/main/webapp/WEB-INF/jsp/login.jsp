<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="login.html" var="spring_security_check"/>

<head>
	<title>${title}</title>
    <jsp:include page="header.jsp"/>
    <script type='text/javascript' src='<c:url value="/js/login.js"/>'></script>
</head>
<body>
	<input type="hidden" id="csrfParamName" value="${_csrf.parameterName}" />
	<input type="hidden" id="csrfParamValue" value="${_csrf.token}"/>
</body>