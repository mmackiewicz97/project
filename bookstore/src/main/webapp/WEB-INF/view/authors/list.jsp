<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Autorzy</title>
    <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />' />
    <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" />
</head>
<body>
<div class="container">
<h3>Autorzy:</h3>
    <div>
        <input type="button" value="Dodaj autora" class="btn btn-success" onclick="window.location.href='form';return false;" />
        <a href="${pageContext.request.contextPath}/" class="btn btn-primary"> Strona główna </a>
    </div>
<div>

    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th>Imie</th>
            <th>Nazwisko</th>
            <th>Edytuj</th>
            <th>Usuń</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="autor" items="${autors}" >
            <c:url var="edit" value="/authors/edit">
                <c:param name="id" value="${autor.id}"/>
            </c:url>
            <c:url var="delete" value="/authors/delete">
                <c:param name="id" value="${autor.id}"/>
            </c:url>
            <tr>
                <td>${autor.imie}</td>
                <td>${autor.nazwisko}</td>
                <td style="width:10%"><a href="${edit}" class="btn btn-primary">Edytuj</a></td>
                <td style="width:10%"><a href="${delete}" class="btn btn-danger">Usuń</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>