package com.secondhand.controller.mypage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;


import com.secondhand.service.facade.MyPageFacade;
import com.secondhand.service.facade.SecondhandFacade;

@Controller
@SessionAttributes("userSession")
@RequestMapping("/shop/viewAccount.do")
public class ViewAccountController {

	@Autowired
	private SecondhandFacade secondhand;
	public void setsecondhand(SecondhandFacade secondhand) {
		this.secondhand = secondhand;
	}
	
	private MyPageFacade myPageFacade;
	
	@Autowired
	public void setMyPageFacade(MyPageFacade myPageFacade) {
		this.myPageFacade = myPageFacade;
	}

	@ModelAttribute("accountForm")
	public AccountForm formBackingObject(HttpServletRequest request) 
			throws Exception {
		System.out.println("formBackingObject");
		UserSession userSession = 
			(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		if (userSession != null) {	// edit an existing account
			return new AccountForm(
					secondhand.getAccountByUserId(userSession.getAccount().getUserId()));
		}
		else {	// create a new account
			return new AccountForm();
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showForm(Model model, HttpServletRequest rq) {
		//select from accuse 
		HttpSession session = rq.getSession(false);
		UserSession userSession = (UserSession)session.getAttribute("userSession");
		String victim = userSession.getAccount().getUserId();

		return "account/mypage";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String goEdit() {
		return "redirect:/shop/editAccount.do";
	}
}
