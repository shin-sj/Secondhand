<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="itemTop.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<title>경매 상세 페이지</title>
</head>
<style type="text/css"> 
	a { text-decoration:none } 
</style> 
<style>

	table#detail {
		border: none;
		text-align: center;
		font-size: medium;
		padding: 15px;
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
	
	 th, td {
				border-bottom: 1px solid black;
			    border-collapse: collapse;
			    text-align: center;
			    font-size: medium;
			    padding: 15px;
	}
}

</style>
<script>
function participation(price, unitCost, startPrice) {
	var p = parseInt(price);
	var u = parseInt(unitCost);
	var s = parseInt(startPrice);
	
	alert("입력 가격 : " + p);

	if ((p > s) && (p > u)) {
		var c = confirm("경매에 참여하시겠습니까?");

		if (c) {
			form.submit();
		}
		else {
			alert("참여 취소");
		}
	} else {
		alert("참여 금액이 적습니다.");
	}
}


function getTime() { 
	var time = "${auction.deadLine}";

	var year = time.substring(0, 4);
	var month = time.substring(5, 7) - 1;
	var day = time.substring(8, 10);
	var hour = time.substring(11, 13);
	var minute = time.substring(14, 16);
	var second = time.substring(17);

	
now = new Date(); 
dday = new Date(year, month, day, hour, minute, second); 
// 원하는 날짜, 시간 정확하게 초단위까지 기입.
days = (dday - now) / 1000 / 60 / 60 / 24; 
daysRound = Math.floor(days); 
hours = (dday - now) / 1000 / 60 / 60 - (24 * daysRound); 
hoursRound = Math.floor(hours); 
minutes = (dday - now) / 1000 /60 - (24 * 60 * daysRound) - (60 * hoursRound); 
minutesRound = Math.floor(minutes); 
seconds = (dday - now) / 1000 - (24 * 60 * 60 * daysRound) - (60 * 60 * hoursRound) - (60 * minutesRound); 
secondsRound = Math.round(seconds); 

document.getElementById("counter0").innerHTML = daysRound; 
document.getElementById("counter1").innerHTML = hoursRound; 
document.getElementById("counter2").innerHTML = minutesRound; 
document.getElementById("counter3").innerHTML = secondsRound; 
newtime = window.setTimeout("getTime();", 1000); 
} 
</script>
<body>
<table id="main-menu">
  <tr>
    <td>
	    <a href='<c:url value="/shop/index.do"/>'>
	        <b><font color="black" size="2">&lt;&lt; Go to Index</font></b>
	    </a><br><br>
	    <a href='<c:url value="/shop/auction/listItem.do">
    				<c:param name="productId" value="${auction.productId}" />
    			</c:url>'>
	        <b><font color="black" size="2">&lt;&lt; Go to List</font></b>
	    </a><br>
    </td>
  </tr>
</table> 
	<table style="margin-left: auto; margin-right: auto;">
	<tr>
		<td style="text-align: left; padding: 0px; font-size: small; border-bottom: none;">
		${auction.viewCount}<font color=gray>view</font>
		</td>
	</tr>
   		<tr>
	   		<th style="border-right: 1px solid black; border-top: 1px solid black;">제목</th>
	   		<td style="border-top: 1px solid black;">${auction.title}</td>
   		</tr>
   		
   		<tr>
   			<th style="border-right: 1px solid black;">판매자</th>
   			<td>${auction.userId}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%--   			<c:if test="${auction.userId ne userSession.account.userId}">--%>
