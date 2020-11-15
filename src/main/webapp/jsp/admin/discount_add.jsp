<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="i18n.application_message"/>
<html>
<head>
    <title><fmt:message key="discountAdd.title"/></title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/css/project.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/fragment/navbar.jsp"/>
<section class="masthead">
    <div class="intro-body">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 mx-auto">
                    <form method="post" action="/bullfinch" autocomplete="off">
                        <div class="form-group">
                            <input type="hidden" name="userId" value="${userId}">
                            <input style="background-color: black" type="text"
                                   class="form-control text-white" name="percent"
                                   maxlength="2" required
                                   oninvalid="this.setCustomValidity('<fmt:message key="discountAdd.percentValidate"/>')"
                                   onchange="this.setCustomValidity('')"
                                   pattern="^[1-9]\d?$"
                                   title='<fmt:message key="discountAdd.percentValidate"/>'
                                   placeholder='<fmt:message key="discountAdd.percent"/>'>
                        </div>
                        <c:if test="${incorrectDataMessage}">
                            <div style="color: red">
                                <fmt:message key="discountAdd.incorrectDataMessage"/>
                            </div>
                        </c:if>
                        <button class="btn btn-outline-secondary" name="commandName"
                                value="add_discount_command">
                            <fmt:message key="discountAdd.add"/>
                        </button>
                    </form>
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
