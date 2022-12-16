<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Adres</title>
    <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />' />
    <link href='<c:url value="/resources/css/styles.css" />' rel="stylesheet" />
</head>
<body>
<div class="container">
    <h3>Dodaj adres</h3>
<form:form action="address" modelAttribute="address" method="POST">
    <div class="form-group">
        <label>Miejscowość:</label>
        <form:input path="shippingAddressCity" class="form-control"/>
    </div>
    <div class="form-group">
        <label>Ulica:</label>
        <form:input path="shippingAddressStreet" class="form-control"/>
    </div>
    <div class="form-group">
        <label>Województwo:</label>
        <form:input path="shippingAddressState" class="form-control"/>
    </div>
    <div class="form-group">
        <label>Kod pocztowy:</label>
        <form:input path="shippingAddressZipcode" class="form-control"/>
    </div>

    <button type="submit" class="btn btn-primary" value="Save" class="save">Zapisz</button>
    <form:hidden path="id"/>
</form:form>
<%--/<!-- -wracamy do listy bez zapisu->--%>
    <a class="btn btn-danger" href="${pageContext.request.contextPath}/shoppingcart/cart "> Powróć </a>
</div>
</body>
</html>