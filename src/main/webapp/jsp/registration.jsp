<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Registration</title>
    <meta name="viewport" content="width=device-width , initial-scale=1">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/project.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/project.js"></script>
</head>
<body>

<div class="sidenav">
    <div class="login-main-text">
        <h2>Bullfinch<br>Registration Page</h2>
        <p>Sign up from here to access</p>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12 col-sm-6">
        <div class="register-form">
            <form name="registrationForm" method="post" action="controller" autocomplete="off">
                <div class="form-group">
                    <label>Email</label>
                    <input type="text" class="form-control" placeholder="Email" name="email">
                </div>
                <div class="form-group">
                    <label>Login</label>
                    <input type="text" class="form-control" placeholder="Login" name="login">
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <label>First name</label>
                        <input type="text" class="form-control" placeholder="First name"
                               name="firstName">
                    </div>
                    <div class="form-group col-sm-6">
                        <label>Second name</label>
                        <input type="text" class="form-control" placeholder="Second name"
                               name="secondName">
                    </div>
                </div>
                <div class="form-group">
                    <label>Phone number</label>
                    <input type="text" class="form-control" placeholder="Phone number"
                           name="phoneNumber">
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" id="password" class="form-control" placeholder="Password"
                           name="password">
                </div>
                <div class="form-group">
                    <label>Password(Confirm)</label>
                    <input type="password" id="confirmedPassword" class="form-control"
                           placeholder="Password(Confirm)"
                           name="confirmedPassword">
                </div>
                <div style="color: red"> ${errorDataMessage}</div>
                <button type="submit" class="btn btn-black" <%--onclick="ansValidation(event)"--%>
                        name="commandName"
                        value="registration_command">
                    Sign up
                </button>
                <button type="submit" class="btn btn-secondary" name="commandName"
                        value="browse_login_page_command">
                    Login
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
