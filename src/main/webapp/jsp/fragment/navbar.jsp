<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="i18n.application_message"/>

<div>
    <nav style="background-color: black; padding-bottom: 5px; border-bottom-color: white"
         class="navbar navbar-light navbar-expand-md navbar navbar-expand-lg fixed-top"
         id="mainNav">
        <div class="container">
            <form method="post" action="/bullfinch">
                <button style="color:white" class="btn navbar-brand js-scroll-trigger"
                        name="commandName" value="browse_home_page_command">
                    <fmt:message key="navbar.home"/>
                </button>
            </form>
            <form method="post" action="/bullfinch">
                <input type="hidden" name="commandName" value="switch_locale_command">
                <button style="color:white" class="btn navbar-brand js-scroll-trigger"
                        name="newLocale" value=<fmt:message key="navbar.switchLanguage"/>>
                    <fmt:message key="navbar.language"/>
                </button>
            </form>
            <button data-toggle="collapse" class="navbar-toggler navbar-toggler-right"
                    data-target="#navbarResponsive"
                    type="button" aria-controls="navbarResponsive" aria-expanded="false"
                    aria-label="Toggle navigation"
                    value="Menu"><i class="fa fa-bars"></i></button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="nav navbar-nav ml-auto">
                    <c:if test="${!role.equals('admin')}">
                        <form method="post" action="/bullfinch">
                            <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                                <button style="color:white"
                                        class="btn navbar-brand js-scroll-trigger"
                                        name="commandName" value="browse_catalog_page_command">
                                    <fmt:message key="navbar.catalog"/>
                                </button>
                            </li>
                        </form>
                    </c:if>
                    <c:if test="${role.equals('guest')}">
                        <form method="post" action="/bullfinch">
                            <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                                <button style="color:white"
                                        class="btn navbar-brand js-scroll-trigger"
                                        name="commandName" value="browse_login_page_command">
                                    <fmt:message key="navbar.logIn"/>
                                </button>
                            </li>
                        </form>
                        <form method="post" action="/bullfinch">
                            <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                                <button style="color:white"
                                        class="btn navbar-brand js-scroll-trigger"
                                        name="commandName" value="browse_registration_page_command">
                                    <fmt:message key="navbar.signUp"/>
                                </button>
                            </li>
                        </form>
                    </c:if>
                    <c:if test="${role.equals('user')}">
                        <form method="post" action="/bullfinch">
                            <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                                <button style="color:white"
                                        class="btn navbar-brand js-scroll-trigger"
                                        name="commandName"
                                        value="browse_profile_page_command">
                                        ${login}
                                </button>
                            </li>
                        </form>
                    </c:if>
                    <c:if test="${role.equals('admin')}">
                        <form method="post" action="/bullfinch">
                            <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                                <div class="dropdown">
                                    <button style="color:white"
                                            class="btn navbar-brand js-scroll-trigger dropdown-toggle"
                                            id="dropdownMenuButton" data-toggle="dropdown"
                                            aria-haspopup="true" aria-expanded="false"
                                            name="commandName"
                                            value="browse_tattoos_page_command">
                                        <fmt:message key="navbar.tattoos"/>
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                        <button class="dropdown-item" name="commandName"
                                                value="browse_all_tattoos_admin_page_command">
                                            <fmt:message key="navbar.all"/>
                                        </button>
                                        <button class="dropdown-item" name="commandName"
                                                value="browse_catalog_tattoos_admin_page_command">
                                            <fmt:message key="navbar.catalog"/>
                                        </button>
                                        <button class="dropdown-item" name="commandName"
                                                value="browse_offered_tattoos_admin_page_command">
                                            <fmt:message key="navbar.offered"/>
                                        </button>
                                        <button class="dropdown-item" name="commandName"
                                                value="browse_archived_tattoos_admin_page_command">
                                            <fmt:message key="navbar.archived"/>
                                        </button>
                                    </div>
                                </div>
                            </li>
                        </form>
                        <form method="post" action="/bullfinch">
                            <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                                <button style="color:white"
                                        class="btn navbar-brand js-scroll-trigger"
                                        name="commandName"
                                        value="browse_orders_admin_page_command">
                                    <fmt:message key="navbar.orders"/>
                                </button>
                            </li>
                        </form>
                        <form method="post" action="/bullfinch">
                            <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                                <button style="color:white"
                                        class="btn navbar-brand js-scroll-trigger"
                                        name="commandName"
                                        value="browse_users_admin_page_command">
                                    <fmt:message key="navbar.users"/>
                                </button>
                            </li>
                        </form>
                    </c:if>
                    <c:if test="${!role.equals('guest')}">
                        <form method="post" action="/bullfinch">
                            <li class="nav-item nav-link js-scroll-trigger" role="presentation">
                                <button style="color:white"
                                        class="btn navbar-brand js-scroll-trigger"
                                        name="commandName" value="logout_command">
                                    <fmt:message key="navbar.logOut"/>
                                </button>
                            </li>
                        </form>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
</div>
