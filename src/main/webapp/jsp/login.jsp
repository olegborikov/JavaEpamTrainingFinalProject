<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css\bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css\login.css">
    <script type="text/javascript" src="js\bootstrap.min.js"></script>
</head>
<body>

<div class="sidenav">
    <div class="login-main-text">
        <h2>Application<br> Login Page</h2>
        <p>Login or register from here to access.</p>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">
        <div class="login-form">
            <form name="loginRegisterForm" method="post" action="controller" autocomplete="off">
                <div class="form-group">
                    <label>Login</label>
                    <input type="text" class="form-control" placeholder="Login" name="login">
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" class="form-control" placeholder="Password" name="password">
                </div>
                <div style="color: red">${errorLoginPasswordMessage}</div>
                <button type="submit" class="btn btn-black" name="commandName" value="login_command">Login</button>
                <button type="submit" class="btn btn-secondary" name="commandName" value="register_command">Register</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
