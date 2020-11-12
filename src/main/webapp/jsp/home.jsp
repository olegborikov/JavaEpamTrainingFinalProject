<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="i18n.application_message"/>

<html>
<head>
    <title><fmt:message key="home.title"/></title>
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
<jsp:include page="${pageContext.request.contextPath}/jsp/fragment/navbar.jsp"/>
<header style="background-image:url('${pageContext.request.contextPath}/assets/image/reception.jpg')" class="masthead">
    <div class="intro-body">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 mx-auto">
                    <h1 class="brand-heading stroke">
                        <fmt:message key="home.name"/>
                    </h1>
                    <h2 class="stroke"><fmt:message key="home.slogan"/></h2>
                </div>
            </div>
        </div>
    </div>
</header>
<section class="content-section text-center">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <h2><fmt:message key="home.about"/></h2>
                <p><fmt:message key="home.about.part1"/></p>
                <p><fmt:message key="home.about.part2"/></p>
                <p><fmt:message key="home.about.part3"/></p>
            </div>
        </div>
    </div>
</section>
<section style="background-image:url('${pageContext.request.contextPath}/assets/image/work_space.jpg');"
         class="masthead content-section text-center">
    <div class="container">
        <div class="col-lg-8 mx-auto"></div>
    </div>
</section>
<section class="content-section text-center">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <h2><fmt:message key="home.contact"/></h2>
                <ul class="list-inline banner-social-buttons">
                    <li class="list-inline-item">
                            <span class="btn btn-outline-secondary">
                                <fmt:message key="home.phoneNumber"/>
                            </span>
                    </li>
                    <li class="list-inline-item">
                          <span class="btn btn-outline-secondary">
                              <fmt:message key="home.address"/>
                          </span>
                    </li>
                </ul>
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
