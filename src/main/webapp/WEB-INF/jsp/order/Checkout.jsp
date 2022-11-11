<%@ include file="../IncludeTop.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 
<table>
  <tr style="vertical-align:top">
    <td style="text-align:left;width:20%;">
      <table id="main-menu">
        <tr>
          <td><a href='<c:url value="/shop/viewCart.do"/>'>
            <b><font color="black" size="2">&lt;&lt; Shopping Cart</font></b></a></td>
        </tr>
      </table>
    </td>
   </tr>
</table>

<table align="center">
  <tr>
    <td style="text-align: center; vertical-align: top">
      <h2>Checkout Summary</h2>
      <table class="table table-hover" style="text-align:center">
      	<thead>
        <tr bgcolor="#FFFFFF">
            <th scope="col"><b>제품명</b></th>
            <th scope="col"><b>설명</b></th>
            <th scope="col"><b>재고여부</b></th>
            <th scope="col"><b>개수</b></th>
            <th scope="col"><b>가격</b></th>
            <th scope="col"><b>총액</b></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="cartItem" items="${cart.cartItemList.pageList}">
          <tr bgcolor="#FFFFFF">
            <td scope="row">
               	<a href='<c:url value="/shop/order/viewItem.do">
                  <c:param name="itemId" value="${cartItem.item.itemId}"/>
                  <c:param name="productId" value="${cartItem.item.productId}"/></c:url>'>
                  <c:out value="${cartItem.item.title}" />
                </a>
            </td>
            <td scope="row">
              <c:out value="${cartItem.item.description}" />
            </td>
            <td scope="row" align="center"><c:out value="${cartItem.inStock}" /></td>
            <td scope="row" align="center"><c:out value="${cartItem.quantity}" /></td>
            <td scope="row" align="right"><fmt:formatNumber
                value="${cartItem.item.unitCost}" pattern="###,###,###원" /></td>
            <td scope="row" align="right"><fmt:formatNumber
                value="${cartItem.totalPrice}" pattern="###,###,###원" /></td>
          </tr>
        </c:forEach>
        <tr bgcolor="#FFFFFF">
          <td scope="row" colspan="7" align="right"><b>Sub Total: <fmt:formatNumber
                value="${cart.subTotal}" pattern="###,###,###원" /></b><br /></td>
        </tr>
        </tbody>
      </table>
      <c:if test="${!cart.cartItemList.firstPage}">
        <a href="checkout.do?page=previousCart"><font color="green">
          <B>&lt;&lt; Prev</B></font></a>
      </c:if>
      <c:if test="${!cart.cartItemList.lastPage}">
        <a href="checkout.do?page=nextCart"><font color="green">
          <B>Next &gt;&gt;</B></font></a>
      </c:if>
      <br> 
      <a href='<c:url value="/shop/newOrder.do"/>'>
        	주문하기</a>
    </td>
    <td style="text-align: right; vertical-align: top; width: 20%">&nbsp;</td>
  </tr>
</table>
<%@ include file="../IncludeBottom.jsp" %>