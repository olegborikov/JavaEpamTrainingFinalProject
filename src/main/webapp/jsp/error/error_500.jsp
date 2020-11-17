<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Error 500</title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/css/project.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/fragment/navbar.jsp"/>
<section class="masthead">
    <div class="intro-body">
        <c:if test="${not empty errorMessage}">
            <div class="container">
                <h5>Request is failed</h5>
                <br/>
                <h5>Status code: 500</h5>
                <br/>
                <h5>Error: ${errorMessage.getClass().getCanonicalName()}</h5>
                <br/>
            </div>
        </c:if>
        <c:if test="${empty errorMessage}">
            <div class="container">
                <h5>Request from ${pageContext.errorData.requestURI} is failed</h5>
                <br/>
                <h5>Status code: 500</h5>
                <br/>
                <h5>Error: ${pageContext.exception}</h5>
                <br/>
            </div>
        </c:if>
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
