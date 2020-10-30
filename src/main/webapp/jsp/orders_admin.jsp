<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customTags" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="i18n.application_message"/>
<html>
<head>
    <title><fmt:message key="ordersAdmin.title"/></title>
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
<section style="padding-top: 170px" class="masthead">
    <div class="container">
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-8 mb-5">
                <div class="row form-group">
                    <c:if test="${empty orders}">
                        <div class="masthead content-section text-center">
                            <div class="container">
                                <h3><fmt:message key="ordersAdmin.empty"/></h3>
                            </div>
                        </div>
                    </c:if>
                    <ctg:pagination-orders-admin pageNumber="${pageNumber}"
                                                 ordersAmountOnPage="${ordersAmountOnPage}"/>
                </div>
                <c:if test="${not empty orders && orders.size() > ordersAmountOnPage}">
                    <form method="post" action="controller">
                        <input type="hidden" name="commandName" value="pagination_command">
                        <c:choose>
                            <c:when test="${pageNumber != 1}">
                                <button type="submit" class="btn btn-outline-secondary"
                                        name="pageNumber" value=${pageNumber - 1}>
                                    <fmt:message key="ordersAdmin.paginationPrevious"/>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" class="btn btn-outline-secondary" disabled>
                                    <fmt:message key="ordersAdmin.paginationPrevious"/>
                                </button>
                            </c:otherwise>
                        </c:choose>
                        <input type="button" class="btn btn-outline-secondary"
                               disabled value=${pageNumber}>
                        <c:choose>
                            <c:when test="${pageNumber < pageAmount}">
                                <button type="submit" class="btn btn-outline-secondary"
                                        name="pageNumber" value=${pageNumber + 1}>
                                    <fmt:message key="ordersAdmin.paginationNext"/>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" class="btn btn-outline-secondary" disabled>
                                    <fmt:message key="ordersAdmin.paginationNext"/>
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </c:if>
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
