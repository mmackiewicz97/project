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
    <title>Logowanie</title>
</head>
<body>
<div class="container">
<h3>Log in</h3>
<c:if test="${(param.error != null) && (not empty SPRING_SECURITY_LAST_EXCEPTION)}">
<div class="alert alert-danger" role="alert">
    <p><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></p>
</div>
</c:if>
<form:form method="post">
    <div class="form-group">
        <label for="username">Username: </label>
        <input required="required" type="text" class="form-control" id="username" name="username" class="form-control"/>
        <p style="color: #828282">Enter your username here.</p>
    </div>

    <div class="form-group">
        <label for="password">Password: </label>
        <input required="required" type="password" class="form-control" id="password" name="password" />
        <p style="color: #828282">Enter the password that accompanies your username</p>
    </div>
    <button type="submit" class="btn btn-primary">Login</button>
</form:form>
</div>
</body>
</html>
