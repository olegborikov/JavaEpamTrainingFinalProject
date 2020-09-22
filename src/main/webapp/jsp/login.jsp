<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/css/project.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/assets/js/project.js"></script>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<section class="masthead"
         style="background-image:url('${pageContext.request.contextPath}/assets/image/reception.jpg');">
    <div class="intro-body">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 mx-auto">
                    <form name="loginForm" method="post" action="controller" autocomplete="off">
                        <div class="form-group">
                            <label class="stroke">Login</label>
                            <input type="text" class="form-control" placeholder="Login"
                                   name="login">
                        </div>
                        <div class="form-group">
                            <label class="stroke">Password</label>
                            <input type="password" class="form-control" placeholder="Password"
                                   name="password">
                        </div>
                        <div style="color: red">${errorLoginPasswordMessage}</div>
                        <button type="submit" class="btn btn-black" name="commandName"
                                value="login_command">
                            Sign in
                        </button>
                        <button type="submit" class="btn btn-secondary" name="commandName"
                                value="browse_registration_page_command">
                            Registration
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
