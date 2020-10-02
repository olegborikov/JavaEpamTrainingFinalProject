<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="i18n.application_message"/>
<html>
<head>
    <title><fmt:message key="registration.title"/></title>
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
                    <div class="register-form">
                        <form name="registrationForm" method="post" action="controller"
                              autocomplete="off">
                            <div class="form-group">
                                <label><fmt:message key="registration.email"/></label>
                                <input style="background-color: black" type="text"
                                       class="form-control text-white" name="email"
                                       maxlength="55" required
                                       oninvalid="this.setCustomValidity('<fmt:message
                                               key="registration.emailValidate"/>')"
                                       onchange="this.setCustomValidity('')"
                                       pattern="^[\w.+-]{3,30}@[\w.-]{2,15}\.[\p{Lower}]{2,4}$"
                                       placeholder=<fmt:message key="registration.email"/>>
                            </div>
                            <div class="form-group">
                                <label><fmt:message key="registration.login"/></label>
                                <input style="background-color: black" type="text"
                                       class="form-control text-white" name="login"
                                       maxlength="20" required
                                       oninvalid="this.setCustomValidity('<fmt:message
                                               key="registration.loginValidate"/>')"
                                       onchange="this.setCustomValidity('')"
                                       pattern="^[\w.]{3,20}$"
                                       placeholder=<fmt:message key="registration.login"/>>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <label><fmt:message key="registration.firstName"/></label>
                                    <input style="background-color: black" type="text"
                                           class="form-control text-white" name="firstName"
                                           maxlength="25" required
                                           oninvalid="this.setCustomValidity('<fmt:message
                                                   key="registration.firstNameValidate"/>')"
                                           onchange="this.setCustomValidity('')"
                                           pattern="^\p{L}{2,25}$"
                                           placeholder=<fmt:message key="registration.firstName"/>>
                                </div>
                                <div class="form-group col-sm-6">
                                    <label><fmt:message key="registration.secondName"/></label>
                                    <input style="background-color: black" type="text"
                                           class="form-control text-white" name="secondName"
                                           maxlength="25" required
                                           oninvalid="this.setCustomValidity('<fmt:message
                                                   key="registration.secondNameValidate"/>')"
                                           onchange="this.setCustomValidity('')"
                                           pattern="^\p{L}{2,25}$"
                                           placeholder=<fmt:message key="registration.secondName"/>>
                                </div>
                            </div>
                            <div class="form-group">
                                <label> <fmt:message key="registration.phoneNumber"/></label>
                                <input style="background-color: black" type="text"
                                       class="form-control text-white" name="phoneNumber"
                                       maxlength="13" required
                                       oninvalid="this.setCustomValidity('<fmt:message
                                               key="registration.phoneNumberValidate"/>')"
                                       onchange="this.setCustomValidity('')"
                                       pattern="\+?375(24|25|29|33|44)\d{7}|80(24|25|29|33|44)\d{7}$"
                                       placeholder=<fmt:message key="registration.phoneNumber"/>>
                            </div>
                            <div class="form-group">
                                <label><fmt:message key="registration.password"/></label>
                                <input style="background-color: black" type="password" id="password"
                                       class="form-control text-white" name="password"
                                       maxlength="20" required
                                       name="password" class="form-control text-white"
                                       oninvalid="this.setCustomValidity('<fmt:message
                                               key="registration.passwordValidate"/>')"
                                       onchange="this.setCustomValidity('')"
                                       placeholder=<fmt:message key="registration.password"/>>
                            </div>
                            <div class="form-group">
                                <label><fmt:message key="registration.passwordConfirm"/></label>
                                <input style="background-color: black" type="password"
                                       id="confirmedPassword" class="form-control text-white"
                                       name="confirmedPassword"
                                       maxlength="20" required
                                       name="password" class="form-control text-white"
                                       oninvalid="this.setCustomValidity('<fmt:message
                                               key="registration.passwordValidate"/>')"
                                       onchange="this.setCustomValidity('')"
                                       placeholder=<fmt:message
                                        key="registration.passwordConfirm"/>>
                            </div>
                            <div style="color: red"> ${errorDataMessage}</div>
                            <button type="submit" class="btn btn-outline-secondary"
                                    name="commandName" value="registration_command">
                                <fmt:message key="registration.signUp"/>
                            </button>
                            <button type="submit" class="btn btn-outline-secondary"
                                    name="commandName"
                                    value="browse_login_page_command">
                                <fmt:message key="registration.logIn"/>
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
