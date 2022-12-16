<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Koszyk</title>
    <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />' />
    <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" />
</head>
<body>
<script type="text/javascript" language="javascript" src='<c:url value="/resources/js/jquery-3.5.1.js" />'></script>
<div class="container">
    <h1 class="display-2">Podsumowanie zamówienia</h1>

<%--   --%>
<%--    <div class="row">--%>
<%--        <form action="/check/out" method="post">--%>
<%--            <div class="col-sm-3">--%>
<%--                ${shoppingCart.grandTotal}--%>
<%--            </div>--%>
<%--            <div class="col-sm-3">--%>
<%--                Cena za wszystko--%>
<%--            </div>--%>
<%--        </form>--%>
<%--    </div>--%>
    <div id="addressInfo">
            <div class="row">
                <h1>Adres</h1>
                <div class="col-sm-9">
                    <h4>Miejscowość</h4>
                </div>
                <div class="col-sm">
                    ${shippingAddress.shippingAddressCity}
                </div>
            </div>
            <div class="row">
                <div class="col-sm-9">
                    <h4>Ulica</h4>
                </div>
                <div class="col-sm">
                    ${shippingAddress.shippingAddressStreet}
                </div>
            </div>
            <div class="row">
                <div class="col-sm-9">
                    <h4>Województwo</h4>
                </div>
                <div class="col-sm">
                    ${shippingAddress.shippingAddressState}
                </div>
            </div>
            <div class="row">
                <div class="col-sm-9">
                    <h4>Kod pocztowy</h4>
                </div>
                <div class="col-sm">
                    ${shippingAddress.shippingAddressZipcode}
                </div>
            </div>
        </div>
    <div class="row">
        <h1>Przedmioty</h1>
        <hr />
        <div class="col-sm"><h4>Tytuł</h4></div>
        <div class="col-sm"><h4>Cena</h4></div>
        <div class="col-sm"><h4>Ilość</h4></div>
        <div class="col-sm"><h4>Suma</h4></div>
    </div>
<c:forEach var="cartItem" items="${cartItemList}">
    <div class="row">

    <hr />
    <div class="col-sm">
        <img src="data:image/jpg;base64,${cartItem.book.okladka}" width="150" height="150" />
            ${cartItem.book.nazwa}
    </div>
    <div class="col-sm">${cartItem.book.cena}</div>
    <div class="col-sm">${cartItem.qty}</div>
    <div class="col-sm">${cartItem.subtotal}</div>
    </div>
    </c:forEach>
    <div class="row">
        <h1>Podsumowanie</h1>
        <div class="col-sm-9">
            <h4>Suma (${cartItemList.size()} książek)</h4>
        </div>
        <div class="col-sm">
            <h4><span class="bg-danger">${shoppingCart.grandTotal} zł</span></h4>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-9"></div>
        <div class="col-sm"><button class="btn btn-danger">Kup</button></div>
    </div>
</div>
</body>
</body>
</html>
