<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="i18n.application_message"/>
<html>
<head>
    <title><fmt:message key="tattooAdd.title"/></title>
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
                    <form action="/upload" enctype="multipart/form-data" method="post"
                          autocomplete="off">
                        <div class="row form-group">
                            <div class="col-md-6 mb-3 mb-md-0">
                                <label class="text-white">
                                    <fmt:message key="tattooAdd.name"/>
                                </label>
                                <input style="background-color: black" name="name" required
                                       maxlength="25"
                                       type="text" class="form-control text-white"
                                       oninvalid="this.setCustomValidity('<fmt:message
                                               key="tattooAdd.nameValidate"/>')"
                                       onchange="this.setCustomValidity('')"
                                       pattern="^[\p{L}]{2,25}$"
                                       title='<fmt:message key="tattooAdd.nameValidate"/>'
                                       placeholder='<fmt:message key="tattooAdd.name"/>'>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-6 mb-3 mb-md-0">
                                <label class="text-white">
                                    <fmt:message key="tattooAdd.price"/>
                                </label>
                                <input style="background-color: black" name="price" required
                                       maxlength="10"
                                       type="text" class="form-control text-white"
                                       oninvalid="this.setCustomValidity('<fmt:message
                                               key="tattooAdd.priceValidate"/>')"
                                       onchange="this.setCustomValidity('')"
                                       title='<fmt:message key="tattooAdd.priceValidate"/>'
                                       placeholder='<fmt:message key="tattooAdd.price"/>'>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-12">
                                <fmt:message key="tattooAdd.fileUpload"/>
                                <input type="file" name="content" accept="image/jpeg" required>
                                <c:if test="${incorrectImageMessage}">
                                    <div style="color: red"><fmt:message
                                            key="tattooAdd.incorrectImageMessage"/></div>
                                </c:if>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-12">
                                <label class="text-white">
                                    <fmt:message key="tattooAdd.description"/>
                                </label>
                                <textarea style="background-color: black"
                                          name="description" cols="30" rows="7" required
                                          class="form-control text-white" maxlength="1000"
                                          oninvalid="this.setCustomValidity('<fmt:message
                                                  key="tattooAdd.descriptionValidate"/>')"
                                          onchange="this.setCustomValidity('')"
                                          title='<fmt:message key="tattooAdd.descriptionValidate"/>'
                                          placeholder='<fmt:message key="tattooAdd.description"/>'></textarea>
                            </div>
                        </div>
                        <c:if test="${incorrectDataMessage}">
                            <div style="color: red"><fmt:message
                                    key="tattooAdd.incorrectDataMessage"/></div>
                        </c:if>
                        <input type="hidden" name="commandName" value="add_tattoo_command">
                        <button type="submit" class="btn btn-outline-secondary">
                            <fmt:message key="tattooAdd.addTattoo"/>
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
