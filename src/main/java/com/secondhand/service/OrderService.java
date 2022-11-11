package com.secondhand.service;

import java.util.List;

import com.secondhand.domain.Orders;
import com.secondhand.service.facade.SecondhandFacade;
import com.secondhand.service.impl.OrderServiceImpl;
import com.secondhand.service.impl.SecondhandImpl;

/**
 * Separate OrderService interface, implemented by OrderServiceImpl. * 
 *
 * <p>Mainly targeted at usage as remote service interface,
 * just exposing the <code>getOrder</code> method.
 *
 * @author Juergen Hoeller
 * @since 26.12.2003
 * @see SecondhandFacade
 * @see SecondhandImpl
 * @see OrderServiceImpl
 */
//@WebService(name = "OrderService") 
public interface OrderService {

	Orders getOrder(int orderId);
	
	public List<Orders> getOrdersByUserId(String userId);

}
