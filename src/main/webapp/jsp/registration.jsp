<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="i18n.application_message"/>
<html>
<head>
    <title><fmt:message key="registration.title"/></title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/css/project.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<section class="masthead">
    <div class="intro-body">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 mx-auto">
                    <div class="register-form">
                        <form method="post" action="controller"
                              autocomplete="off">
                            <div class="form-group">
                                <label><fmt:message key="registration.email"/></label>
                                <input style="background-color: black" type="text"
                                       class="form-control text-white" name="email" maxlength="55" required
                                       oninvalid="this.setCustomValidity('<fmt:message key="registration.emailValidate"/>')"
                                       onchange="this.setCustomValidity('')"
                                       value="${registrationParameters.get('email')}"
                                       pattern="^[a-zA-Z_0-9.+-]{3,30}@[a-zA-Z_0-9.-]{2,15}\.[a-z]{2,4}$"
                                       title='<fmt:message key="registration.emailValidate"/>'
                                       placeholder='<fmt:message key="registration.email"/>'>
                                <c:if test="${not empty registrationParameters && empty registrationParameters.get('email')}">
                                    <div style="color: red">
                                        <fmt:message key="registration.incorrectEmail"/>
                                    </div>
                                </c:if>
                            </div>
                            <div class="form-group">
                                <label><fmt:message key="registration.login"/></label>
                                <input style="background-color: black" type="text"
                                       class="form-control text-white" name="login" maxlength="20" required
                                       oninvalid="this.setCustomValidity('<fmt:message key="registration.loginValidate"/>')"
                                       onchange="this.setCustomValidity('')"
                                       value="${registrationParameters.get('login')}"
                                       pattern="^[a-zA-Z_0-9.]{3,20}$"
                                       title='<fmt:message key="registration.loginValidate"/>'
                                       placeholder='<fmt:message key="registration.login"/>'>
                                <c:if test="${not empty registrationParameters && empty registrationParameters.get('login')}">
                                    <div style="color: red">
                                        <fmt:message key="registration.incorrectLogin"/>
                                    </div>
                                </c:if>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <label><fmt:message key="registration.firstName"/></label>
                                    <input style="background-color: black" type="text"
                                           class="form-control text-white" name="firstName" maxlength="25" required
                                           oninvalid="this.setCustomValidity('<fmt:message key="registration.firstNameValidate"/>')"
                                           onchange="this.setCustomValidity('')"
                                           value="${registrationParameters.get('firstName')}"
                                           pattern="^[a-zA-ZА-Яа-яЁё]{2,25}$"
                                           title='<fmt:message key="registration.firstNameValidate"/>'
                                           placeholder='<fmt:message key="registration.firstName"/>'>
                                    <c:if test="${not empty registrationParameters && empty registrationParameters.get('firstName')}">
                                        <div style="color: red">
                                            <fmt:message key="registration.incorrectFirstName"/>
                                        </div>
                                    </c:if>
                                </div>
                                <div class="form-group col-sm-6">
                                    <label><fmt:message key="registration.secondName"/></label>
                                    <input style="background-color: black" type="text"
                                           class="form-control text-white" name="secondName" maxlength="25" required
                                           oninvalid="this.setCustomValidity('<fmt:message key="registration.secondNameValidate"/>')"
                                           onchange="this.setCustomValidity('')"
                                           value="${registrationParameters.get('secondName')}"
                                           pattern="^[a-zA-ZА-Яа-яЁё]{2,25}$"
                                           title='<fmt:message key="registration.secondNameValidate"/>'
                                           placeholder='<fmt:message key="registration.secondName"/>'>
                                    <c:if test="${not empty registrationParameters && empty registrationParameters.get('secondName')}">
                                        <div style="color: red">
                                            <fmt:message key="registration.incorrectSecondName"/>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label> <fmt:message key="registration.phoneNumber"/></label>
                                <input style="background-color: black" type="text"
                                       class="form-control text-white" name="phoneNumber" maxlength="13" required
                                       oninvalid="this.setCustomValidity('<fmt:message key="registration.phoneNumberValidate"/>')"
                                       onchange="this.setCustomValidity('')"
                                       value="${registrationParameters.get('phoneNumber')}"
                                       pattern="\+?375(24|25|29|33|44)\d{7}|80(24|25|29|33|44)\d{7}$"
                                       title='<fmt:message key="registration.phoneNumberValidate"/>'
                                       placeholder='<fmt:message key="registration.phoneNumber"/>'>
                                <c:if test="${not empty registrationParameters && empty registrationParameters.get('phoneNumber')}">
                                    <div style="color: red">
                                        <fmt:message key="registration.incorrectPhoneNumber"/>
                                    </div>
                                </c:if>
                            </div>
                            <div class="form-group">
                                <label><fmt:message key="registration.password"/></label>
                                <input style="background-color: black" type="password"
                                       class="form-control text-white" name="password" maxlength="20" required
                                       oninvalid="this.setCustomValidity('<fmt:message key="registration.passwordValidate"/>')"
                                       onchange="this.setCustomValidity('')"
                                       value="${registrationParameters.get('password')}"
                                       title='<fmt:message key="registration.passwordValidate"/>'
                                       placeholder='<fmt:message key="registration.password"/>'>
                                <c:if test="${not empty registrationParameters && empty registrationParameters.get('password')}">
                                    <div style="color: red">
                                        <fmt:message key="registration.incorrectPassword"/>
                                    </div>
                                </c:if>
                            </div>
                            <div class="form-group">
                                <label><fmt:message key="registration.passwordConfirm"/></label>
                                <input style="background-color: black" type="password"
                                       class="form-control text-white" name="confirmedPassword" maxlength="20" required
                                       oninvalid="this.setCustomValidity('<fmt:message key="registration.passwordValidate"/>')"
                                       onchange="this.setCustomValidity('')"
                                       value="${registrationParameters.get('confirmedPassword')}"
                                       title='<fmt:message key="registration.passwordValidate"/>'
                                       placeholder='<fmt:message key="registration.passwordConfirm"/>'>
                                <c:if test="${not empty registrationParameters && empty registrationParameters.get('confirmedPassword')}">
                                    <div style="color: red">
                                        <fmt:message key="registration.incorrectConfirmedPassword"/>
                                    </div>
                                </c:if>
                                <c:if test="${not empty registrationParameters && empty registrationParameters.get('loginExists')}">
                                    <div style="color: red">
                                        <fmt:message key="registration.loginExists"/>
                                    </div>
                                </c:if>
                                <c:if test="${not empty registrationParameters && empty registrationParameters.get('emailExists')}">
                                    <div style="color: red">
                                        <fmt:message key="registration.emailExists"/>
                                    </div>
                                </c:if>
                            </div>
                            <button class="btn btn-outline-secondary"
                                    name="commandName" value="registration_command">
                                <fmt:message key="registration.signUp"/>
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/js/project.js"></script>
</body>
</html>
