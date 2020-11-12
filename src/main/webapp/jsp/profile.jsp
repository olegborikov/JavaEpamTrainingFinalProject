<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="i18n.application_message"/>
<html>
<head>
    <title><fmt:message key="profile.title"/></title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/css/project.css">
</head>
<style>
    .list-group {
        max-height: 250px;
        margin-bottom: 10px;
        overflow: scroll;
        -webkit-overflow-scrolling: touch;
    }
</style>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/fragment/navbar.jsp"/>
<section style="text-align: left" class="masthead content-section">
    <div class="intro-body">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-5">
                    <form name="editProfileForm" method="post" action="/bullfinch">
                        <input type="hidden" name="userId" value="${user.userId}">
                        <div style="text-align: left">
                            <button class="btn btn-outline-secondary"
                                    name="commandName" value="browse_profile_edit_page_command">
                                <fmt:message key="profile.editProfile"/>
                            </button>
                        </div>
                    </form>
                    <br/>
                    <h3 class="text-white"><fmt:message key="profile.information"/></h3>
                    <p><fmt:message key="profile.login"/> ${user.login}</p>
                    <p><fmt:message key="profile.email"/> ${user.email}</p>
                    <p><fmt:message key="profile.name"/> ${user.firstName} </p>
                    <p><fmt:message key="profile.surname"/> ${user.secondName} </p>
                    <p><fmt:message key="profile.phoneNumber"/> ${user.phoneNumber} </p>
                    <p><fmt:message key="profile.walletBalance"/> ${user.wallet.balance} <fmt:message key="profile.rubles"/></p>
                    <form name="allowDeleteForm" method="post" action="/bullfinch">
                        <input type="hidden" name="walletId" value="${user.wallet.walletId}">
                        <button type="submit" class="btn btn-outline-secondary" name="commandName"
                                value="browse_balance_enrich_page_command">
                            <fmt:message key="profile.enrich"/>
                        </button>
                    </form>
                </div>
                <div class="col-md-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading"><h3 class="panel-title">
                            <fmt:message key="profile.orders"/></h3>
                        </div>
                        <form method="post" action="/bullfinch">
                            <div class="panel-body">
                                <ul class="list-group">
                                    <c:if test="${empty orders}">
                                        <li style="color: black" class="list-group-item">
                                            <strong><fmt:message key="profile.emptyOrders"/></strong>
                                        </li>
                                    </c:if>
                                    <input type="hidden" name="commandName"
                                           value="browse_order_page_command">
                                    <c:forEach var="order" items="${orders}">
                                        <li style="color: black" class="list-group-item">
                                            <button class="btn" name="orderId" value="${order.orderId}">
                                                <strong>${order.tattoo.name}, ${order.price}, ${order.date}</strong>
                                            </button>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </form>
                    </div>
                    <br/>
                    <div class="panel panel-primary">
                        <div class="panel-heading"><h3 class="panel-title">
                            <fmt:message key="profile.discounts"/></h3>
                        </div>
                        <div class="panel-body">
                            <ul class="list-group">
                                <c:if test="${empty discounts}">
                                    <li style="color: black" class="list-group-item">
                                        <strong><fmt:message key="profile.emptyDiscounts"/></strong>
                                    </li>
                                </c:if>
                                <c:forEach var="discount" items="${discounts}">
                                    <li style="color: black" class="list-group-item">
                                            <span class="btn">
                                                <strong>${discount.discountPercent}
                                                 <fmt:message key="profile.percent"/></strong>
                                            </span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-1">
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="${pageContext.request.contextPath}/jsp/fragment/footer.jsp"/>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/js/project.js"></script>
</body>
</html>
