package com.secondhand.controller.item;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.secondhand.domain.Orders;
import com.secondhand.service.OrderService;


@Controller
public class RestfulOrderController {
	private OrderService orderSvc;

	@Autowired
	public void setPetStoreSvc(OrderService orderService) {
		this.orderSvc = orderService;
	}
	
	@RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody         
	public Orders getOrder(@PathVariable("orderId") int orderId, HttpServletResponse response)
			throws IOException {
		System.out.println("/rest/order/{orderId} request accepted: {orderId} = " + orderId);
		Orders order = orderSvc.getOrder(orderId);
		if (order == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return order;   // convert order to JSON text in response body
	}
	
	@RequestMapping(value = "/ordersBy/{userId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody         
	public List<Orders> getOrdersByUserId(@PathVariable("userId") String userId, HttpServletResponse response)
			throws IOException {
		System.out.println("/rest/order/{userId} request accepted: {userId} = " + userId);
		List<Orders> orderList = orderSvc.getOrdersByUserId(userId);
		if (orderList == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return orderList;  // convert list of orders to JSON text in response body
	}
}
