<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Catalog</title>

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
    <div class="container-fluid" data-aos="fade" data-aos-delay="500">
        <div class="row">
            <c:forEach var="tattoo" items="${tattoos}">
                <div class="col-lg-4">
                    <div class="image-wrap-2">
                        <div class="image-info">
                            <h2 class="mb-3">${tattoo.name}</h2>
                            <button class="btn btn-outline-white py-2 px-4">More info</button>
                        </div>
                        <img src="${pageContext.request.contextPath}/assets/image/${tattoo.imageName}.jpg"
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
