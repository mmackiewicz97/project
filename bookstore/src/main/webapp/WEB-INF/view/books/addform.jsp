<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Dodawanie książki</title>
    <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />' />

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" />
</head>
<body>

<%--        <c:forEach var="author" items="${authors}" >--%>
<%--&lt;%&ndash;            <td><form:checkboxes path="autorzy" items="${authors}" itemValue="id" id="authors"/></td>&ndash;%&gt;--%>
<%--            <tr><td></td><td><form:checkbox path="autorzy" value="${author.id}" checked="checked"/>${author.imie} ${author.nazwisko}</td></tr>--%>
<%--        </c:forEach>--%>
<div class="container">
    <h3>Dodaj Książkę</h3>
<form:form action="form" modelAttribute="book" method="POST" enctype="multipart/form-data">

<%--    <form:form action="form" modelAttribute="book" enctype="multipart/form-data" method="POST">--%>
    <div class="form-group">
        <label>Tytuł:</label>
        <form:input path="nazwa" class="form-control"/>
    </div>
    <div class="form-group">
        <label>Wydawnictwo:</label>
        <form:input path="wydawnictwo" class="form-control"/>
    </div>
    <div class="form-group">
        <label>Cena:</label>
        <form:input path="cena" class="form-control"/>
    </div>
    <div class="form-group">
        <label>Ilość:</label>
        <form:input path="ilosc" class="form-control"/>
    </div>
    <div class="form-group">
        <label>Kategoria</label>
        <form:select path="kategoria" class="form-control">
            <form:options items="${categories}" itemValue="id" itemLabel="nazwa" />
        </form:select>

    </div>
    <div class="form-group">
        <label>Autorzy:</label>
        <form:select multiple="true" path="autorzy" id="authors" class="form-control">
            <form:options items="${authors}" itemValue="id" itemLabel="fullName"/>
        </form:select>
    </div>

    <div class="form-group">
        <label>File inpust:</label>
<%--        <form:input path="okladka" type="file" name="okladka" class="form-control-file"/>--%>
        <input type="file" name="okladka2" class="form-control-file"/>
    </div>
    <form:hidden path="id"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <button type="submit" class="btn btn-primary" value="Save" class="save">Zapisz</button>

</form:form>
<a class="btn btn-danger" href="${pageContext.request.contextPath}/books/ "> Powróć </a>
</div>
</body>
</html>