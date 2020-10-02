<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="i18n.application_message"/>
<html>
<head>
    <title><fmt:message key="tattooOffer.title"/></title>
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
<section style="text-align: left;padding-top: 120px" class="masthead">
    <div class="intro-body">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 mb-5">
                    <form action="#">
                        <div class="row form-group">
                            <div class="col-md-6 mb-3 mb-md-0">
                                <label class="text-white">
                                    <fmt:message key="tattooOffer.name"/>
                                </label>
                                <input style="background-color: black"
                                       type="text" class="form-control text-white"
                                       placeholder=<fmt:message key="tattooOffer.name"/>>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-12">
                                <label class="text-white">Subject</label>
                                <input style="background-color: black" type="text"
                                       class="form-control text-white">
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-12">
                                <label class="text-white">
                                    <fmt:message key="tattooOffer.description"/>
                                </label>
                                <textarea style="background-color: black"
                                          name="message" cols="30" rows="7"
                                          class="form-control text-white"
                                          placeholder=<fmt:message key="tattooOffer.description"/>>
                                </textarea>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-outline-secondary" name="commandName"
                                value="browse_registration_page_command">
                            <fmt:message key="tattooOffer.offerTattoo"/>
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
