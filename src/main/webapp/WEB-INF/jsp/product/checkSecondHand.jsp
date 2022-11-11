<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="itemTop.jsp"%>

<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>

<title>중고거래 입력 최종 확인 step3</title>
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

<h1>중고거래 최종 확인</h1>
<h2>다음 정보로 판매하시겠습니까?</h2><br><br>

<form:form action="done.do">
      <hr width="927px" align="left">
      <br><br>
      <label>제목</label> : ${itemForm.title} <br>
      <label>내용</label> : ${itemForm.description} <br>
      <label>수량</label> : ${itemForm.qty} <br>
      <label>정가</label> : ${itemForm.unitCost}원 <br> 
      <label>판매가</label> : ${secondHandForm.listPrice}원 <br> 
       
      <label>가격 흥정 여부</label> :
      		<c:choose>
	   				<c:when test="${secondHandForm.discount eq 'Yes'}">
						<td><c:out value="흥정 가능" /></td> 
					</c:when>
					<c:otherwise>
						<td> <c:out value="흥정 불가능" /></td> 
					</c:otherwise>
			</c:choose><br>
      <label>태그</label> : <c:forEach var="tag" items="${tags}"> 
                  <c:if test="${not empty tag.tagName}">
                     <font color="blue">#</font>${tag.tagName}
                  </c:if>
               </c:forEach><br>

      <a href="<c:url value='/shop/secondHand/addItem2.do'>
         <c:param name="productId" value="${itemForm.productId}"/></c:url>">[이전 단계로]&nbsp;&nbsp;</a> 
      <input type="submit" value="[확인]" /> 
</form:form>

</body>
</html>