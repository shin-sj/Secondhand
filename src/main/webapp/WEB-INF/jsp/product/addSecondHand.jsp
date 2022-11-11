<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="itemTop.jsp"%>
<style>
div#addItemForm {
	position: absolute;
	left: 18%;
	border: none;
	padding: 20px;
}
</style>

<table id="main-menu">
  <tr>
    <td><a href='<c:url value="/shop/secondHand/index.do"/>'>
        <b><font color="black" size="2">
          &lt;&lt; Go to Index</font></b></a>
    </td>
  </tr>
</table>

<div id="addItemForm">
	<h2>ADD ITEM</h2>
	<hr width="927px" align="left">
	<br>
	<br>

	<form:form modelAttribute="secondHandForm"  method="post" action="step3.do">
		
	정가: <form:input type="text" path="listPrice" value="${listPrice}" />
		<form:errors path="listPrice" />
		<br>
		<br>
	
	가격 흥정 여부: 
		<form:radiobuttons items="${radioKind}" path="discount"/>
		<br><br>

		<a href="<c:url value='/shop/secondHand/gobackItem.do'>
		         <c:param name="productId" value="${productId}"/>
		     </c:url>">[이전 단계로]</a> 
		<input type="submit" value="다음 단계로" />
	</form:form>
</div>

</body>
</html>