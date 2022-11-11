<%@ include file="../IncludeTop.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
	function getOrder(orderId) {
		var reqUrl = "../rest/order/" + orderId;
		$.ajax({
			type: "get",
			url: reqUrl,
			processData: false,
			success: function(responseJson){	// object parsed from JSON text	
				$("#detail").html("<ul></ul>");
				$("#detail > ul").append("<li>Order ID: " + responseJson.orderId + "</li>");
				$("#detail > ul").append("<li>Order Date: " + new Date(responseJson.orderDate) + "</li>");
				$("#detail > ul").append("<li>User name: " + responseJson.userId + "</li>");
				$("#detail > ul").append("<li>Shipping address: " + responseJson.shipAddr1 + ", " + 
					responseJson.shipAddr2 + ", " + responseJson.shipCity + "</li>");
				var content = "";
				$(responseJson.lineItems).each(function(i, lineItem){	        	
			       	content += "LineItem " + lineItem.lineNum + ": " + lineItem.quantity +
							" piece(s) of item " + lineItem.itemId + "<br>";
				});
				$("#detail > ul").append ("<li>" + content + "</li>");
				$("#detail > ul").append("<li>Total prices: " + responseJson.totalPrice + "</li>");
			},
			error: function(){
				alert("ERROR", arguments);
			}
		});
	};
</script>
<div align="center" width="500">
  <p>
    <h2>My Order</h2>
  </p>
 
  <!-- REST API 내용 부분 -->
  <table>
	<tr>
		<td>
			<div id="detail"></div>
		</td>
	</tr>
  </table>

  <table class="table table-hover">
    <tr bgcolor="#CCCCCC">
      <th scope="col">Order ID</th> 
      <th scope="col">Date</th> 
      <th scope="col">Total Price</th>
    </tr>
    <c:forEach var="order" items="${orderList}">
      <tr bgcolor="#FFFFFF">
        <td scope="row">
          <b><a href='<c:url value="/shop/viewOrder.do">
              <c:param name="orderId" value="${order.orderId}"/></c:url>'>
              <font color="black"><c:out value="${order.orderId}" /></font>
            </a></b></td>
        <td scope="row"><fmt:formatDate value="${order.orderDate}"
            pattern="yyyy/MM/dd hh:mm:ss" /></td>
        <td scope="row" onClick="getOrder(${order.orderId});"><fmt:formatNumber value="${order.totalPrice}"
            pattern="###,###,###원" /></td>
      </tr>
    </c:forEach>
  </table>
</div>
<br><br>
<%@ include file="../IncludeBottom.jsp"%>