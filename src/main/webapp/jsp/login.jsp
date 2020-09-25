<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.application_message"/>

<html>
<head>
    <title><fmt:message key="login.title"/></title>
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
<section class="masthead">
    <div class="intro-body">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 mx-auto">
                    <form name="loginForm" method="post" action="controller" autocomplete="off">
                        <div class="form-group">
                            <label><fmt:message key="login.login"/></label>
                            <input type="text" class="form-control" placeholder="Login"
                                   name="login">
                        </div>
                        <div class="form-group">
                            <label><fmt:message key="login.password"/></label>
                            <input type="password" class="form-control" placeholder="Password"
                                   name="password">
                        </div>
                        <div style="color: red">${errorLoginPasswordMessage}</div>
                        <button type="submit" class="btn btn-dark" name="commandName"
                                value="login_command">
                            <fmt:message key="login.signIn"/>
                        </button>
                        <button type="submit" class="btn btn-dark" name="commandName"
                                value="browse_registration_page_command">
                            <fmt:message key="login.registration"/>
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
