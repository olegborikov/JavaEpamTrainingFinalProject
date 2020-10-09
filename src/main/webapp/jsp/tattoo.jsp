<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="i18n.application_message"/>
<html>

<head>
    <title><fmt:message key="tattoo.title"/></title>
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
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-5">
                    <img style="max-width: 80%;max-height: 80%"
                         src="${pageContext.request.contextPath}/assets/image/${tattoo.image.name}.jpg"
                         alt="Responsive image" class="img-fluid">
                </div>
                <div class="col-md-4">
                    <br/>
                    <h3 class="text-white"><fmt:message key="tattoo.information"/></h3>
                    <p><fmt:message key="tattoo.name"/> ${tattoo.name}</p>
                    <p><fmt:message key="tattoo.description"/> ${tattoo.description}</p>
                    <p><fmt:message key="tattoo.price"/> ${tattoo.price} <fmt:message key="tattoo.rubles"/></p>
                    <p><fmt:message key="tattoo.rating"/> ${tattoo.rating}</p>
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
