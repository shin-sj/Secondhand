<%@ include file="../IncludeTop.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<table>
  <tr style="vertical-align:top">
    <td style="text-align:left;width:20%;">
      <table id="main-menu">
        <tr>
          <td><a href='<c:url value="/shop/index.do"/>'>
            <b><font color="black" size="2">&lt;&lt; Main Menu</font></b></a></td>
        </tr>
      </table>
    </td>
   </tr>
</table>

<table align="center">
  <tr style="vertical-align:top">
    <td style="text-align:center">
      <h2>Shopping Cart</h2>
      <form action='<c:url value="/shop/updateCartQuantities.do"/>' method="post">
        <table id="cart" class="table table-hover" style="text-align:center">
          <thead>
          <tr bgcolor="#FFFFFF">
            <th scope="col"><b>Item</b></th>
            <th scope="col"><b>Description</b></th>
            <th scope="col"><b>In Stock?</b></th>
            <th scope="col"><b>Quantity</b></th>
            <th scope="col"><b>Price</b></th>
            <th scope="col"><b>Total Cost</b></th>
            <th scope="col">&nbsp;</th>
          </tr>
          </thead>
		  <tbody>
          <c:if test="${cart.numberOfItems == 0}">
            <tr bgcolor="#FFFFFF">
              <th colspan="8"><b>Your cart is empty.</b></th>
            </tr>
          </c:if>
          <c:forEach var="cartItem" items="${cart.cartItemList.pageList}">
            <tr bgcolor="#FFFFFF">
              <td scope="row">
                <a href='<c:url value="/shop/order/viewItem.do">
                  <c:param name="itemId" value="${cartItem.item.itemId}"/>
                  <c:param name="productId" value="${cartItem.item.productId}"/></c:url>'>
                  <c:out value="${cartItem.item.title}" />
                </a>
              </td>
              <td scope="row"><c:out value="${cartItem.item.description}" /></td>
              <td scope="row" style="text-align:center"><c:out value="${cartItem.inStock}" /></td>
              <td scope="row" style="text-align:center">
                <input type="text" size="3"
                  name='<c:out value="${cartItem.item.itemId}"/>'
                  value='<c:out value="${cartItem.quantity}"/>' /></td>
              <td scope="row" style="text-align:right"><fmt:formatNumber
                  value="${cartItem.item.unitCost}" pattern="###,###,###원" /></td>
              <td scope="row" style="text-align:right"><fmt:formatNumber
                  value="${cartItem.totalPrice}" pattern="###,###,###원" /></td>
              <td scope="row"><a href='<c:url value="/shop/removeItemFromCart.do">
                    <c:param name="workingItemId" value="${cartItem.item.itemId}"/></c:url>'>
                    <img width="40" height="40" src="${pageContext.request.contextPath}/images/delete.png" alt="" /></a>
              </td>
            </tr>
          </c:forEach>
          <tr bgcolor="#FFFFFF">
            <td colspan="7" align="right">
              <b>Sub Total: <fmt:formatNumber value="${cart.subTotal}" pattern="###,###,###원" /></b><br><br> 
              <input type="image" src="${pageContext.request.contextPath}/images/loop.png" name="update" width="40" height="40" />
            </td>
          </tr>
        </tbody>
        </table>
        <div style="text-align:center">
          <c:if test="${!cart.cartItemList.firstPage}">
            <a href='<c:url value="viewCart.do?page=previousCart"/>'>
              <font color="green"><B>&lt;&lt; Prev</B></font></a>
          </c:if>
          <c:if test="${!cart.cartItemList.lastPage}">
            <a href='<c:url value="viewCart.do?page=nextCart"/>'>
              <font color="green"><B>Next &gt;&gt;</B></font></a>
          </c:if>
        </div>
      </form> 
      <c:if test="${cart.numberOfItems > 0}">
        <br />
        <div style="text-align:center">
          <a href='<c:url value="/shop/checkout.do"/>'>
            Go to CheckOut</a>
        </div>
      </c:if>
    </td>
  </tr>
</table>
<%@ include file="../IncludeBottom.jsp" %>