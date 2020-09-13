<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Home</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/main.js"></script>
</head>

<body id="page-top">
<header class="navbar navbar-light navbar-expand-md navbar navbar-expand-lg fixed-top" id="mainNav">
    <div class="container">
        <button style="color:white" class="btn navbar-brand js-scroll-trigger">Home</button>
        <button data-toggle="collapse" class="navbar-toggler navbar-toggler-right" data-target="#navbarResponsive"
                type="button" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"
                value="Menu"><i class="fa fa-bars"></i></button>
        <div class="collapse navbar-collapse" id="navbarResponsive">

            <ul class="nav navbar-nav ml-auto">
                <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                    <button style="color:white" class="btn navbar-brand js-scroll-trigger">Catalog</button>
                </li>
                <form name="loginFrom" method="post" action="controller">
                    <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                        <button style="color:white" class="btn navbar-brand js-scroll-trigger"
                                name="commandName" value="browse_login_command">Log in
                        </button>
                    </li>
                </form>
                <form name="registrationFrom" method="post" action="controller">
                    <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                        <button style="color:white" class="btn navbar-brand js-scroll-trigger"
                                name="commandName" value="browse_registration_command">Sign up
                        </button>
                    </li>
                </form>
            </ul>
        </div>
    </div>
</header>
<article class="masthead" style="background-image:url('assets/image/reception.jpg');">
    <div class="intro-body">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 mx-auto">
                    <h1 class="brand-heading">Bullfinch</h1>
                    <p class="intro-text">A free, responsive, one page Bootstrap theme.<br>Created with love.</p>
                </div>
            </div>
        </div>
    </div>
</article>
<section class="content-section text-center">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <h2>About Bullfinch</h2>
                <p>Grayscale is a free Bootstrap theme. It can be yours right now, simply download the template on&nbsp;<a
                        href="#">the preview page</a>. The theme is open source, and you can use it for any purpose,
                    personal or commercial.</p>
                <p>This theme features stock photos by&nbsp;<a href="#">Gratisography</a>&nbsp;along with a custom
                    Google Maps skin courtesy of&nbsp;<a href="#">Snazzy Maps</a>.</p>
                <p>Grayscale includes full HTML, CSS, and custom JavaScript files along with SASS and LESS files for
                    easy customization!</p>
            </div>
        </div>
    </div>
</section>
<section class="masthead content-section text-center" style="background-image:url('assets/image/work_space.jpg');">
    <div class="container">
        <div class="col-lg-8 mx-auto">
            <h1>Download Grayscale</h1>
        </div>
    </div>
</section>
<section class="content-section text-center">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 mx-auto">
                <h2>Contact us</h2>
                <p>Feel free to leave us a comment on the<a href="#">&nbsp;Grayscale template overview page</a>&nbsp;to
                    give some feedback about this theme!</p>
                <ul class="list-inline banner-social-buttons">
                    <li class="list-inline-item">&nbsp;<button class="btn btn-primary btn-lg btn-default" type="button">
                        <i class="fa fa-google-plus fa-fw"></i><span class="network-name">&nbsp; Google+</span></button>
                    </li>
                    <li class="list-inline-item">&nbsp;<button class="btn btn-primary btn-lg btn-default" type="button">
                        <i class="fa fa-twitter fa-fw"></i><span class="network-name">&nbsp;Twitter</span></button>
                    </li>
                    <li class="list-inline-item">&nbsp;<button class="btn btn-primary btn-lg btn-default" type="button">
                        <i class="fa fa-github fa-fw"></i><span class="network-name">&nbsp;github</span></button>
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
