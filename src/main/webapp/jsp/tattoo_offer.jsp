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
                    <form action="/upload" enctype="multipart/form-data" method="post" autocomplete="off">
                        <input type="hidden" name="commandName" value="offer_tattoo_command">
                        <div class="row form-group">
                            <div class="col-md-6 mb-3 mb-md-0">
                                <label class="text-white">
                                    <fmt:message key="tattooOffer.name"/>
                                </label>
                                <input style="background-color: black" name="name" required maxlength="25"
                                       type="text" class="form-control text-white"
                                       oninvalid="this.setCustomValidity('<fmt:message
                                               key="tattooOffer.nameValidate"/>')"
                                       onchange="this.setCustomValidity('')"
                                       pattern="^[\p{L}]{2,25}"
                                       title='<fmt:message key="tattooOffer.nameValidate"/>'
                                       placeholder='<fmt:message key="tattooOffer.name"/>'>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-12">
                                Upload File: <input type="file" name="content" accept="image/jpeg" required>
                                <c:if test="${errorImageMessage}">
                                    <div style="color: red"><fmt:message key="emailOfferConfirm.errorImageMessage"/></div>
                                </c:if>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-12">
                                <label class="text-white">
                                    <fmt:message key="tattooOffer.description"/>
                                </label>
                                <textarea style="background-color: black"
                                          name="description" cols="30" rows="7" required
                                          class="form-control text-white" maxlength="1000"
                                          oninvalid="this.setCustomValidity('<fmt:message
                                                  key="tattooOffer.descriptionValidate"/>')"
                                          onchange="this.setCustomValidity('')"
                                          title='<fmt:message key="tattooOffer.descriptionValidate"/>'
                                          placeholder='<fmt:message key="tattooOffer.description"/>'></textarea>
                            </div>
                        </div>
                        <div style="color: red"> ${errorDataMessage}</div>
                        <button type="submit" class="btn btn-outline-secondary">
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
