<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../IncludeTop.jsp"%>

  <div class="container">
    <div class="row">
      <div class="col-lg-10 col-xl-9 mx-auto">
        <div class="card card-signin flex-row my-5">
          <div class="card-img-left d-none d-md-flex">
             <!-- Background image for card set in CSS! -->
          </div>
          <div class="card-body">
            <h5 class="card-title text-center">My Account</h5>
            <form:form class="form-signin" modelAttribute="accountForm" method="post">
 			<form:errors cssClass="error" /> 			
              <div class="form-label-group">
	            <form:input path="account.userId" class="form-control" disabled="true"/>
                <label for="inputUserame">USER ID</label> 
              </div>
              
			  <hr class="my-4">
			  <div class="form-label-group">
				<form:input path="account.username" id="inputName" class="form-control" disabled="true" />
				<B><form:errors path="account.username" cssClass="error" /></B> <label for="inputName">Name</label>
			  </div>

				<div class="form-label-group">
					<form:input path="account.email" id="inputEmail" class="form-control" disabled="true" />
					<B><form:errors path="account.email" cssClass="error" /></B> <label for="inputEmail">E-Mail</label>
				</div>
				
				<div class="form-label-group">
					<form:input path="account.phone" id="inputPhone" class="form-control" disabled="true" />
					<B><form:errors path="account.phone" cssClass="error" /></B> <label for="inputPhone">Phone</label>
				</div>

				
				<div class="form-label-group">
					<form:input path="account.city" id="inputCity" class="form-control" disabled="true"/>
					<B><form:errors path="account.city" cssClass="error" /></B> <label for="inputCity">city</label>
				</div>
				
				<div class="form-label-group">
					<form:input path="account.addr1" id="inputaddr1" class="form-control" disabled="true"/>
					<B><form:errors path="account.addr1" cssClass="error" /></B> <label for="inputaddr1">Address 1</label>
				</div>
				
				<div class="form-label-group">
					<form:input path="account.addr2" id="inputaddr2" class="form-control" disabled="true"/>
					<B><form:errors path="account.addr2" cssClass="error" /></B> <label for="inputaddr2">Address 2</label>
				</div>
				
				<div class="form-label-group">
					<form:input path="account.zip" id="inputzip" class="form-control" disabled="true"/>
					<B><form:errors path="account.zip" cssClass="error" /></B> <label for="inputzip">Zipcode</label>
				</div>
				
				<div class="form-label-group">
					<form:input path="account.country" id="inputCountry" class="form-control" disabled="true"/>
					<B><form:errors path="account.country" cssClass="error" /></B> <label for="inputCountry">Country</label>
				</div>
				
              <button class="btn btn-lg btn-light btn-block text-uppercase" type="submit">Edit Account</button>
             </form:form>
            
             <!-- 내가 신고한 사람들 보여주기  -->

		  </div>
        </div>
      </div>
    </div>
  </div>
  <%@ include file="../IncludeBottom.jsp" %>
</body>