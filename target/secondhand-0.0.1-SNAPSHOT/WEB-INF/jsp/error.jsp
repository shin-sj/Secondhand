<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="IncludeTop.jsp" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<title>Error</title>
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
                  <h2>
                     <font color=#FF4500>Error!</font>
                  </h2>
                  <hr>
                  <b><c:out value="${message}" default="No further information was provided."/></b>
               </div>
            </div>
         </div>
      </div>
</form>
</body>
</html>