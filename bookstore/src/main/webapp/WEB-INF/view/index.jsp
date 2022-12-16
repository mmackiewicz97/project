<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Navbar Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
<%--    <link href="/css/bootstrap.min.css" rel="stylesheet">--%>
    <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />' />

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" />
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample09" aria-controls="navbarsExample09" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExample09">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#">Disabled</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    downa masz
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>


<%--        <form class="form-inline my-2 my-md-0">--%>
<%--            <input class="form-control" type="text" placeholder="Search" aria-label="Search">--%>
<%--        </form>--%>
        <sec:authorize access="!isAuthenticated()">
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/login">Log in</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/bookstore/register/"> Register </a>
        </li>
        <li class="nav-item float-right">
            <a class="nav-link" href="/bookstore/adminregister/"> Admin Register </a>
        </li>
        </sec:authorize>
            <sec:authorize access="isAuthenticated()">
            <li class="nav-item">
                <form id="logout" action="<%=request.getContextPath()%>/logout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <input type="submit" value="Log out" class="nav-link">${user.username} </input>
                </form>
            </li>
            </sec:authorize>
        </ul>
        </div>
    </div>
</nav>


<p>Bookstore</p>
<a href="${pageContext.request.contextPath}/authors/"> Autorzy </a><br/>
<a href="${pageContext.request.contextPath}/books/"> Ksiazki </a><br/>
<a href="${pageContext.request.contextPath}/categories/"> Kategorie </a><br/>
<a href="${pageContext.request.contextPath}/shoppingcart/cart/"> koszyk </a><br/>
<br/>
br/>

<h1>Witaj</h1>
<div>
    <a href="${pageContext.request.contextPath}/success">success page</a>
</div>
<sec:authorize access="!isAuthenticated()">
    <a href="${pageContext.request.contextPath}/login">Zaloguj się</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
<form id="logout" action="<%=request.getContextPath()%>/logout" method="post" >
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="submit" value="logout" />
</form>
</sec:authorize>
<sec:authorize access="hasAuthority('ADMIN')">
<div>
    <a href="${pageContext.request.contextPath}/admin">Admin page</a>

</div>
</sec:authorize>
<sec:authorize access="hasAuthority('USER')">
<div>
    <a href="${pageContext.request.contextPath}/user">User page</a>
</div>
</sec:authorize>
<c:if test="${badShoppingCart}">
    <div class="alert alert-warning">Oops, chciałeś kupić nie swoje produkty!</div>
</c:if>
<c:if test="${!badShoppingCart}">
    <div class="alert alert-warning">Wszystko git, </div>
</c:if>
<!-- Bootstrap core JavaScript
   ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src='<c:url value="/resources/js/bootstrap.bundle.min.js" />'></script>
<script type="text/javascript" language="javascript" src='<c:url value="/resources/js/jquery-3.5.1.js" />'></script>

</body>
</html>
