<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <nav class="navbar navbar-light navbar-expand-md navbar navbar-expand-lg fixed-top"
         id="mainNav">
        <div class="container">
            <form name="homeButtonFrom" method="post" action="controller">
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
                        <button style="color:white" class="btn navbar-brand js-scroll-trigger"
                                name="commandName" value="browse_login_page_command">Catalog
                        </button>
                    </li>
                    <c:if test="${role.equals('guest')}">
                        <form name="loginButtonFrom" method="post" action="controller">
                            <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                                <button style="color:white"
                                        class="btn navbar-brand js-scroll-trigger"
                                        name="commandName" value="browse_login_page_command">Log in
                                </button>
                            </li>
                        </form>
                        <form name="registrationFrom" method="post" action="controller">
                            <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                                <button style="color:white"
                                        class="btn navbar-brand js-scroll-trigger"
                                        name="commandName" value="browse_registration_page_command">
                                    Sign
                                    up
                                </button>
                            </li>
                        </form>
                    </c:if>
                    <c:if test="${role.equals('user')}">
                        <form name="loginNameFrom" method="post" action="controller">
                            <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                                <button style="color:white"
                                        class="btn navbar-brand js-scroll-trigger"
                                        name="commandName"
                                        value="browse_profile_page_command">${login}
                                </button>
                            </li>
                        </form>
                        <form name="logoutButtonFrom" method="post" action="controller">
                            <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                                <button style="color:white"
                                        class="btn navbar-brand js-scroll-trigger"
                                        name="commandName" value="logout_command">Log out
                                </button>
                            </li>
                        </form>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
</div>
