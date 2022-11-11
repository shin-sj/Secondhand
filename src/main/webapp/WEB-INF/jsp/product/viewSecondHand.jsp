<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="itemTop.jsp" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

<title>중고거래 상세페이지</title>
<style type="text/css"> 
	a { text-decoration:none } 
</style> 
<style>
	.right-box {
	  float: right;
	  border-radius: 2em;
	  text-align: center;
	}
	
	span {
		width:10%; 
		height: 10px; 
		border: 1px solid blue;
		border-radius: 2em;
		font-size: small;
		text-align: center;
		padding: 5px;
	}
	
	table {
			border: none;
		 	text-align: center;
			font-size: medium;
			padding: 15px;
	}
	
	 th, td {
				border-bottom: 1px solid black;
			    border-collapse: collapse;
			    text-align: center;
			    font-size: medium;
			    padding: 15px;
	}

</style>
<body>
<!-- 여기서 secondHand는 컨트롤러에서 보내준 모델 객체(db에서 select 결과 ) -->
<table id="main-menu">
  <tr>
    <td>
	    <a href='<c:url value="/shop/index.do"/>'>
	        <b><font color="black" size="2">&lt;&lt; Go to Index</font></b>
	    </a><br><br>
	    <a href='<c:url value="/shop/secondHand/listItem.do">
    				<c:param name="productId" value="${sh.productId}" />
    			</c:url>'>
	        <b><font color="black" size="2">&lt;&lt; Go to List</font></b>
	    </a><br>
    </td>
  </tr>
</table> 
<table style="margin-left: auto; margin-right: auto;">
	<tr>
		<td style="text-align: left; padding: 0px; font-size: small; border-bottom: none;">
		${sh.viewCount}<font color=gray>view</font>
		</td>
	</tr>
   	<tr>
	   	<th style="border-right: 1px solid black; border-top: 1px solid black;">제목</th>
	   	<td style="border-top: 1px solid black;">${sh.title}</td>
   	</tr>	
   	<tr>
   		<th style="border-right: 1px solid black;">판매자</th>
   		<td>${sh.userId}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<c:if test="${sh.userId ne userSession.account.userId}">	

			</c:if>
   		</td>
   	</tr> 
   	<tr>
   		<td colspan="2" style="padding: 15px;">${sh.description}<br></td>
   	</tr>
   	<tr>
   			<th style="border-right: 1px solid black;"><font color=blue>#</font>관련 태그</th>
   			<td>
   			<c:forEach var="tag" items="${tags}">        
	   			<a href='<c:url value="/shop/search/viewItem.do">
	   			<c:param name="tagName" value="${tag.tagName}"/></c:url>'>
	   			#${tag.tagName}
				</a>&nbsp;
			</c:forEach>
   			</td>
   		</tr>
   	<tr>
   			<th style="border-right: 1px solid black;">가격</th>
   			<td>
   				 인터넷 정가  : ${sh.listPrice}원<br>
   				 중고거래 판매가 : ${sh.unitCost}원 
   			</td>
   	</tr>
   	<tr>
   			<th style="border-right: 1px solid black;">에눌가능여부</th>
   			<c:choose>
	   				<c:when test="${sh.discount eq 1}">
						<td><c:out value="에눌 가능" /></td> 
					</c:when>
					<c:otherwise>
						<td> <c:out value="에눌 불가능" /></td> 
					</c:otherwise>
			</c:choose>
   	</tr>
   	<tr>
   		<th style="border-right: 1px solid black;">수량 </th> 
   		<td>
	   			<c:if test="${sh.qty <= 0}">
			        <button type="button" class="btn btn-outline-danger">품절</button>	
			    </c:if> 
			    <c:if test="${sh.qty > 0}">
			        <font size="2"><fmt:formatNumber value="${sh.qty}" /> 개 남았습니다.</font>
			    </c:if>
	   </td>
   	</tr>	

   	<c:if test="${sh.qty <= 0}">
   			<tr>
   				<td colspan="2" style="border-bottom: none;">
   					<span>	
   						<a href="<c:url value="/shop/addItemToCart.do">
					     	<c:param name="workingItemId" value="${sh.itemId}"/>
					     	<c:param name="productId" value="${sh.productId}"/></c:url>">
					 			장바구니 추가</a>
					</span>	
				</td>	
			</tr>
	</c:if>	
   	<c:if test="${sh.userId eq userSession.account.userId}"> <!-- 로그인시 실행 -->
		<tr>   		
				<td colspan="2" style="text-align: right; padding: 0px; font-size: small; border-bottom: none; border-top: 1px solid black;">
				   <a href="<c:url value='/shop/secondHand/edit.do'>
				   				<c:param name="itemId" value="${sh.itemId}" />
				   			</c:url>
				   			">[게시물 수정하기]</a>
				   <a href="<c:url value='/shop/secondHand/delete.do'>
				   				<c:param name="itemId" value="${sh.itemId}" />
				   				<c:param name="productId" value="${sh.productId}" />
				   			</c:url>
				   			"> [게시물 삭제하기]</a>
				</td>		
		</tr>
	</c:if>
</table>				
</body>
</html>