<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width , initial-scale=1">
    <link rel="stylesheet" type="text/css" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="assets/css/login_registration.css">
    <script type="text/javascript" src="assets/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

<div class="sidenav">
    <div class="login-main-text">
        <h2>Bullfinch<br> Login Page</h2>
        <p>Sign in from here to access</p>
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
                <button type="submit" class="btn btn-black" name="commandName" value="login_command">
                    Sign in
                </button>
                <button type="submit" class="btn btn-secondary" name="commandName" value="browse_registration_command">
                    Registration
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
