<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css\bootstrap.min.css">
    <script type="text/javascript" src="js\bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div>
        <form name="loginForm" method="post" action="controller" autocomplete="off">
            <input type="hidden" name="commandName" value="login_command">
            Login:<br/>
            <input type="text" name="login"/><br/>
            Password:<br/>
            <input type="password" name="password"/><br/>
            <div style="color: red">${errorLoginPasswordMessage}</div>
            <br/>
            <input type="submit" value="Login"/>
        </form>
    </div>
</div>
</body>
</html>
