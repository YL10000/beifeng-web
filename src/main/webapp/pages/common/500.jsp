<%@page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    Throwable e = null;
    if (request.getAttribute("javax.servlet.error.exception") != null) {
        e = (Throwable) request.getAttribute("javax.servlet.error.exception");
    }
%>
<!DOCTYPE HTML>
<html>
<head>
<title>ibeifeng 500 - 系统内部错误</title>
</head>
<body>
	<div>
		<h2>系统发生内部错误.</h2>
		<div>
			<%=e.getLocalizedMessage()%>
		</div>
		<a href="<c:url value="/"/>">返回首页</a>
	</div>
</body>
</html>
