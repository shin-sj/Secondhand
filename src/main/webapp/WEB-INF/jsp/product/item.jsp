<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="itemTop.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<title>물품 판매 입력 폼</title>
</head>
<style>
	div#addItemForm {
		position: absolute;
		left: 18%;
		border: none;
		padding: 20px;
	}
</style>

<body>
<table id="main-menu">
  <tr>
    <td><a href='<c:url value="/shop/item/index.do">
    				<c:param name="productId" value="${productId}"/>
    			</c:url>'>
        <b><font color="black" size="2">
          &lt;&lt; Go to Index</font></b></a>
    </td>
  </tr>
</table>
<div id = "addItemForm">
<h2>ADD ITEM</h2><hr><br><br>
	<spring:hasBindErrors name="item" />
	<form:form modelAttribute="item" method="post" action="addItem2.do?productId=${productId}">
	
	제목 : <form:input type="text" path="title" style="width:380px; height:35px;" value="${title}"/>
	<form:errors path="title"/> <br><br>
	
	내용 : <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<form:textarea path="description" cols="50" rows="10" value="${discription}"/>
	<form:errors path="description"/> <br><br>
	
	<c:set var="pId" value="${productId}"/>
	<c:if test="${pId == 1}">
	<form:input type="text" path="unitCost" value="1" readonly="true" hidden="true"/>
	<form:errors path="unitCost"/> <br><br>
	</c:if>
	<c:if test="${pId != 1}">
	판매가 : <form:input type="text" path="unitCost" value="${unitCost}"/>
	<form:errors path="unitCost"/> <br><br>
	</c:if>
	
	재고 : <form:input type="text" path="qty" value="${qty}"/>
	<form:errors path="qty"/> <br><br>
	
	태그 : <c:forEach var="i" begin="0" end="4" step="1">
				<form:input path="tags[${i}].tagName" placeholder="#tag"/>&nbsp;
		</c:forEach>
	<br><br><br>
	
	<input type="submit" value="다음 단계로" />
	
	</form:form>
</div>
</body>
</html>