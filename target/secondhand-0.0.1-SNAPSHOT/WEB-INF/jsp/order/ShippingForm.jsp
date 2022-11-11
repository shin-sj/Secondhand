<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../IncludeTop.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="targetUrl"><c:url value="/shop/newOrderSubmitted.do" /></c:set>

<div align="center">
<form:form modelAttribute="orderForm" action="${targetUrl}" method="post">
  <form:errors cssClass="error" /><br><br>
	<h2>Order Infomation</h2>
  <table class="n13">
    <tr>
      <td colspan="2">
        <font color="GREEN" size="4"><b>Shipping Address</b></font></td>
    </tr>
    <tr>
      <td>name:</td>
      <td><form:input path="order.shipToUsername" />
        <form:errors path="order.shipToUsername" /></td>
    </tr>
    <tr>
      <td>Address 1:</td>
      <td><form:input path="order.shipAddr1" /> 
         <form:errors path="order.shipAddr1" /></td>
    </tr>
    <tr>
      <td>Address 2:</td>
      <td><form:input path="order.shipAddr2" /> 
         <form:errors path="order.shipAddr2" /></td>
    </tr>
    <tr>
      <td>City:</td>
      <td><form:input path="order.shipCity" /> 
        <form:errors path="order.shipCity" /></td>
    </tr>
    <tr>
      <td>Zip:</td>
      <td><form:input path="order.shipZip" /> 
        <form:errors path="order.shipZip" /></td>
    </tr>
    <tr>
      <td>Country:</td>
      <td><form:input path="order.shipCountry" /> 
        <form:errors path="order.shipCountry" /></td>
    </tr>
  </table>
  <br><br>
  <p>
    <input type="image" src="${pageContext.request.contextPath}/images/button_submit.gif">
  </p>
</form:form>
</div>
<%@ include file="../IncludeBottom.jsp" %>