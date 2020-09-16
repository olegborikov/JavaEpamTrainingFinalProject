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
<nav class="navbar navbar-light navbar-expand-md navbar navbar-expand-lg fixed-top" id="mainNav">
    <div class="container">
        <form name="registrationFrom" method="post" action="controller">
            <button style="color:white" class="btn navbar-brand js-scroll-trigger"
                    name="commandName" value="browse_home_page_command">Home
            </button>
        </form>
        <button data-toggle="collapse" class="navbar-toggler navbar-toggler-right"
                data-target="#navbarResponsive"
                type="button" aria-controls="navbarResponsive" aria-expanded="false"
                aria-label="Toggle navigation"
                value="Menu"><i class="fa fa-bars"></i></button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="nav navbar-nav ml-auto">
                <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                    <button style="color:white" class="btn navbar-brand js-scroll-trigger">Catalog
                    </button>
                </li>
                <c:if test="${role.equals('guest')}">
                    <form name="loginFrom" method="post" action="controller">
                        <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                            <button style="color:white" class="btn navbar-brand js-scroll-trigger"
                                    name="commandName" value="browse_login_page_command">Log in
                            </button>
                        </li>
                    </form>
                    <form name="registrationFrom" method="post" action="controller">
                        <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                            <button style="color:white" class="btn navbar-brand js-scroll-trigger"
                                    name="commandName" value="browse_registration_page_command">Sign
                                up
                            </button>
                        </li>
                    </form>
                </c:if>
                <c:if test="${role.equals('user')}">
                    <form name="loginFrom" method="post" action="controller">
                        <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                            <button style="color:white" class="btn navbar-brand js-scroll-trigger"
                                    name="commandName" value="logout_command">Log out
                            </button>
                        </li>
                    </form>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
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

<footer>
    <div class="container text-center">
        <p>Copyright © 2020 Bullfinch tattoo studio</p>
        <p>Minsk, Belarus</p>
    </div>
</footer>

</body>
</html>
