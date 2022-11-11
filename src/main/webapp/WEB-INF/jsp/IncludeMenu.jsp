<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
  <div class="d-flex" id="wrapper">

    <!-- Sidebar -->
    <div class="bg-white border-light" id="sidebar-wrapper">
      <div class="sidebar-heading">
      	<c:if test="${!empty userSession.account}">
		<b><i><font size="4" color="RED">Welcome ${userSession.account.username}!</font></i></b>
        </c:if>
      </div>
      <div class="list-group list-group-flush">
        <a href="<c:url value="/shop/secondHand/listItem.do?productId=2"/>" class="list-group-item list-group-item-action bg-light">중고거래</a>
        <a href="<c:url value="/shop/auction/listItem.do?productId=1"/>" class="list-group-item list-group-item-action bg-light">경매</a>
        <a href="<c:url value="/shop/groupBuying/listItem.do?productId=0"/>" class="list-group-item list-group-item-action bg-light">공동구매</a>
		
      </div>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
      <div class="container-fluid" align="center">
      	<!-- MAIN IMAGE -->
        <img src="../images/logo.png" style="float:center;width:800;height:450;border:0;" />
       </div>
    </div>
    <!-- /#page-content-wrapper -->
  </div>
  <!-- /#wrapper -->

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Menu Toggle Script -->
  <script>
    $("#menu-toggle").click(function(e) {
      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });
  </script>