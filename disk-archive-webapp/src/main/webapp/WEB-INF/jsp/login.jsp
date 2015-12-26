<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="login.html" var="spring_security_check"/>

<html>
<head>
	<jsp:include page="header.jsp"/>
	<title>${title}</title>
</head>
<!--script type="text/javascript">
    Ext.onReady(function(){
        Ext.QuickTips.init();   
    });
</script-->

<body>
	<div style="margin-left: 30%; margin-top: 10%;">
	<table style="width:60%">
		<tr>
			<td colspan="2" style="text-align: left">
				<h2>Please input password</h2>
			</td>
		</tr>
        <tr>
            <td colspan="2">
                <c:if test="${param.error != null}">
                    <div style="color:red">
                           <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
                    </div>
                </c:if>
            </td>
        </tr>
		<tr>
			<td colspan="2">
                <table style="width:100%;">
                <tr>
                    <td>
						<form:form action="${spring_security_check}">
							<input name="j_username" style="width: 100%;"/><br/>
							<input type="password" name="j_password" style="width: 100%;"/><br/>
							<input type="submit" value="Login"/>&nbsp;
							<a style="float:right" href="/register.html">Register</a>
		
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						</form:form>
				    </td>
				    <td align="center">
                        <img src="/images/login-scaled.jpg"/>
				    </td>
				</tr>
				</table>
			</td>
		</tr>
	</table>
	</div>
</body>

</html>