<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Koszyk</title>
  <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css" />' />
  <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" />
</head>
<body>
<script type="text/javascript" language="javascript" src='<c:url value="/resources/js/jquery-3.5.1.js" />'></script>
<script type="text/javascript" language="javascript" src='<c:url value="/resources/js/scripts.js" />'></script>
<div class="container">
  <div class="row">
    <div class="col-sm">
        <h2 class="section-headline">
          <span>Zamówione książki:</span>
        </h2>
    </div>
  </div>
<%--  <hr style="position: absolute; width:85%; height: 6px; background-color: #333; z-index: -1; margin-top: -80px;" />--%>
  <div class="row" style="margin-top: 10px;">
        <div class="col-sm-9 text-left">
          <a class="btn btn-warning" href="${pageContext.request.contextPath}/books/">Kontynuuj zamówienie</a>
        </div>
        <div class="col-sm text-right">
            <c:url var="koszyk" value="/check/address">
                <c:param name="shoppingcart" value="${shoppingCart.id}"/>
            </c:url>
          <a class="btn btn-primary" href="${koszyk}">Koszyk</a>
        </div>
        <br />
        <c:if test="${notEnoughStock}">
          <div class="alert alert-warning">
            Oops, chcesz kupić więcej niż jest dostępne w sklepie! Zmniejsz ilość wybranych produktów.</div>
        </c:if>
        <c:if test="${emptyCart}">
          <div class="alert alert-warning">Oops, twój koszyk jest pusty. Sprwadź listę książek i dodaj je do zamówienia.</div>
        </c:if>
      </div>


        <div class="row">
          <div class="col-sm">
            <h4>Produkty</h4>
          </div>
          <div class="col-sm">
            <h4>Cena</h4>
          </div>
          <div class="col-sm">
            <h4>Ilość</h4>
          </div>
            <div class="col-sm">
                <h4>Suma</h4>
            </div>
        </div>

        <!--**************** display products in cart ****************-->
<c:forEach var="cartItem" items="${cartItemList}" >
    <c:url var="delete" value="/shoppingcart/removeitem">
        <c:param name="cartItemId" value="${cartItem.id}"/>
    </c:url>
        <div class="row">

            <hr />
            <div class="col-sm">${cartItem.book.nazwa}</div>
              <div class="col-sm">${cartItem.book.cena}</div>
            <div class="col-sm">
                <form action="${pageContext.request.contextPath}/shoppingcart/updatecartitem" method="post">
                    <input hidden="hidden" name="cartItemId" value="${cartItem.id}" />
                    <input id="${cartItem.id}" name="qty" value="${cartItem.qty}" class="form-control cartItemQty"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <button style="display: none;" id="update-item-${cartItem.id}" type="submit" class="btn btn-danger btn-sm">Update</button>
                </form>
            </div>
              <div class="col-sm">${cartItem.subtotal}
                  <a href="${delete}" class="btn btn-danger">Usuń</a>
              </div>

  </div>
</c:forEach>


        <div class="row">
          <div class="col-sm-9"></div>
          <div class="col-sm">
             <span style="color: #db3208; font-size: large;">Suma: (${cartItemList.size()})<span>${shoppingCart.grandTotal}</span>zł</span>
          </div>
        </div>
</div>
<!-- end of container -->



</body>
</html>
