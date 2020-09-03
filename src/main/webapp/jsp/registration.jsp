<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <meta name="viewport" content="width=device-width , initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css\bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css\login_registration.css">
    <script type="text/javascript" src="js\bootstrap.min.js"></script>
</head>
<body>

<div class="sidenav">
    <div class="login-main-text">
        <h2>Application<br> Registration Page</h2>
        <p>Register from here to access</p>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">
        <div class="register-form">
            <form name="registerForm" method="post" action="controller" autocomplete="off">
                <div class="form-group">
                    <label>Email</label>
                    <input type="text" class="form-control" placeholder="Email" name="email">
                    <p class="help-block">Please provide your E-mail</p>
                </div>
                <div class="form-group">
                    <label>Login</label>
                    <input type="text" class="form-control" placeholder="Login" name="login">
                    <p class="help-block">Username can contain any letters or numbers, without spaces</p>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" class="form-control" placeholder="Password" name="password">
                    <p class="help-block">Password should be at least 4 characters</p>
                </div>
                <div class="form-group">
                    <label>Password(Confirm)</label>
                    <input type="password" class="form-control" placeholder="Password(Confirm)"
                           name="passwordConfirm">
                    <p class="help-block">Please confirm password</p>
                </div>
                <div style="color: red">${errorLoginPasswordMessage}</div>
                <button type="submit" class="btn btn-black" name="commandName" value="registration_command">
                    Registration
                </button>
                <button type="submit" class="btn btn-secondary" name="commandName" value="browse_login_command">
                    Login
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
