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
                    <h1 class="brand-heading">Bullfinch</h1>
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
                <p>Студия «Bullfinch» - это то, что Вы искали. Ведь придя к нам, Вам больше не
                    захочется искать себе мастера в дальнейшем. </p>
                <p>Татуировку здесь воспринимают как искусство: подобно художнику, который работает
                    с красками и холстом, мастер наносит рисунок на кожу.
                    Более того, команда салона состоит из людей, имеющих огромный опыт работы,
                    художественное образование и навыки художника.
                    А приятные цены и СКИДКИ дополнят и так приятные ощущения от Наших
                    профессионалов.</p>
                <p>Краски, используемые при нанесении являются высококачественными и
                    гипоаллергенными,
                    а оборудование исключительно из последних новинок современных брендов.
                    Расходный материал только одноразовый и стерильный.
                    Поэтому, переживания следует оставить за порогом, а внутри – только
                    решительность, стойкость и смелость.</p>
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
                    <li class="list-inline-item">&nbsp;<div class="btn btn-lg btn-default">
                        <span>+375259542181</span></div>
                    </li>
                    <li class="list-inline-item">&nbsp;<div class="btn  btn-lg btn-default">
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
