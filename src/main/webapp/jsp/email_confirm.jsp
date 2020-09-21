<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Email confirm</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/fonts/font-awesome.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/project.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/project.js"></script>
</head>
<body>

<jsp:include page="navbar.jsp"/>

<section class="masthead"
         style="background-image:url('${pageContext.request.contextPath}/image/reception.jpg');">
    <div class="intro-body">
        <div class="container">
            <h2> ${confirmEmailMessage}</h2>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
