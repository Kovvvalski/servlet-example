<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 25.01.2024
  Time: 2:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>500</title>
</head>
<body>

Request from : ${pageContext.errorData.requestURI} is failed<br/>
Servlet name : ${pageContext.errorData.servletName} <br/>
Status code : ${pageContext.errorData.statusCode} <br/>
Exception : ${pageContext.exception} <br/><br/>
Message from exception: ${error_msg} <br/>
<form action="controller">
<input type="hidden" name="command" value="default"/>
<input type="submit" name="syn" value="Back to main">
</form>
</body>
</html>
