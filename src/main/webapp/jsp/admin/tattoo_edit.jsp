<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="i18n.application_message"/>
<html>
<head>
    <title><fmt:message key="tattooEdit.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/css/project.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/fragment/navbar.jsp"/>
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
                    <form action="/bullfinch" method="post" autocomplete="off">
                        <input type="hidden" name="commandName" value="edit_tattoo_command">
                        <input type="hidden" name="tattooId" value="${tattoo.tattooId}">
                        <div class="row form-group">
                            <div class="col-6">
                                <label class="text-white">
                                    <fmt:message key="tattooEdit.name"/>
                                </label>
                                <input style="background-color: black" name="name" required
                                       maxlength="25"
                                       type="text" class="form-control text-white"
                                       oninvalid="this.setCustomValidity('<fmt:message key="tattooEdit.nameValidate"/>')"
                                       onchange="this.setCustomValidity('')"
                                       pattern="^[^<>]{2,25}$"
                                       title='<fmt:message key="tattooEdit.nameValidate"/>'
                                       placeholder='<fmt:message key="tattooEdit.name"/>'
                                       value="<c:out value="${tattoo.name}"/>">
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-6">
                                <label class="text-white">
                                    <fmt:message key="tattooEdit.price"/>
                                </label>
                                <input style="background-color: black" name="price" required
                                       maxlength="10"
                                       type="text" class="form-control text-white"
                                       oninvalid="this.setCustomValidity('<fmt:message key="tattooEdit.priceValidate"/>')"
                                       onchange="this.setCustomValidity('')"
                                       pattern="^[1-9]\d{0,4}(\.\d{0,2})?$"
                                       title='<fmt:message key="tattooEdit.priceValidate"/>'
                                       placeholder='<fmt:message key="tattooEdit.price"/>'
                                       value="${tattoo.price}">
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-12">
                                <label class="text-white">
                                    <fmt:message key="tattooEdit.description"/>
                                </label>
                                <textarea style="background-color: black" cols="30" rows="7" required
                                          class="form-control text-white" maxlength="1000" name="description"
                                          oninvalid="this.setCustomValidity('<fmt:message key="tattooEdit.descriptionValidate"/>')"
                                          onchange="this.setCustomValidity('')"
                                          title='<fmt:message key="tattooEdit.descriptionValidate"/>'
                                          placeholder='<fmt:message key="tattooEdit.description"/>'>${tattoo.description}</textarea>
                            </div>
                        </div>
                        <c:if test="${incorrectDataMessage}">
                            <div style="color: red">
                                <fmt:message key="tattooEdit.incorrectDataMessage"/>
                            </div>
                        </c:if>
                        <button type="submit" class="btn btn-outline-secondary">
                            <fmt:message key="tattooEdit.edit"/>
                        </button>
                    </form>
                </div>
                <div class="col-md-1">
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="${pageContext.request.contextPath}/jsp/fragment/footer.jsp"/>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/js/project.js"></script>
</body>
</html>
