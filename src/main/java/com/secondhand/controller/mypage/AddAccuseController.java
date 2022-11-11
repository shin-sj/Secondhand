package com.secondhand.controller.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.secondhand.domain.Accuse;
import com.secondhand.service.facade.MyPageFacade;

@Controller
public class AddAccuseController {

   private MyPageFacade myPageFacade;
   
   @Autowired
   public void setMyPageFacade(MyPageFacade myPageFacade) {
      this.myPageFacade = myPageFacade;
   }

   @RequestMapping("/addAccuse.do")  //로그인이 정상적으로 되어있을 때만 들어오는 컨트롤러
   public String addAccuse(@RequestParam(value="userId") String userId,
         @RequestParam(value="itemId") int itemId, 
         @RequestParam(value="productId") int productId,
         @RequestParam(value="reason") String reason, HttpServletRequest request, Model model) {
      //신고하고자 하는 판매자 아이디를 파라미터로 받는다.   
        System.out.println("addAccuse 컨트롤러 진입\n");
        System.out.println("이유: " + reason);
        
        HttpSession session =  request.getSession(false);
 	   	UserSession userSession = (UserSession) session.getAttribute("userSession");
 	    String victim = userSession.getAccount().getUserId();  
 	    	
 	    if(!reason.equals("") && reason != null && !reason.equals("null")) {
 	    	if(victim.equals(userId)) {
 	    		System.err.println("자신을 신고할 수 없습니다.");
 	    	}else {
	           Accuse accuse = new Accuse(); 
	           accuse.setAttacker(userId);  // 판매자가 공격자이다. 
	           accuse.setReason(reason); //자바스크립트에서 넘어온 신고사유
	           accuse.setVictim(victim);  // 세션: 로그인아이디
	           
	           this.myPageFacade.insertAccuse(accuse);
 	    	}
 	   }

        model.addAttribute("itemId", itemId);
        model.addAttribute("productId", productId);
      
        if(productId == 0)
           return "redirect:/shop/groupBuying/viewItem.do?itemId=" + itemId + "&productId=" + productId;
        else if(productId ==1)
           return "redirect:/shop/auction/viewItem.do?itemId=" + itemId + "&productId=" + productId;
        else if(productId ==2)
           return "redirect:/shop/secondHand/viewItem.do?itemId=" + itemId + "&productId=" + productId;
        else
           return "/index";
  
   }
   
   
   @RequestMapping("/addAccuseNoLogin.do")  //로그인이 안되었는데 판매자 신고 눌렀을때 
   public String addAccuseNoLogin( 
		   	@RequestParam(value="itemId") int itemId, 
	         @RequestParam(value="productId") int productId,HttpServletRequest rq){
	   
	   HttpSession session = rq.getSession(false);
	   
	   session.setAttribute("accuseItemId", itemId);
	   session.setAttribute("accuseProductId", productId);
	   return "redirect:shop/signonForm.do";
   }
   
   
}