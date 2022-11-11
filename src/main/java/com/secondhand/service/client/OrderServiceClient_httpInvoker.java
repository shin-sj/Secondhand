package com.secondhand.service.client;

import java.util.Iterator;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StopWatch;
import com.secondhand.domain.LineItem;
import com.secondhand.domain.Orders;
import com.secondhand.service.OrderService;



public class OrderServiceClient_httpInvoker {

	private static final String CLIENT_CONTEXT_CONFIG_LOCATION = "client/clientContext.xml";
	private static final String orderServiceBeanName = "httpOrderService";
	
	private final ListableBeanFactory beanFactory;
	public OrderServiceClient_httpInvoker(ListableBeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	public void invokeOrderServices(int orderId) {
		OrderService orderService = (OrderService) beanFactory.getBean(orderServiceBeanName);
		StopWatch stopWatch = new StopWatch(orderServiceBeanName + " call");
		System.out.println("Calling httpOrderService with order ID " + orderId);
		stopWatch.start(orderServiceBeanName);
		Orders order = orderService.getOrder(orderId);
		stopWatch.stop();
		if (order != null) {
			printOrder(order);
		}
		else {
			System.out.println("Order with ID " + orderId + " not found");
		}
		System.out.println();
		System.out.println(stopWatch.prettyPrint());
	}

	protected void printOrder(Orders order) {
		System.out.println("Got order with order ID " + order.getOrderId() +
				" and order date " + order.getOrderDate());
		System.out.println("Shipping address is: " + order.getShipAddr1());
		for (Iterator<LineItem> lineItems = order.getLineItems().iterator(); lineItems.hasNext();) {
			LineItem lineItem = (LineItem) lineItems.next();
			System.out.println("LineItem " + lineItem.getLineNum() + ": " + lineItem.getQuantity() +
					" piece(s) of item " + lineItem.getItemId());
		}
	}

	public static void main(String[] args) {
		if (args.length == 0 || "".equals(args[0])) {
			System.out.println(
				"You need to specify an order ID, e.g. for order ID 1000: 'client 1000'");
		}
		else {
			int orderId = Integer.parseInt(args[0]);
			ListableBeanFactory beanFactory = (ListableBeanFactory) new ClassPathXmlApplicationContext(CLIENT_CONTEXT_CONFIG_LOCATION);
			OrderServiceClient_httpInvoker client = new OrderServiceClient_httpInvoker(beanFactory);
			client.invokeOrderServices(orderId); 
		}
	}
}