<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>

Hello, ${login}


<br/>
<hr>
${filter_attr}
<hr/>
<form action="controller">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="logout">
</form>
<br/>
<form action="controller">
    <input type="hidden" name="command" value="profile">
    <input type="submit" value="View profile">
</form>


</body>
</html>
