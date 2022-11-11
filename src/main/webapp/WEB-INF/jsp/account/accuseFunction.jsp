<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<script type="text/javascript">
function accuse(a){   //여기는 로그인이 되었을 때 보내주는 페이지 
  	  var reason = prompt('판매자 신고하기\n신고사유를 작성하세요.', '');
  	  a.href += "&reason=" + reason;
	  if(reason){ alert("신고되었습니다.") }
	  else if(reason == ''){ alert("이유를 작성하지 않아 취소됩니다."); }
	  else{ alert("취소되었습니다"); }
}
</script>
<span>
      <c:choose>
               <c:when test="${isAccuse}">
                        신고한 판매자입니다
               </c:when>
               <c:otherwise>
                  <c:choose> 
                     <c:when test="${productId==0}">
                        <a href="<c:url value='/addAccuse.do'>
                        <c:param name="userId" value="${gb.userId}"/>
                         <c:param name="itemId" value="${gb.itemId}"/>
                         <c:param name="productId" value="${gb.productId}"/></c:url>"
                        onClick = "accuse(this);">판매자 신고하기</a>
                     </c:when>
                     <c:when test="${auction.productId==1}">
                        <a href="<c:url value='/addAccuse.do'>
                        <c:param name="userId" value="${auction.userId}"/>
                         <c:param name="itemId" value="${auction.itemId}"/>
                         <c:param name="productId" value="${auction.productId}"/></c:url>"
                        onClick = "accuse(this);">판매자 신고하기</a>
                     </c:when>
                     <c:when test="${sh.productId==2}">
                        <a href="<c:url value='/addAccuse.do'>
                        <c:param name="userId" value="${sh.userId}"/>
                         <c:param name="itemId" value="${sh.itemId}"/>
                         <c:param name="productId" value="${sh.productId}"/></c:url>"
                        onClick = "accuse(this);">판매자 신고하기</a>
                     </c:when>
                     <c:when test="${productId==3}">
                        <a href="<c:url value='/addAccuse.do'>
                        <c:param name="userId" value="${handMade.userId}"/>
                         <c:param name="itemId" value="${handMade.itemId}"/>
                         <c:param name="productId" value="${handMade.productId}"/></c:url>"
                        onClick = "accuse(this);">판매자 신고하기</a>
                     </c:when>
                  </c:choose>
               </c:otherwise>
      </c:choose>
</span>