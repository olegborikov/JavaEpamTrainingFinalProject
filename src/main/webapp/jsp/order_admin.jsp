<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="i18n.application_message"/>
<html>
<head>
    <title><fmt:message key="orderAdmin.title"/></title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/css/project.css">
</head>
<body id="page-top">
<jsp:include page="navbar.jsp"/>
<section style="text-align: left" class="masthead content-section">
    <div class="intro-body">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-5">
                    <img style="max-width: 80%;max-height: 80%"
                         src="/images/${order.tattoo.image.name}.jpg"
                         alt="Responsive image" class="img-fluid">
                </div>
                <div class="col-md-4">
                    <br/>
                    <h3 class="text-white"><fmt:message key="orderAdmin.information"/></h3>
                    <p><fmt:message key="orderAdmin.tattooName"/> ${order.tattoo.name}</p>
                    <p><fmt:message key="orderAdmin.description"/> ${order.description}</p>
                    <p><fmt:message key="orderAdmin.price"/> ${order.price} <fmt:message key="tattoo.rubles"/></p>
                    <p><fmt:message key="orderAdmin.date"/> ${order.date}</p>
                    <p><fmt:message key="orderAdmin.orderedBy"/> ${order.user.login}</p>
                    <p>
                        <fmt:message key="orderAdmin.isConfirmed"/>
                        <c:choose>
                            <c:when test="${!order.confirmed}">
                                <fmt:message key="orderAdmin.no"/>
                                <form method="post" action="/bullfinch">
                                    <input type="hidden" name="orderId" value="${order.orderId}">
                                    <button class="btn btn-outline-secondary"
                                            name="commandName"
                                            value="submit_order_command">
                                        <fmt:message key="orderAdmin.submit"/>
                                    </button>
                                    <button class="btn btn-outline-secondary"
                                            name="commandName"
                                            value="deny_order_command">
                                        <fmt:message key="orderAdmin.deny"/>
                                    </button>
                                 </form>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="orderAdmin.yes"/>
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
                <div class="col-md-1">
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
