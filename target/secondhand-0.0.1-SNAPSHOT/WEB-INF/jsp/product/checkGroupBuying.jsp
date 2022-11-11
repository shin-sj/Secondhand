<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="itemTop.jsp" %>
<%@ page import="com.secondhand.controller.item.ItemForm" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<title>Step3</title>
</head>
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
    <td><a href='<c:url value="/shop/groupBuying/index.do"/>'>
        <b><font color="black" size="2">
          &lt;&lt; Go to Index</font></b></a>
    </td>
  </tr>
</table>
<body>
<h1>공동구매 최종 확인</h1>
다음 정보로 판매하시겠습니까?<br><br>

<form:form modelAttribute="GroupBuying" action="detailItem.do">
<label>제목</label>: ${itemForm.title} <br> 
<label>내용</label>: ${itemForm.description} <br> 
<label>정가</label>: ${GroupBuying.listPrice}원 <br> 
<label>판매가</label>: ${itemForm.unitCost}원 <br> 
<label>할인율</label>: ${GroupBuying.discount}% <br> 
<label>수량</label>: ${itemForm.qty} <br> 
<label>태그</label>: <c:forEach var="tag" items="${tags}"> 
                  <c:if test="${not empty tag.tagName}">
                     <font color="blue">#</font>${tag.tagName}
                  </c:if>
               </c:forEach>
<br> 
<label>마감기한</label>: ${GroupBuying.deadLine} <br><br>

<a href="<c:url value='/shop/groupBuying/addItem2.do'>
         <c:param name="productId" value="${itemForm.productId}"/>
       </c:url>
      ">[이전 단계로]</a> <input type="submit" value="확인" />
</form:form>
</body>
</html>