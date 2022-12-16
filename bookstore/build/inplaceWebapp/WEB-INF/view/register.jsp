<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />' />

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" />
    <title>Rejestracja</title>
</head>
<body>
<div class="container">
<h3>Registration</h3>

<c:if test="${not empty validator}">
    <div class="alert alert-danger" role="alert">
    <p><c:out value="${validator}"/></p>
</div>
</c:if>

<form:form method="post" modelAttribute="user">
<div class="form-group">
    <label for="username">* Username: </label>
    <form:input path="username" id="username" required="required" type="text" class="form-control"/>
    <p style="color: #828282">Enter your username here.</p>
</div>

<div class="form-group">
    <label for="email">* Email Address: </label>
    <form:input path="email" id="email" required="required" type="text" class="form-control"/>
    <p style="color: #828282">A valid email address. All
        emails from the system withll be sent to this address. The
        email address is not made public and will only be used if
        you wish to receive a new password or wish to receive
        certain notification.</p>
</div>
    <div class="form-group">
        <label for="password">* Password: </label>
        <form:input path="password" id="password" required="required" type="password" class="form-control"/>
        <p style="color: #828282">Enter your username here.</p>
    </div>

<button type="submit" class="btn btn-primary">Create new account</button>
</form:form>
</div>
</body>
</html>
