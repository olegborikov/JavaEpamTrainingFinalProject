<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="i18n.application_message"/>

<html>
<head>
    <title><fmt:message key="message.title"/></title>
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
               <c:if test="${tattooFindErrorMessage}">
                <h2><fmt:message key="message.tattooFindErrorMessage"/></h2>
            </c:if>

            <c:if test="${tattooAllowErrorMessage}">
                <h2><fmt:message key="message.tattooAllowErrorMessage"/></h2>
            </c:if>

            <c:if test="${tattooArchiveErrorMessage}">
                <h2><fmt:message key="message.tattooArchiveErrorMessage"/></h2>
            </c:if>

            <c:if test="${tattooUnarchiveErrorMessage}">
                <fmt:message key="message.tattooUnarchiveErrorMessage"/>
            </c:if>

            <c:if test="${userEmailConfirmMessage}">
                <h2><fmt:message key="message.userEmailConfirmMessage"/></h2>
            </c:if>

            <c:if test="${userEmailConfirmPositiveMessage}">
                <h2><fmt:message key="message.userEmailConfirmPositiveMessage"/></h2>
            </c:if>

            <c:if test="${userEmailConfirmErrorMessage}">
                <h2><fmt:message key="message.userEmailConfirmErrorMessage"/></h2>
            </c:if>

            <c:if test="${tattooDeleteConfirmMessage}">
                <h2><fmt:message key="message.tattooDeleteConfirmMessage"/></h2>
            </c:if>

            <c:if test="${tattooDeleteErrorMessage}">
                <h2><fmt:message key="message.tattooDeleteErrorMessage"/></h2>
            </c:if>

            <c:if test="${tattooOfferConfirmMessage}">
                <h2><fmt:message key="message.tattooOfferConfirmMessage"/></h2>
            </c:if>

            <c:if test="${tattooAddConfirmMessage}">
                <h2><fmt:message key="message.tattooAddConfirmMessage"/></h2>
            </c:if>

            <c:if test="${userFindErrorMessage}">
                <h2><fmt:message key="message.userFindErrorMessage"/></h2>
            </c:if>

            <c:if test="${tattooOrderConfirmMessage}">
                <h2><fmt:message key="message.tattooOrderConfirmMessage"/></h2>
            </c:if>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>