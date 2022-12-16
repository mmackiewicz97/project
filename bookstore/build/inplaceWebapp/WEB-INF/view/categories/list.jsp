<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Kategorie</title>
    <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />' />
    <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" />
</head>
<body>
<div class="container">
<h3>Kategorie:</h3>
    <div>
        <input type="button" value="Dodaj kategorię" class="btn btn-success" onclick="window.location.href='form';return false;" />
        <a href="${pageContext.request.contextPath}/" class="btn btn-primary"> Strona główna </a>
    </div>
<div>
    <table class="table">
        <tr>
            <th>Nazwa</th>
            <th>Edytuj</th>
            <th>Usuń</th>
        </tr>
        <c:forEach var="category" items="${categories}" >
            <c:url var="edit" value="/categories/edit">
                <c:param name="id" value="${category.id}"/>
            </c:url>
            <c:url var="delete" value="/categories/delete">
                <c:param name="id" value="${category.id}"/>
            </c:url>
            <tr>
                <td>${category.nazwa}</td>
                <td style="width:10%"><a href="${edit}" class="btn btn-primary">Edytuj</a></td>
                <td style="width:10%"><a href="${delete}" class="btn btn-danger">Usuń</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</div>
</body>
</html>