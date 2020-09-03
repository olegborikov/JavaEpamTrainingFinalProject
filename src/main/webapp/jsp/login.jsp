<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width , initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css\bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css\login_registration.css">
    <script type="text/javascript" src="js\bootstrap.min.js"></script>
</head>
<body>

<div class="sidenav">
    <div class="login-main-text">
        <h2>Application<br> Login Page</h2>
        <p>Login from here to access</p>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">
        <div class="login-form">
            <form name="loginForm" method="post" action="controller" autocomplete="off">
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
                <button type="submit" class="btn btn-secondary" name="commandName" value="browse_registration_command">
                    Registration
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
