<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
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
                            <input style="background-color: black" type="text"
                                   class="form-control text-white" name="login"
                                   maxlength="20" required
                                   oninvalid="this.setCustomValidity('<fmt:message
                                           key="login.loginValidate"/>')"
                                   onchange="this.setCustomValidity('')"
                                   pattern="^[\w.]{3,20}$"
                                   title='<fmt:message key="login.loginValidate"/>'
                                   placeholder='<fmt:message key="login.login"/>'>
                        </div>
                        <div class="form-group">
                            <label><fmt:message key="login.password"/></label>
                            <input style="background-color: black" type="password"
                                   maxlength="20" required
                                   name="password" class="form-control text-white"
                                   oninvalid="this.setCustomValidity('<fmt:message
                                           key="login.passwordValidate"/>')"
                                   onchange="this.setCustomValidity('')"
                                   title='<fmt:message key="login.passwordValidate"/>'
                                   placeholder='<fmt:message key="login.password"/>'>
                        </div>
                        <c:if test="${incorrectDataMessage}">
                            <div style="color: red"><fmt:message key="login.incorrectDataMessage"/></div>
                        </c:if>
                        <button type="submit" class="btn btn-outline-secondary" name="commandName"
                                value="login_command">
                            <fmt:message key="login.signIn"/>
                        </button>
                        <button type="submit" class="btn btn-outline-secondary" name="commandName"
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
