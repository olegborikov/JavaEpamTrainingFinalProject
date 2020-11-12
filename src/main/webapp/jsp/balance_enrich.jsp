<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="i18n.application_message"/>
<html>
<head>
    <title><fmt:message key="balanceEnrich.title"/></title>
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
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-8">
                    <div style="color: black" class="padding ">
                        <div class="row">
                            <div class="col-sm-6">
                                <form method="post" action="/bullfinch" autocomplete="off">
                                    <div class="card">
                                        <div class="card-header">
                                            <strong><fmt:message key="balanceEnrich.creditCard"/></strong>
                                            <small><fmt:message key="balanceEnrich.creditCardInfo"/></small>
                                        </div>
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <div class="form-group">
                                                        <label><fmt:message key="balanceEnrich.cardNumber"/></label>
                                                        <div class="input-group" >
                                                            <input style="color: black" class="form-control" type="text" maxlength="19"
                                                                   placeholder="0000 0000 0000 0000" required
                                                                   oninvalid="this.setCustomValidity('<fmt:message key="balanceEnrich.cardNumberValidate"/>')"
                                                                   onchange="this.setCustomValidity('')"
                                                                   pattern="^\d{4}\s\d{4}\s\d{4}\s\d{4}$"
                                                                   title='<fmt:message key="balanceEnrich.cardNumberValidate"/>'>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-6">
                                                    <div class="form-group">
                                                        <label><fmt:message key="balanceEnrich.name"/></label>
                                                        <input style="color: black" class="form-control" required
                                                               type="text" maxlength="20"
                                                               placeholder='<fmt:message key="balanceEnrich.enterName"/>'
                                                               oninvalid="this.setCustomValidity('<fmt:message key="balanceEnrich.nameValidate"/>')"
                                                               onchange="this.setCustomValidity('')"
                                                               pattern="^[A-Za-z]{2,20}$"
                                                               title='<fmt:message key="balanceEnrich.nameValidate"/>'>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <div class="form-group">
                                                        <label><fmt:message key="balanceEnrich.surname"/></label>
                                                        <input style="color: black" class="form-control" required
                                                               type="text" maxlength="20"
                                                               placeholder='<fmt:message key="balanceEnrich.enterSurname"/>'
                                                               oninvalid="this.setCustomValidity('<fmt:message key="balanceEnrich.surnameValidate"/>')"
                                                               onchange="this.setCustomValidity('')"
                                                               pattern="^[A-Za-z]{2,20}$"
                                                               title='<fmt:message key="balanceEnrich.surnameValidate"/>'>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-sm-6">
                                                    <label><fmt:message key="balanceEnrich.month"/></label>
                                                    <select class="form-control" style="color: black">
                                                        <option>1</option>
                                                        <option>2</option>
                                                        <option>3</option>
                                                        <option>4</option>
                                                        <option>5</option>
                                                        <option>6</option>
                                                        <option>7</option>
                                                        <option>8</option>
                                                        <option>9</option>
                                                        <option>10</option>
                                                        <option>11</option>
                                                        <option>12</option>
                                                    </select>
                                                </div>
                                                <div class="form-group col-sm-6">
                                                    <label><fmt:message key="balanceEnrich.year"/></label>
                                                    <select class="form-control" style="color: black">
                                                        <option>2016</option>
                                                        <option>2017</option>
                                                        <option>2018</option>
                                                        <option>2019</option>
                                                        <option>2020</option>
                                                        <option>2021</option>
                                                        <option>2022</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-6">
                                                    <div class="form-group">
                                                        <label><fmt:message
                                                                key="balanceEnrich.cvc"/></label>
                                                        <input style="color: black" class="form-control"
                                                               type="text" required
                                                               placeholder="123" maxlength="3"
                                                               oninvalid="this.setCustomValidity('<fmt:message key="balanceEnrich.cvcValidate"/>');"
                                                               onchange="this.setCustomValidity('')"
                                                               pattern="^\d{3}$"
                                                               title='<fmt:message key="balanceEnrich.cvcValidate"/>'>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <div class="form-group">
                                                        <label><fmt:message key="balanceEnrich.amount"/></label>
                                                        <input style="color: black" class="form-control" required
                                                               type="text" maxlength="6" name="enrichAmount"
                                                               placeholder='<fmt:message key="balanceEnrich.enterAmount"/>'
                                                               oninvalid="this.setCustomValidity('<fmt:message key="balanceEnrich.amountValidate"/>')"
                                                               onchange="this.setCustomValidity('')"
                                                               pattern="^[1-9]\d{0,2}(\.\d{0,2})?$"
                                                               title='<fmt:message key="balanceEnrich.amountValidate"/>'>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <input type="hidden" name="walletId" value="${walletId}">
                                            <button class="btn btn-sm btn-success float-right"
                                                    name="commandName" value="enrich_balance_command">
                                                <fmt:message key="balanceEnrich.confirm"/>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
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

