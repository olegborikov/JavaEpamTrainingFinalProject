<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="i18n.application_message"/>
<html>
<head>
    <title><fmt:message key="tattooAdmin.title"/></title>
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
<section style="text-align: left; padding-top: 120px" class="masthead">
    <div class="intro-body">
        <div class="container-fluid">
            <div class="row col-12">
                <div class="col-md-3">
                </div>
                <div class="col-md-5">
                    <br/>
                </div>
                <div class="col-12 col-md-3">
                    <form name="offerForm" method="post" action="controller">
                        <input type="hidden" name="tattooId" value="${tattoo.tattooId}">
                        <div style="text-align: right">
                            <button style="vertical-align: center" class="btn btn-outline-secondary"
                                    name="commandName"
                                    value="browse_tattoo_edit_page_command">
                                <fmt:message key="tattooAdmin.editTattoo"/>
                            </button>
                        </div>
                    </form>
                </div>
                <div class="col-md-1">
                </div>
            </div>
            <br/>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-5">
                    <img style="max-width: 80%;max-height: 80%"
                         src="/images/${tattoo.image.name}.jpg"
                         alt="Responsive image" class="img-fluid">
                </div>
                <div class="col-md-4">
                    <br/>
                    <h3 class="text-white"><fmt:message key="tattooAdmin.information"/></h3>
                    <p><fmt:message key="tattooAdmin.name"/> ${tattoo.name}</p>
                    <p><fmt:message key="tattooAdmin.description"/> ${tattoo.description}</p>
                    <p><fmt:message key="tattooAdmin.price"/>
                        <c:choose>
                            <c:when test="${empty tattoo.price}">
                                <fmt:message key="tattooAdmin.none"/>
                            </c:when>
                            <c:otherwise>
                                ${tattoo.price} <fmt:message key="tattooAdmin.rubles"/>
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <p><fmt:message key="tattooAdmin.rating"/> ${tattoo.rating}</p>
                    <p>
                        <fmt:message key="tattooAdmin.allowed"/>
                        <c:choose>
                        <c:when test="${tattoo.allowed}">
                            <fmt:message key="tattooAdmin.yes"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="tattooAdmin.no"/>
                    <form name="allowDeleteForm" method="post" action="controller">
                        <input type="hidden" name="tattooId" value="${tattoo.tattooId}">
                        <button type="submit" class="btn btn-outline-secondary" name="commandName"
                                value="allow_tattoo_command">
                            <fmt:message key="tattooAdmin.allow"/>
                        </button>
                        <button type="submit" class="btn btn-outline-secondary" name="commandName"
                                value="delete_tattoo_command">
                            <fmt:message key="tattooAdmin.delete"/>
                        </button>
                    </form>
                    </c:otherwise>
                    </c:choose>
                    </p>
                    <p>
                        <fmt:message key="tattooAdmin.archived"/>
                        <c:choose>
                        <c:when test="${tattoo.archived}">
                            <fmt:message key="tattooAdmin.yes"/>
                        <c:if test="${tattoo.allowed}">
                    <form name="archiveForm" method="post" action="controller">
                        <input type="hidden" name="tattooId" value="${tattoo.tattooId}">
                        <button type="submit" class="btn btn-outline-secondary" name="commandName"
                                value="unarchive_tattoo_command">
                            <fmt:message key="tattooAdmin.unarchive"/>
                        </button>
                    </form>
                    </c:if>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="tattooAdmin.no"/>
                        <c:if test="${tattoo.allowed}">
                            <form name="unArchiveForm" method="post" action="controller">
                                <input type="hidden" name="tattooId" value="${tattoo.tattooId}">
                                <button type="submit" class="btn btn-outline-secondary"
                                        name="commandName"
                                        value="archive_tattoo_command">
                                    <fmt:message key="tattooAdmin.archive"/>
                                </button>
                            </form>
                        </c:if>
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
</body>
</html>