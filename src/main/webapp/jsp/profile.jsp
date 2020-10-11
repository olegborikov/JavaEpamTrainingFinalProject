<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="i18n.application_message"/>
<html>
<head>
    <title><fmt:message key="profile.title"/></title>
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
<section style="text-align: left" class="masthead">
    <div class="intro-body">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-5">
                    <h3 class="text-white"><fmt:message key="profile.information"/></h3>
                    <p><fmt:message key="profile.login"/> ${user.login}</p>
                    <p><fmt:message key="profile.email"/> ${user.email}</p>
                    <p><fmt:message key="profile.name"/> ${user.firstName} </p>
                    <p><fmt:message key="profile.surname"/> ${user.secondName} </p>
                    <p><fmt:message key="profile.phoneNumber"/> ${user.phoneNumber} </p>
                    <p><fmt:message key="profile.walletBalance"/> ${user.wallet.balance} </p>
                    <p><fmt:message key="profile.userRating"/> ${user.userRating.name} </p>
                </div>
                <div class="col-md-4">
                </div>
                <div class="col-md-1">
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>