<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="i18n.application_message"/>
<html>
<head>
    <title><fmt:message key="catalog.title"/></title>
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
<section style="padding-top: 120px" class="masthead content-section text-center">
    <div class="intro-body">
        <div class="container-fluid">
            <div class="row col-12">
                <div class="col-md-3">
                    <c:if test="${role.equals('user')}">
                        <form method="post" action="controller">
                            <button style="vertical-align: center" class="btn btn-outline-secondary"
                                    name="commandName"
                                    value="browse_tattoo_offer_page_command">
                                <fmt:message key="catalog.offerTattoo"/>
                            </button>
                        </form>
                    </c:if>
                </div>
                <div class="col-md-6">
                    <br/>
                </div>
                <div class="col-12 col-md-3">
                    <form method="post" action="controller" autocomplete="off">
                        <div class="input-group">
                            <input style="background-color: black" type="text"
                                   class="form-control text-white" maxlength="25"
                                   name="tattooName" value="${tattooName}"
                                   placeholder=<fmt:message key="catalog.search"/>>
                            <div class="input-group-btn">
                                <button type="submit" class="btn btn-outline-secondary"
                                        name="commandName" value="find_tattoos_command">
                                    <fmt:message key="catalog.search"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <br/>
            <div class="container-fluid">
                <div class="row">
                    <c:if test="${empty tattoos}">
                        <div class="masthead content-section text-center">
                            <div class="container">
                                <h3><fmt:message key="catalog.empty"/></h3>
                            </div>
                        </div>
                    </c:if>
                    <c:forEach var="tattoo" items="${tattoos}">
                        <div class="col-lg-4">
                            <div class="image-wrap-2">
                                <div class="image-info">
                                    <h2 class="mb-3">${tattoo.name}</h2>
                                    <form method="post" action="controller">
                                        <input type="hidden" name="commandName"
                                               value="browse_tattoo_page_command">
                                        <button class="btn btn-outline-white py-2 px-4"
                                                name="tattooId" value="${tattoo.tattooId}">
                                            <fmt:message key="catalog.info"/></button>
                                    </form>
                                </div>
                                <img src="/images/${tattoo.image.name}.jpg"
                                     alt="Image" class="img-fluid">
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <c:if test="${not empty tattoos}">
                    <form method="post" action="controller">
                        <input type="hidden" name="commandName" value="pagination_catalog_command">
                        <c:choose>
                            <c:when test="${pageNumber!=1}">
                                <button type="submit" class="btn btn-outline-secondary"
                                        name="pageNumber" value=${pageNumber-1}>
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
                            <c:when test="${pageNumber<pageAmount}">
                                <button type="submit" class="btn btn-outline-secondary"
                                        name="pageNumber" value=${pageNumber+1}>
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
