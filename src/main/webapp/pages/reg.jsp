<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>reg</title>
</head>
<body>
<form action="controller">
    <input type="hidden" name="command" value="registration"/>
    Login: <label>
    <input type="text" name="login" value=""/>
</label>
    <br/>
    Password: <label>
    <input type="password" name="password" value=""/>
</label>
    <br/>
    First name: <label>
    <input type="text" name="first_name" value=""/>
</label>
    <br/>
    Last name: <label>
    <input type="text" name="last_name" value=""/>
</label>
    <br/>
    Email: <label>
    <input type="email" name="email" value=""/>
</label>
    <br/>
    <input type="submit" name="sub" value="Register"/>
    <br/>
    ${reg_message}
    <br/>
</form>
</body>
</html>
