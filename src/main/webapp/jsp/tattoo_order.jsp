<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="i18n.application_message"/>
<html>
<head>
    <title><fmt:message key="tattooOrder.title"/></title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/css/project.css">
</head>
<body id="page-top">
<jsp:include page="navbar.jsp"/>
<section style="text-align: left" class="masthead content-section">
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
                        <input type="hidden" id="discountId" name="discountId">
                        <div class="row form-group">
                            <div class="col-6">
                                <label class="text-white">
                                    <fmt:message key="tattooOrder.date"/>
                                </label>
                                <input type="date" style=" filter: invert(1);" name="date"
                                       required class="form-control" min="${minDate}"
                                       oninvalid="this.setCustomValidity('<fmt:message key="tattooOrder.dateValidate"/>')"
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
                                <input id="price" style="background-color: black" type="text"
                                       class="form-control text-white" name="price" readonly
                                       value="${tattoo.price}">
                            </div>
                        </div>
                        <div class="row form-group">
                            <div style=" filter: invert(1);" class="col-6">
                                <select class="form-control" size="4">
                                    <option disabled><fmt:message key="tattooOrder.availableDiscount"/></option>
                                    <option onclick='document.getElementById("price").value = ${tattoo.price};
                                            document.getElementById("discountId").value = null'>
                                        <fmt:message key="tattooOrder.zero"/>
                                    </option>
                                    <c:forEach var="discount" items="${discounts}">
                                        <option onclick='document.getElementById("price").value =
                                            ${(100 - discount.discountPercent) * tattoo.price / 100};
                                                document.getElementById("discountId").value = ${discount.discountId}'>
                                                ${discount.discountPercent}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-12">
                                <label class="text-white">
                                    <fmt:message key="tattooOrder.description"/>
                                </label>
                                <textarea style="filter: invert(1);" name="description" cols="30" rows="7"
                                          class="form-control" maxlength="250" required
                                          oninvalid="this.setCustomValidity('<fmt:message key="tattooOrder.descriptionValidate"/>')"
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
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/js/project.js"></script>
</body>
</html>
