<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="itemTop.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<title>공동구매 리스트 페이지</title>
<style>
   th, td {
      text-align: center;
      height:70px;
      padding-left:30px;
      padding-right:30px;
   }
         
   span#black {
      width:10%; 
      height: 10px; 
      border: 1px solid black;
      border-radius: 2em;
      font-size: small;
      text-align: center;
      padding: 5px;
   }
   
   span#red {
      width:10%; 
      height: 10px; 
      border: 1px solid red;
      border-radius: 2em;
      font-size: small;
      text-align: center;
      padding: 5px;
   }
</style>
</head>
<body>    
<table id="main-menu">
  <tr>
    <td><a href='<c:url value="/shop/index.do"/>'>
        <b><font color="black" size="2">
          &lt;&lt; Go to Index</font></b></a>
    </td>
  </tr>
</table> 
   <form name = "pform" action="" style="position:absolute; left:42%; margin:0 0 0 -400px;">
      <div class="container" >
         <div class="row"  style="display:inline">
            <div style="display:inline;float:left;">
               <div style="font-size:15px">
                  <h2>
                        GroupBuying List
                  </h2>
                  <p style="text-align:right;">
                      <a href="<c:url value='/shop/item/addItem.do'>
                           <c:param name="productId" value="${productId}"/></c:url>
                  ">공동구매 물품 판매하기
                  </a>
               </p>   <!-- 로그인 여부 인터셉터로 이동 -->
                  <hr>
                  <table>
                     <tr>
                        <th>글 제목</th>
                        <th>가격</th>
                        <th>할인율</th>
                        <th>수량</th>
                        <th>마감 날짜</th>
                        <th/>
                     </tr>
                     
                  <tbody>    
                  
      
               <c:forEach var="gb" items="${groupBuyingList.pageList}">         
                  <tr style="height:70px;">
                  
                  <td>
                                <a href="<c:url value='/shop/groupBuying/viewItem.do'>
                                           <c:param name="itemId" value="${gb.itemId}"/>
                                         <c:param name="productId" value="${gb.productId}"/>
                                       </c:url>">
                                      <font>${gb.title}</font>
                                </a>
                
                   </td>
                   <td><del>정가 &nbsp;&nbsp;${gb.listPrice}원</del><br>할인가 &nbsp;${gb.unitCost}원</td>
                   <td>${gb.discount}%</td>
                   <td>${gb.qty}</td>
                   <td>${gb.deadLine}<br></td>
                   <td>
                         <c:choose>
                     <c:when test="${gb.state eq 1}"><span id="black">마감</span></c:when>
                     <c:otherwise><span id="red"><font color="red">진행중</font></span></c:otherwise>
                  </c:choose>
                  &nbsp;&nbsp;
                   </td>
                       
                       </tr>
                  </c:forEach>
                  </tbody>
                  
                  <tr>
                     <td style="text-align: left;">
                        <c:if test="${!groupBuyingList.firstPage}">
                           <a href='<c:url value="/shop/groupBuying/listItem2.do">
                                         <c:param name="pageName" value="previous"/>
                                         <c:param name="productId" value="${productId}"/>
                                      </c:url>'>
                              <font color="black"><B>&lt;&lt; Prev</B></font>
                           </a>
                        </c:if>
                     </td><td/><td/><td/><td/>
                     <td style="text-align: right;">
                        <c:if test="${!groupBuyingList.lastPage}">
                           <a href='<c:url value="/shop/groupBuying/listItem2.do">/>
                                        <c:param name="pageName" value="next"/>
                                        <c:param name="productId" value="${productId}"/>
                                     </c:url>'>
                              <font color="black"><B>Next &gt;&gt;</B></font>
                           </a>
                        </c:if>
                     </td>
               </tr>
                  
                  </table>
               </div>
            </div>
         </div>
      </div>
      <br><br>
      
      
   </form>
  
</body>
</html>