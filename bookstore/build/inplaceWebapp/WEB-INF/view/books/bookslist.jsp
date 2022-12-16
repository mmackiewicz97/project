<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>List books</title>
    <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />' />
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/css/jquery.dataTables.min.css" />'>
</head>
<body>
<div class="container">
<h3>Książki:</h3>
    <div>
        <input type="button" value="Dodaj książkę" class="btn btn-success" onclick="window.location.href='form';return false;" />
        <a href="${pageContext.request.contextPath}/" class="btn btn-primary"> Strona główna </a>
    </div>
    <table id="example">
        <thead>
        <tr>
            <th
            >Nazwa</th>
            <th>Wydawnictwo</th>
            <th>Cena</th>
            <th>Kategoria</th>
            <th>Okladka</th>
            <th>Opis</th>
            <th>Usuń</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${books}" >
            <c:url var="edit" value="/books/info">
                <c:param name="bookId" value="${book.id}"/>
            </c:url>
            <c:url var="delete" value="/books/delete">
                <c:param name="bookId" value="${book.id}"/>
            </c:url>
            <tr>
                <td>${book.nazwa}</td>
                <td>${book.wydawnictwo}</td>
                <td>${book.cena}</td>
                <td>${book.kategoria.nazwa}</td>
                <td><img src="data:image/jpg;base64,${book.okladka}" width="150" height="150" /></td>
<%--                <td>${book.okladka}</td>--%>
                <td><a href="${edit}" class="btn btn-primary">Opis</a></td>
                <td><a href="${delete}" class="btn btn-danger">Usuń</a></td>


            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<script type="text/javascript" language="javascript" src='<c:url value="/resources/js/jquery-3.5.1.js" />'></script>
<script type="text/javascript" language="javascript" charset="utf8" src='<c:url value="/resources/js/jquery.dataTables.min.js" />'></script>

<script>
//     $(document).ready(function() {
//     $('#example').DataTable( {
//         "lengthMenu": [[5,10,15,20,-1],[5,10,15,20,"All"]],
//         "order": [[ 3, "desc" ]],
//         } );
// } );
    $(document).ready( function () {
        $('#example').DataTable(
            {
                "lengthMenu": [[-1,1,5,10,20],["All",1,5,10,20]],
            }
        );
    } );
</script>
</body>
</html>