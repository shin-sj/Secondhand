package com.secondhand.controller.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.secondhand.service.facade.SecondhandFacade;


@Controller
public class RemoveItemSessionController { 

   private SecondhandFacade secondhand;

   @Autowired
   public void setsecondhand(SecondhandFacade secondhand) {
      this.secondhand = secondhand;
   }
   
   @RequestMapping("/shop/item/indexFromLogo.do") //go index(remove sessions)
   public String Index(SessionStatus sessionStatus, HttpServletRequest rq)
   {
      HttpSession session = rq.getSession(false);
         System.out.println("들어옴");
         session.removeAttribute("itemForm");   //itemForm session close
         session.removeAttribute("status");      //edit flag Session close
         session.removeAttribute("GroupBuying"); 
         session.removeAttribute("Auction"); 
         session.removeAttribute("secondHandForm"); 
         
         return "redirect:/shop/index.do";
   }
}