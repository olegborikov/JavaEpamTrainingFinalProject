<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customTags" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="i18n.application_message"/>
<html>
<head>
    <title><fmt:message key="usersAdmin.title"/></title>
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
<section style="padding-top: 120px" class="masthead">
    <div class="container">
        <div class="row">
            <div class="col-lg-6"></div>
            <div class="col-lg-4 mb-5">
                <form method="post" action="controller"
                      autocomplete="off">
                    <div class="input-group">
                        <input style="background-color: black" maxlength="25"
                               type="text" class="form-control text-white"
                               name="searchLogin" value="${searchLogin}"
                               placeholder=<fmt:message key="usersAdmin.login"/>>
                        <div class="input-group-btn">
                            <button type="submit"
                                    class="btn btn-outline-secondary"
                                    name="commandName"
                                    value="find_users_command">
                                <fmt:message key="usersAdmin.search"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-8 mb-5">
                <div class="row form-group">
                    <c:if test="${empty users}">
                        <div class="masthead content-section text-center">
                            <div class="container">
                                <h3><fmt:message key="catalog.empty"/></h3>
                            </div>
                        </div>
                    </c:if>
                    <ctg:pagination-users-admin pageNumber="${pageNumber}"
                                          usersAmountOnPage="${usersAmountOnPage}"/>
                </div>
                <c:if test="${not empty users && users.size() > usersAmountOnPage}">
                    <form method="post" action="controller">
                        <input type="hidden" name="commandName" value="pagination_command">
                        <c:choose>
                            <c:when test="${pageNumber != 1}">
                                <button type="submit" class="btn btn-outline-secondary"
                                        name="pageNumber" value=${pageNumber - 1}>
                                    <fmt:message key="catalog.paginationPrevious"/>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" class="btn btn-outline-secondary" disabled>
                                    <fmt:message key="catalog.paginationPrevious"/>
                                </button>
                            </c:otherwise>
                        </c:choose>
                        <input type="button" class="btn btn-outline-secondary"
                               disabled value=${pageNumber}>
                        <c:choose>
                            <c:when test="${pageNumber < pageAmount}">
                                <button type="submit" class="btn btn-outline-secondary"
                                        name="pageNumber" value=${pageNumber + 1}>
                                    <fmt:message key="catalog.paginationNext"/>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" class="btn btn-outline-secondary" disabled>
                                    <fmt:message key="catalog.paginationNext"/>
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
