<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="itemTop.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<title>검색 결과 페이지</title>
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
<form name = "form" action="" style="position:absolute; left:50%; margin:0 0 0 -430px;">
      <div class="container" >
         <div class="row"  style="display:inline">
            <div style="display:inline;float:left;width:800px">
               <div style="font-size:15px">
               	  <c:set var="kind" value="${sKind}"/>
               	  <c:if test="${'title' eq kind}">
					<h2>
                     Search Title <font color=#008080>'<c:out value="${searchWord}"/>'</font>
                  </h2>
				  </c:if>
                  <c:if test="${'tag' eq kind}">
					<h2>
                     Search Tag <font color=#008080>#<c:out value="${searchWord}"/></font>
                  </h2>
				  </c:if>
                  <hr>
                  <c:set var="no" value="${noResult}"/>
                  <c:if test="${no == 1}">
                  	<c:out value="검색 결과가 없습니다."/>
                  </c:if>
                  <c:if test="${empty no}">
                  <table>
                     <tr>
                        <th style="padding-left:40px">제목</th>
                        <th style="padding-left:90px">내용</th>
                        <th style="padding-left:130px">분류</th>
                     </tr>
                  <tbody>   
                  <c:forEach var="item" items="${resultList.pageList}" varStatus="status">         
                  <tr style="height:70px;">
                  <td style="padding-left:40px">
				  	<a href="<c:url value='/shop/search/selectItem.do'>
                    <c:param name="itemId" value="${item.itemId}"/>
                    </c:url>">
                    	<font><c:out value="${item.title}"/></font>
                    </a>
                  </td>
                  <td style="padding-left:90px">
                   	<c:out value="${item.description}"/><br>
                  </td>
				  <td style="padding-left:120px">
				  <c:set var="productId" value="${item.productId}"/>
				  <c:if test="${productId eq 0}">
					<c:out value="(공동구매)"/>
				  </c:if>
				  <c:if test="${productId eq 1}">
					<c:out value="(경매)"/>
				  </c:if>
				  <c:if test="${productId eq 2}">
					<c:out value="(중고거래)"/>
				  </c:if>
				  <c:if test="${productId eq 3}">
					<c:out value="(수공예)"/>
				  </c:if>
				  </td>   
                  </tr>
                  </c:forEach>
                  </tbody>
                  </table>
                  </c:if>
                  <c:if test="${!resultList.firstPage}">
									<a href='<c:url value="/shop/search/viewItem2.do">
	           								<c:param name="pageName" value="previous"/></c:url>'>
										<font color="black"><B>&lt;&lt; Prev</B></font>
									</a>
								</c:if>
								<c:if test="${!resultList.lastPage}">
									<a href='<c:url value="/shop/search/viewItem2.do">/>
	            							 <c:param name="pageName" value="next"/></c:url>'>
										<font color="black"><B>Next &gt;&gt;</B></font>
									</a>
								</c:if>
               </div>
            </div>
         </div>
      </div>
</form>                  
</body>
</html>