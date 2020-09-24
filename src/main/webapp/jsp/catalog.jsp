<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="ru_RU"/>
<fmt:setBundle basename="i18n.application_message"/>
<html>
<head>
    <title><fmt:message key="catalog.title"/></title>
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

<body id="page-top">

<jsp:include page="navbar.jsp"/>

<section class="content-section text-center">

    <div class="row col-12">
        <div class="col-md-9"></div>
        <div class="col-12 col-md-3">
            <form name="findTattooForm" method="post" action="controller" autocomplete="off">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder=
                    <fmt:message key="catalog.search"/>
                            name="tattooName" value="${tattooName}">
                    <div class="input-group-btn">
                        <button type="submit" class="btn btn-outline-secondary" name="commandName"
                                value="find_tattoo_command"><fmt:message key="catalog.search"/>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <br/>
    <div class="container-fluid" data-aos="fade" data-aos-delay="500">
        <div class="row">
            <c:if test="${empty tattoos}">
                <section class="masthead content-section text-center">
                    <div class="container">
                        <h3><fmt:message key="catalog.empty"/></h3>
                    </div>
                </section>
            </c:if>
            <c:forEach var="tattoo" items="${tattoos}">
                <div class="col-lg-4">
                    <div class="image-wrap-2">
                        <div class="image-info">
                            <h2 class="mb-3">${tattoo.name}</h2>
                            <button class="btn btn-outline-white py-2 px-4">
                                <fmt:message key="catalog.info"/></button>
                        </div>
                        <img src="${pageContext.request.contextPath}/assets/image/${tattoo.image.name}.jpg"
                             alt="Image"
                             class="img-fluid">
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>

</body>

</html>
