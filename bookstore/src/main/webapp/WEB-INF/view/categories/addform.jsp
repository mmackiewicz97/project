<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Kategorie</title>
    <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />' />
    <link href='<c:url value="/resources/css/styles.css" />' rel="stylesheet" />
</head>
<body>
<div class="container">
    <h3>Dodaj kategorię</h3>
<form:form action="form" modelAttribute="category" method="POST">
    <div class="form-group">
        <label>Nazwa:</label>
        <form:input path="nazwa" class="form-control"/>
    </div>
    <button type="submit" class="btn btn-primary" value="Save" class="save">Zapisz</button>
    <form:hidden path="id"/>
    </form:form>
    <a class="btn btn-danger" href="${pageContext.request.contextPath}/categories/ "> Powróć </a>
</div>
</body>
</html>