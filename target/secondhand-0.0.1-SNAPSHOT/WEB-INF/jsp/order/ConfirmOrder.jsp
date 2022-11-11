<%@ include file="../IncludeTop.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 
<table id="main-menu">
  <tr><td>
    <a href='<c:url value="/shop/index.do"/>'>
      <b><font color="black" size="2">&lt;&lt; Main Menu</font></b></a>
  </td></tr>
</table>

<div align="center">
  <p>
    <b>Please confirm the information below and then press continue...</b>
  </p>
  <p></p>
  <table class="n13">
    <tr>
      <td align="center" colspan="2">
      	<h2>Order Infomation</h2>
        <font size="3">
          <b><fmt:formatDate value="${orderForm.order.orderDate}" 
            pattern="yyyy/MM/dd hh:mm:ss" /></b></font>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <font color="GREEN" size="4"><b>Billing Address</b></font></td>
    </tr>
    <tr>
      <td>name:</td>
      <td>${orderForm.order.billToUsername}</td>
    </tr>
    <tr>
      <td>Address 1:</td>
      <td>${orderForm.order.billAddr1}</td>
    </tr>
    <tr>
      <td>Address 2:</td>
      <td>${orderForm.order.billAddr2}</td>
    </tr>
    <tr>
      <td>City:</td>
      <td>${orderForm.order.billCity}</td>
    </tr>
    <tr>
      <td>Zip:</td>
      <td>${orderForm.order.billZip}</td>
    </tr>
    <tr>
      <td>Country:</td>
      <td>${orderForm.order.billCountry}</td>
    </tr>
    <tr>
      <td colspan="2">
        <font color="GREEN" size="4"><b>Shipping Address</b></font></td>
    </tr>
    <tr>
      <td>First name:</td>
      <td>${orderForm.order.shipToUsername}</td>
    </tr>
    <tr>
      <td>Address 1:</td>
      <td>${orderForm.order.shipAddr1}</td>
    </tr>
    <tr>
      <td>Address 2:</td>
      <td>${orderForm.order.shipAddr2}</td>
    </tr>
    <tr>
      <td>City:</td>
      <td>${orderForm.order.shipCity}</td>
    </tr>
    <tr>
      <td>Zip:</td>
      <td>${orderForm.order.shipZip}</td>
    </tr>
    <tr>
      <td>Country:</td>
      <td>${orderForm.order.shipCountry}</td>
    </tr>
  </table>
  <br><br>
  <p>
    <a href='<c:url value="/shop/confirmOrder.do"/>'>
      <img border="0" src="${pageContext.request.contextPath}/images/button_continue.gif" alt="" /></a>
  </p>
</div>
<%@ include file="../IncludeBottom.jsp" %>