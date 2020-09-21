<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Home</title>
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
<body id="page-top">

<jsp:include page="navbar.jsp"/>

<header class="masthead"
        style="background-image:url('${pageContext.request.contextPath}/image/reception.jpg');">
    <div class="intro-body">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 mx-auto">
                    <h1 class="brand-heading stroke">Bullfinch</h1>
                    <h2 class="stroke">A free, responsive, one page Bootstrap theme.<br>Created with
                        love.</h2>
                </div>
            </div>
        </div>
    </div>
</header>
<section class="content-section text-center">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <h2>About Bullfinch</h2>
                <p> Bullfinch Studio is what you were looking for. After all, having come to us, you
                    no longer
                    want to look for a master in the future.</p>
                <p> Tattoo is perceived here as art: like an artist who works
                    with paints and canvas, the master applies the drawing to the skin.
                    Moreover, the salon team consists of people with vast experience,
                    art education and artist skills.
                    And pleasant prices and DISCOUNTS will complement the already pleasant
                    sensations from Our
                    professionals.</p>
                <p> The paints used for application are of high quality and
                    hypoallergenic,
                    and the equipment is exclusively from the latest novelties of modern brands.
                    Consumables are only disposable and sterile.
                    Therefore, experiences should be left behind the threshold, and inside - only
                    decisiveness, resilience and courage.</p>
            </div>
        </div>
    </div>
</section>
<section class="masthead content-section text-center"
         style="background-image:url('${pageContext.request.contextPath}/image/work_space.jpg');">
    <div class="container">
        <div class="col-lg-8 mx-auto">

        </div>
    </div>
</section>
<section class="content-section text-center">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <h2>Contact us</h2>
                <ul class="list-inline banner-social-buttons">
                    <li class="list-inline-item">
                        <div class="btn btn-lg btn-default">
                            <span>+375259542181</span></div>
                    </li>
                    <li class="list-inline-item">
                        <div class="btn  btn-lg btn-default">
                            <span>проспект Победителей 100</span></div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>

</body>
</html>
