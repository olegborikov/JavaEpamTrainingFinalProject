<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="i18n.application_message"/>
<html>
<head>
    <title><fmt:message key="tattooOrder.title"/></title>
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
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-5">
                    <img style="max-width: 80%;max-height: 80%"
                         src="/images/${tattoo.image.name}.jpg"
                         alt="Responsive image" class="img-fluid">
                </div>
                <div class="col-md-4">
                    <br/>
                    <form action="controller" method="post" autocomplete="off">
                        <input type="hidden" name="commandName" value="order_tattoo_command">
                        <input type="hidden" name="tattooId" value="${tattoo.tattooId}">
                        <div class="row form-group">
                            <div class="col-6">
                                <label class="text-white">
                                    <fmt:message key="tattooOrder.date"/>
                                </label>
                                <input type="date" style=" filter: invert(1);" name="date"
                                       required class="form-control"
                                       oninvalid="this.setCustomValidity('<fmt:message
                                               key="tattooOrder.dateValidate"/>')"
                                       onchange="this.setCustomValidity('')"
                                       title='<fmt:message key="tattooOrder.dateValidate"/>'
                                       placeholder='<fmt:message key="tattooOrder.date"/>'>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-6">
                                <label class="text-white">
                                    <fmt:message key="tattooOrder.price"/>
                                </label>
                                <input style="background-color: black" name="price"
                                       readonly type="text" class="form-control text-white"
                                       title='<fmt:message key="tattooOrder.price"/>'
                                       placeholder='<fmt:message key="tattooOrder.price"/>'
                                       value="${tattoo.price}">
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-12">
                                <label class="text-white">
                                    <fmt:message key="tattooOrder.description"/>
                                </label>
                                <textarea style="background-color: black"
                                          name="description" cols="30" rows="7" required
                                          class="form-control text-white" maxlength="250"
                                          oninvalid="this.setCustomValidity('<fmt:message
                                                  key="tattooOrder.descriptionValidate"/>')"
                                          onchange="this.setCustomValidity('')"
                                          title='<fmt:message key="tattooOrder.descriptionValidate"/>'
                                          placeholder='<fmt:message key="tattooOrder.description"/>'></textarea>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-outline-secondary">
                            <fmt:message key="tattooOrder.order"/>
                        </button>
                    </form>
                </div>
                <div class="col-md-1">
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
