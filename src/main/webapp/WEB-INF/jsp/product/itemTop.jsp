<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
  <title>Secondhand</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="2020-01 소프트웨어 시스템 개발 ">
  <meta name="author" content="愛+">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/secondhand.css?after" type="text/css" />
  
    <!-- Bootstrap core JavaScript -->
  <script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Plugin JavaScript -->
  <script src="${pageContext.request.contextPath}/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom JavaScript for this theme -->
  <script src="${pageContext.request.contextPath}/js/scrolling-nav.js"></script>

  <!-- Bootstrap core CSS -->
  <link href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="${pageContext.request.contextPath}/css/scrolling-nav.css" rel="stylesheet">
</head>
 
  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-white" id="mainNav">
     <a class="navbar-brand js-scroll-trigger" href="<c:url value="/shop/item/indexFromLogo.do"/>">
     	<img border="0" src="${pageContext.request.contextPath}/images/hand3.png" style="float:left;width:80;height:60;border:0;"/>
     	<img border="0" src="${pageContext.request.contextPath}/images/logo.png" style="float:left;width:120;height:60;border:0;" />
     </a>
    <div class="container">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
        <li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle js-scroll-trigger" href="#" id="navbarDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			  	<font color="black"> SEARCH &nbsp; ▼</font>
			</a>
      	  	<div class="dropdown-menu dropdown-menu-right"
						aria-labelledby="navbarDropdown">
						<form name="searchForm"
							action="<c:url value='/shop/search/viewItem.do'/>">
							<input type="radio" name="sKind" value="title">제목&nbsp; 
							<input type="radio" name="sKind" value="tag" checked="checked">태그 <br> 
							<input type="text" id="word" name="word" placeholder="검색어" />
							<button class="btn btn-light dropdown-item"
								onclick="search(word.value)">Click To Search!</button>
						</form>
					</div></li>
			
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="<c:url value="/shop/viewCart.do"/>"><font color="black">CART</font></a>
          </li>
          <c:if test="${empty userSession.account}" >
	          <li class="nav-item">
	            <a class="nav-link js-scroll-trigger" href="<c:url value="/shop/signonForm.do"/>"><font color="black">LOGIN</font></a>
	          </li>
          </c:if>
      	  <c:if test="${!empty userSession.account}" >
	      <li class="nav-item">
	        <a class="nav-link js-scroll-trigger" href="<c:url value="/shop/editAccount.do"/>"><font color="black">MYPAGE</font></a>
	      </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="<c:url value="/shop/signoff.do"/>"><font color="black">LOGOUT</font></a>
          </li>
          </c:if>
<!--           <li> -->
<%--               <form action="<c:url value="/shop/searchProducts.do"/>" method="post"> --%>
<!-- 			    <input type="hidden" name="search" value="true"/> -->
<!-- 		        <input type="text" name="keyword" size="14" />&nbsp; -->
<!-- 		        <input src="${pageContext.request.contextPath}/images/search.gif" type="image"/> -->
<%-- 		      </form> --%>
<!--           </li> -->
        </ul>
      </div>
    </div>
  </nav>
    <script>
function search(word) {
	alert("검색어 : " + word);

	searchForm.submit();	
}
</script>
