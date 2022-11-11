<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../IncludeTop.jsp"%>
<c:if test="${!empty message}">
	<div class="alert alert-success" role="alert">
	  <font color="#FF4500"><c:url value="${message}" /></font>
  	</div>
</c:if>
  <div class="container">
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
            <h5 class="card-title text-center">Sign In</h5>
            <h6 class="card-title text-center">Please enter your ID and password.</h6>
              <form action='<c:url value="/shop/signon.do"/>' method="POST">
				  <c:if test="${!empty signonForwardAction}">
				    <input type="hidden" name="forwardAction" value='<c:url value="${signonForwardAction}"/>' />
				  </c:if>
	              <div class="form-label-group">
	                <input type="text" id="inputUserId" name="userId" class="form-control" placeholder="ID" required autofocus>
	                <label for="inputUserId">Your ID</label>
	              </div>
	              <div class="form-label-group">
	                <input type="password" name="password" id="inputPassword" class="form-control" placeholder="PASSWORD" required>
	                <label for="inputPassword">Your Password</label>
	              </div>
	              <div class="form-label-group">
	                <button class="btn btn-lg btn-warning btn-block text-uppercase" type="submit">Sign in</button>
	              </div>
	              <hr class="my-4">
	              <div class="form-label-group">
	              	<a href='<c:url value="/shop/newAccount.do"/>' class="btn btn-lg btn-warning btn-block text-uppercase">
		              		New Account
	              	</a>
	              </div>
			 </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>