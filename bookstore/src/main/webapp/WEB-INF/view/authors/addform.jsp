<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Autorzy</title>
    <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />' />
    <link href='<c:url value="/resources/css/styles.css" />' rel="stylesheet" />
</head>
<body>
<div class="container">
    <h3>Dodaj autora</h3>
<form:form action="form" modelAttribute="autor" method="POST">
    <div class="form-group">
        <label>Imie:</label>
        <form:input path="imie" class="form-control"/>
    </div>
    <div class="form-group">
        <label>Nazwisko:</label>
        <form:input path="nazwisko" class="form-control"/>
    </div>

    <button type="submit" class="btn btn-primary" value="Save" class="save">Zapisz</button>
    <form:hidden path="id"/>
</form:form>
<%--/<!-- -wracamy do listy bez zapisu->--%>
    <a class="btn btn-danger" href="${pageContext.request.contextPath}/authors/ "> Powróć </a>
</div>
</body>
</html>