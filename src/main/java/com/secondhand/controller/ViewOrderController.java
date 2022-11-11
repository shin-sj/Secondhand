package com.secondhand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.secondhand.controller.mypage.UserSession;
import com.secondhand.domain.Orders;
import com.secondhand.service.OrderService;
import com.secondhand.service.facade.SecondhandFacade;


@Controller
@SessionAttributes("userSession")
public class ViewOrderController {

	private SecondhandFacade secondhandFacade;
	
	@Autowired
	public void setsecondhand(SecondhandFacade secondhandFacade) {
		this.secondhandFacade = secondhandFacade;
	}

	@RequestMapping("/shop/viewOrder.do")
	public ModelAndView handleRequest(
			@ModelAttribute("userSession") UserSession userSession,
			@RequestParam("orderId") int orderId
			) throws Exception {
		
		Orders order = secondhandFacade.getOrder(orderId);
		if (userSession.getAccount().getUsername().equals(order.getShipToUsername())) {
			System.out.println("account username : " + userSession.getAccount().getUsername());
			System.out.println("order shipToUsername : " + order.getShipToUsername());
			return new ModelAndView("order/ViewOrder", "order", order);
		}
		else {
			System.out.println("account username : " + userSession.getAccount().getUsername());
			System.out.println("Order : " + order.toString());
			System.out.println("order shipToUsername : " + order.getShipToUsername());
			return new ModelAndView("Error", "message", "You may only view your own orders.");
		}
	}
	
	@RequestMapping("/shop/order/viewItem.do") // go index(remove sessions)
	public String goIndex(@RequestParam("itemId") int itemId, @RequestParam("productId") int productId) {
		String viewItem = "";

		switch (productId) {
		case 0:
			viewItem = "redirect:/shop/groupBuying/viewItem.do";
			break;
		case 1:
			viewItem = "redirect:/shop/auction/viewItem.do";
			break;
		case 2:
			viewItem = "redirect:/shop/secondHand/viewItem.do";
			break;
			}

		return viewItem + "?itemId=" + itemId + "&productId=" + productId;
	}
}
