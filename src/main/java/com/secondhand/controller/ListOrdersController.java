package com.secondhand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.secondhand.controller.mypage.UserSession;
import com.secondhand.service.facade.SecondhandFacade;


@Controller
@SessionAttributes("userSession")
public class ListOrdersController {

	private SecondhandFacade secondhandFacade;

	@Autowired
	public void setPetStore(SecondhandFacade secondhandFacade) {
		this.secondhandFacade = secondhandFacade;
	}

	@RequestMapping("/shop/listOrders.do")
	public ModelAndView handleRequest(
		@ModelAttribute("userSession") UserSession userSession
		) throws Exception {
		String userId = userSession.getAccount().getUserId();
		return new ModelAndView("order/ListOrders", "orderList", 
				secondhandFacade.getOrdersByUserId(userId));
	}

}
