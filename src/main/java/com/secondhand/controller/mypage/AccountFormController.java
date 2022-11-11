package com.secondhand.controller.mypage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;
import com.secondhand.domain.Account;
import com.secondhand.service.AccountFormValidator;
import com.secondhand.service.facade.SecondhandFacade;


@Controller
@RequestMapping({"/shop/newAccount.do","/shop/editAccount.do"})
public class AccountFormController { 

	@Value("account/EditAccountForm")
	private String formViewName;
	
	@Value("index")
	private String successViewName;
	
	@Autowired
	private SecondhandFacade secondhand;
	public void setsecondhand(SecondhandFacade secondhand) {
		this.secondhand = secondhand;
	}

	@Autowired
	private AccountFormValidator validator;
	public void setValidator(AccountFormValidator validator) {
		this.validator = validator;
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
	public String showForm() {
		return formViewName;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(
			HttpServletRequest request, HttpSession session,
			@ModelAttribute("accountForm") AccountForm accountForm,
			BindingResult result) throws Exception {

		validator.validate(accountForm, result);
		System.out.println("onSubmit");
		if (result.hasErrors())
			return formViewName;
		
		System.out.println();
		
		try {
			if (accountForm.isNewAccount()) {
				System.out.println("newAccount");
				secondhand.insertAccount(accountForm.getAccount());
			}
			else {
				System.out.println("updateAccount");
				secondhand.updateAccount(accountForm.getAccount());
			}
		}
		catch (DataIntegrityViolationException ex) {
			result.rejectValue("account.username", "USER_ID_ALREADY_EXISTS",
					"User ID already exists: choose a different ID.");
			return formViewName; 
		}
		
		UserSession userSession = new UserSession(accountForm.getAccount());
		secondhand.getAccountByUserId(accountForm.getAccount().getUserId());
		session.setAttribute("userSession", userSession);
		return successViewName;  
	}
}
