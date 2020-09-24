<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="ru_RU"/>
<fmt:setBundle basename="i18n.application_message"/>

<div>
    <footer>
        <div class="container text-center">
            <p><fmt:message key="footer.copyright"/></p>
            <p><fmt:message key="footer.location"/></p>
        </div>
    </footer>
</div>
