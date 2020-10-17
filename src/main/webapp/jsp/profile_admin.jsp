<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="i18n.application_message"/>
<html>
<head>
    <title><fmt:message key="profileAdmin.title"/></title>
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
                <div class="col-md-5">
                    <h3 class="text-white"><fmt:message key="profileAdmin.information"/></h3>
                    <p><fmt:message key="profileAdmin.login"/> ${user.login}</p>
                    <p><fmt:message key="profileAdmin.email"/> ${user.email}</p>
                    <p><fmt:message key="profileAdmin.name"/> ${user.firstName} </p>
                    <p><fmt:message key="profileAdmin.surname"/> ${user.secondName} </p>
                    <p><fmt:message key="profileAdmin.phoneNumber"/> ${user.phoneNumber} </p>
                    <p><fmt:message key="profileAdmin.walletBalance"/> ${user.wallet.balance}</p>

                    <c:if test="${user.blocked}">
                        <form name="" method="post" action="controller">
                            <input type="hidden" name="userId" value="${user.userId}">
                            <div style="text-align: left">
                                <input type="hidden" name="login" value="${user.login}">
                                <button class="btn btn-outline-secondary"
                                        name="commandName" value="unblock_user_command">
                                    <fmt:message key="profileAdmin.unblock"/>
                                </button>
                            </div>
                        </form>
                    </c:if>

                    <c:if test="${!user.blocked}">
                        <form name="" method="post" action="controller">
                            <input type="hidden" name="userId" value="${user.userId}">
                            <div style="text-align: left">
                                <input type="hidden" name="login" value="${user.login}">
                                <button class="btn btn-outline-secondary"
                                        name="commandName" value="block_user_command">
                                    <fmt:message key="profileAdmin.block"/>
                                </button>
                            </div>
                        </form>
                    </c:if>
                </div>
                <div class="col-md-4">
                    <div class="panel panel-primary" id="result_panel">
                        <div class="panel-heading"><h3 class="panel-title">
                            <fmt:message key="profileAdmin.orders"/></h3>
                        </div>
                        <form method="post" action="controller">
                            <div class="panel-body">
                                <ul class="list-group">
                                    <c:if test="${empty orders}">
                                        <li style="color: black" class="list-group-item">
                                            <strong><fmt:message key="profileAdmin.empty"/></strong>
                                        </li>
                                    </c:if>

                                    <input type="hidden" name="commandName"
                                           value="browse_order_admin_page_command">
                                    <c:forEach var="order" items="${orders}">
                                        <li style="color: black" class="list-group-item">
                                            <button class="btn" name="orderId"
                                                    value="${order.orderId}">
                                                <strong>${order.tattoo.name}, ${order.price}, ${order.date}</strong>
                                            </button>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </form>
                    </div>
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
