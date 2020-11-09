<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="i18n.application_message"/>
<html>
<head>
    <title><fmt:message key="profileEdit.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/css/project.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<section style="text-align: left" class="masthead">
    <div class="intro-body">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-3">
                    <div>
                        <form method="post" action="/bullfinch"
                              autocomplete="off">
                            <input type="hidden" name="userId" value="${user.userId}">
                            <div class="form-group">
                                <label><fmt:message key="profileEdit.email"/></label>
                                <input style="background-color: black" type="text"
                                       class="form-control text-white" name="email" readonly
                                       title='<fmt:message key="profileEdit.email"/>'
                                       value='${user.email}'>
                            </div>
                            <div class="form-group">
                                <label><fmt:message key="profileEdit.login"/></label>
                                <input style="background-color: black" type="text"
                                       class="form-control text-white" name="login" maxlength="20" readonly
                                       title='<fmt:message key="profileEdit.login"/>'
                                       value='${user.login}' >
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <label><fmt:message key="profileEdit.firstName"/></label>
                                    <input style="background-color: black" type="text"
                                           class="form-control text-white" name="firstName" maxlength="25" required
                                           oninvalid="this.setCustomValidity('<fmt:message key="profileEdit.firstNameValidate"/>')"
                                           onchange="this.setCustomValidity('')"
                                           pattern="^[a-zA-ZА-Яа-яЁё]{2,25}$"
                                           title='<fmt:message key="profileEdit.firstNameValidate"/>'
                                           value='${user.firstName}'
                                           placeholder='<fmt:message key="profileEdit.firstName"/>'>
                                </div>
                                <div class="form-group col-sm-6">
                                    <label><fmt:message key="profileEdit.secondName"/></label>
                                    <input style="background-color: black" type="text"
                                           class="form-control text-white" name="secondName" maxlength="25" required
                                           oninvalid="this.setCustomValidity('<fmt:message key="profileEdit.secondNameValidate"/>')"
                                           onchange="this.setCustomValidity('')"
                                           pattern="^[a-zA-ZА-Яа-яЁё]{2,25}$"
                                           title='<fmt:message key="profileEdit.secondNameValidate"/>'
                                           value='${user.secondName}'
                                           placeholder='<fmt:message key="profileEdit.secondName"/>'>
                                </div>
                            </div>
                            <div class="form-group">
                                <label> <fmt:message key="profileEdit.phoneNumber"/></label>
                                <input style="background-color: black" type="text"
                                       class="form-control text-white" name="phoneNumber" maxlength="13" required
                                       oninvalid="this.setCustomValidity('<fmt:message key="profileEdit.phoneNumberValidate"/>')"
                                       onchange="this.setCustomValidity('')"
                                       pattern="\+?375(24|25|29|33|44)\d{7}|80(24|25|29|33|44)\d{7}$"
                                       title='<fmt:message key="profileEdit.phoneNumberValidate"/>'
                                       value='${user.phoneNumber}'
                                       placeholder='<fmt:message key="profileEdit.phoneNumber"/>'>
                            </div>
                            <c:if test="${incorrectDataMessage}">
                                <div style="color: red">
                                    <fmt:message key="profileEdit.incorrectDataMessage"/>
                                </div>
                            </c:if>
                            <button class="btn btn-outline-secondary"
                                    name="commandName" value="edit_profile_command">
                                <fmt:message key="profileEdit.change"/>
                            </button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-md-7">
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
