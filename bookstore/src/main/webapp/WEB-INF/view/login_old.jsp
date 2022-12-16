<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Logowanie</title>
</head><body>
<h3>Logowanie</h3>
<c:if test="${(param.error != null) && (not empty SPRING_SECURITY_LAST_EXCEPTION)}">
    <p><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></p>
</c:if>
<form:form method="post">
    <table>
        <tbody>
        <tr>
            <td><label for="username">Nazwa:</label></td>
            <td><input type="text" id="username" name="username" class="form-
control"/></td>
        </tr>
        <tr>
            <td><label for="password">Hasło:</label></td>
            <td><input type="password" id="password" name="password"
                       class="form-control"/></td>
        </tr>
        <tr>
            <td>
                <button type="submit" >Zaloguj</button>
            </td>
        </tr>
        </tbody>
    </table>
</form:form>
</body>
</html>
