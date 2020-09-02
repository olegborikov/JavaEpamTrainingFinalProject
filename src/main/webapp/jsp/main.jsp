<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Main</title>
    <link rel="stylesheet" type="text/css" href="css\bootstrap.min.css">
    <script type="text/javascript" src="js\bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div>
        <form name="logoutForm" method="post" action="controller">
            <input type="hidden" name="commandName" value="logout_command">
            <br/>
            <input type="submit" value="Logout"/>
        </form>
    </div>
</div>
</body>
</html>
