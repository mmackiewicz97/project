<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Book info</title>
    <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />' />
    <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" />
</head>
<body>
<c:url var="edit" value="/books/edit">
    <c:param name="bookId" value="${book.id}"/>
</c:url>
<c:url var="delete" value="/books/delete">
    <c:param name="bookId" value="${book.id}"/>
</c:url>
<div class="container">
    <div>
        <ol class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/books/" class="btn btn-primary">Powróć</a></li>
            <li><a href="${edit}" class="btn btn-success">Edytuj</a></li>
            <li><a href="${delete}" class="btn btn-danger">Usuń</a></li>
            <li><a href="${pageContext.request.contextPath}/shoppingcart/cart" class="btn btn-warning">Koszyk</a></li>

        </ol>

    </div>

    <div class="row">
        <div class="col-xs-3">
            <div class="panel panel-default">
                <div class="panel-body">
<%--                    <img th:src="@{/image/book/}+${book.id}+'.png'" class="img-responsive center-block" />--%>
                </div>
            </div>
        </div>
        <div class="col-xs-9">
            <h3>Tytuł: ${book.nazwa}</h3>
            <div class="row">
                <div class="col-xs-6">
                    <p><strong>Autor: </strong>
                        <c:forEach var="author" items="${book.autorzy}" >
                    <p><span>${author.fullName}</span></p>
                        </c:forEach>
                            </p>
                    <p><strong>Wydawnictwo: </strong><span>${book.wydawnictwo}</span></p>
                    <p><strong>Kategoria: </strong><span>${book.kategoria.nazwa}</span></p>
                    <p><strong>Cena: </strong><span>${book.cena} zł</span></p>
                    <p><strong>Dostępna ilość: </strong><span>${book.ilosc}</span></p>
                    <p><strong>Okładka:</strong></p>
                    <img src="data:image/jpg;base64,${book.okladka}" width="150" height="150" />
                </div>
            </div>
        </div>

    </div>


    <form action="${pageContext.request.contextPath}/shoppingcart/additem" method="post">
        <input hidden="hidden" name="bookId" value="${book.id}" />
        <label>Ilość:</label>
        <input name="qty" value="1"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <button type="submit" class="btn btn-warning" style="color:black;border:1px solid black; padding: 10px 40px 10px 40px;">Dodaj do koszyka</button>
    </form>
    <c:if test="${addBookSuccess}">
        <h3><span style="color: forestgreen"><i class="fa fa-check" aria-hidden="true" style="color: forestgreen"></i>Dodano do koszyka!</span></h3>
    </c:if>
    <c:if test="${notEnoughStock}">
        <h3><span style="color: red">Oops, nie ma takiej ilości. <span>${book.ilosc}</span> W sprzedaży.</span></h3>
    </c:if>
<%--    <form action="${pageContext.request.contextPath}/shoppingcart/additem" method="post">--%>
<%--&lt;%&ndash;        <form:form action="/shoppingcart/additem" modelAttribute="book" method="POST">&ndash;%&gt;--%>
<%--        <input hidden="hidden" name="book" value="${book}" />--%>
<%--        <div class="row" style="margin-top: 120px;">--%>
<%--            <div class="col-xs-9">--%>


<%--                    <div class="col-xs-7">--%>
<%--                        <div class="panel panel-default" style="border-width: 5px; margin-top: 20px;">--%>
<%--                            <div class="panel-body">--%>
<%--                                <div class="row">--%>
<%--                                    <div class="col-xs-6">--%>
<%--                                        <h4>Cena: <span style="color:#db3208;">$<span>${book.cena} zł</span></span></h4>--%>
<%--&lt;%&ndash;            <form:form modelAttribute="qty" method="POST">&ndash;%&gt;--%>
<%--                                        <span>Qty: </span>--%>
<%--                                        <select name="qty">--%>
<%--                                            <option value="1"> 1</option>--%>
<%--                                            <option value="2"> 2</option>--%>
<%--                                            <option value="3"> 3</option>--%>
<%--                                            <option value="4"> 4</option>--%>
<%--                                            <option value="5"> 5</option>--%>
<%--                                        </select>--%>
<%--&lt;%&ndash;            </form:form>&ndash;%&gt;--%>
<%--                                    </div>--%>
<%--                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>

<%--                                        <button type="submit" class="btn btn-warning" style="color:black;border:1px solid black; padding: 10px 40px 10px 40px;">Add to Cart</button>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--        </div>--%>
<%--&lt;%&ndash;        </form:form>&ndash;%&gt;--%>
<%--    </form>--%>


</div>
</body>
</html>