<%--					<c:choose>--%>
<%--		   				<c:when test="${! empty userSession.account.userId}">--%>
<%--&lt;%&ndash;							<%@ include file="/WEB-INF/jsp/account/accuseFunction.jsp" %>&ndash;%&gt;--%>
<%--						</c:when>--%>
<%--&lt;%&ndash;						<c:otherwise>&ndash;%&gt;--%>
<%--&lt;%&ndash;						<span><a href="<c:url value='/addAccuseNoLogin.do'>&ndash;%&gt;--%>
<%--&lt;%&ndash;						  <c:param name="itemId" value="${auction.itemId}"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;	                         <c:param name="productId" value="${auction.productId}"/></c:url>">&ndash;%&gt;--%>
<%--&lt;%&ndash;	             				판매자 신고하기</a></span>&ndash;%&gt;--%>
<%--&lt;%&ndash;						</c:otherwise>&ndash;%&gt;--%>
<%--					</c:choose>--%>
<%--			</c:if>--%>
   			</td>
   		</tr> <!-- userId = suppId -->
   		
   		<tr><td colspan="2" style="padding: 15px;">${auction.description}<br></td></tr>
   		
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
   			<th style="border-right: 1px solid black;"><font color=red>마감 날짜</font></th>
   			<td width="400">
   				<font color=red>${auction.deadLine}</font>
   				<c:set var="state" value="${auction.auctionState}"/>
   				<c:if test="${state eq 0}">
   				<br><br>경매 종료까지 "<font id=counter0></font>일 <font id=counter1></font>시간 <font id=counter2></font>분 <font id=counter3></font>초" 남았습니다
   				</c:if>
   			</td>
   			
   		</tr>
   		
   		<c:if test="${state eq 0}">
   		<script>getTime()</script>
   		</c:if>
   		
   		<tr>
   		<th style="border-right: 1px solid black;">가격</th>
   			<td>
   				시작 가격 : ${auction.startPrice}원<br>
   				<c:set var="state" value="${auction.auctionState}"/>
   				<c:if test="${state eq 0}">
   					<c:set var="uc" value="${auction.unitCost}" />
   					<c:if test="${uc eq 1}">
   					▷미참여◁<br>
   					</c:if>
   					<c:if test="${uc ne 1}">
   					현재 최대 금액 : ${auction.unitCost}원<br>
   					</c:if>
   					<br><font color=#6495ED>(경매 진행중)</font>
   				</c:if>
   				<c:if test="${state eq 1}">
   					낙찰 가격 : <ins></ins> ${auction.bidPrice}원<br>
   					<br><font color=#FF4500>(경매 종료)</font>
   				</c:if>
   			</td>
   		</tr>
   		
   		<tr>
   		<th style="border-right: 1px solid black;">수량</th> 
   		<td>${auction.qty}</td>
   		</tr>
   		
   		<tr>
   			<td colspan="2" style="border-bottom: none;">
				<c:if test="${state eq 0}">
   				<br><form name="form" action="<c:url value='/shop/auction/participateItem.do'/>">
   					<input type="text" id="price" name="price" placeholder="참여 가격을 입력하세요."/>
				&nbsp;
				<span onclick="participation(price.value, ${auction.unitCost}, ${auction.startPrice})">
					<!-- 로그인 여부 따지기 -->
					<a href="#">경매 참여</a>
				</span>
				</form>
				</c:if>
				<c:if test="${state eq 1}">
					<c:set var="id" value="${userSession.account.userId}"/>
					<c:set var="bidder" value="${bidder}"/>
					<c:if test="${id eq bidder}">
						<br><span>
						<!-- 로그인 여부 따지기 -->
						<c:set var="qty" value="${auction.qty}"/>
						<c:if test="${qty eq 0}">
							수량이 없습니다
						</c:if>
						<c:if test="${qty ne 0}">
							<a href="<c:url value="/shop/addItemToCart.do">
								     <c:param name="workingItemId" value="${auction.itemId}"/>
								     <c:param name="productId" value="${auction.productId}"/></c:url>">
								 장바구니 추가</a>
						</c:if>
						</span>
					</c:if>
				</c:if>
   			</td>
   		</tr>
   		
   		
   		<c:if test="${auction.userId eq userSession.account.userId}"> <!-- 로그인시 실행 -->
   		<tr>
   		<td colspan="2" style="text-align: right; padding: 0px; font-size: small; border-bottom: none; border-top: 1px solid black;">
		   <a href="<c:url value='/shop/auction/updateItem.do?itemId=${auction.itemId}'/>">[게시물 수정하기]</a>
		   <a href="<c:url value='/shop/auction/deleteItem.do?itemId=${auction.itemId}'/>"> [게시물 삭제하기]</a>
		   </td>
		 </tr>
		</c:if>
   	</table>
   	<br><br>
</body>
</html>