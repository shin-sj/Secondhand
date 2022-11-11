package com.secondhand.controller.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.secondhand.service.ItemFormValidator;

@Controller
public class ItemFormController {
   
   @ModelAttribute("item")        
      public ItemForm formBacking(HttpServletRequest rq, @RequestParam("productId") int productId) {  // accessor method 
            HttpSession session = rq.getSession(false);
            ItemForm itemForm;
            if((ItemForm) session.getAttribute("itemForm") != null) {	//수정을 통해서 addItem폼에 들어온 경우
               ItemForm itemFormSession = (ItemForm) session.getAttribute("itemForm");
               System.out.println(itemFormSession);
               
               return itemFormSession;	//기존의 값으로 초기화
            }
            else {
               itemForm = new ItemForm();
               itemForm.setProductId(autoDetectPid(rq.getRemoteAddr(), productId));   // itemForm.productId 초기화
               return itemForm;
            }
      }
   
   private int autoDetectPid(String remoteAddr, int productId) {   // itemForm.productId 초기화
      return productId;
   }
   
   @RequestMapping(value="/shop/item/addItem.do", method = RequestMethod.GET)      //item.jsp 이동
   public String step1(@ModelAttribute("item") ItemForm itemForm, @RequestParam("productId") int productId, 
		   Model model) {
    
	   model.addAttribute("productId", productId);      // [productId] to item.jsp 전달
	   return "product/item";   //form view(item.jsp)
   }
   
   @RequestMapping(value="/shop/item/addItem2.do", method = RequestMethod.POST)   // 각 판매에서 상품 추가 두번째 폼으로 이동
   public String submit(@ModelAttribute("item") ItemForm itemForm, BindingResult bindingResult, 
         @RequestParam("productId") int productId, HttpServletRequest rq, Model model) {
      
      new ItemFormValidator().validate(itemForm, bindingResult);
      
      String itemController = "";
      HttpSession httpSession = rq.getSession(true); 
            
      httpSession.setAttribute("itemForm", itemForm);   //generate item session
      
      if (bindingResult.hasErrors()) {   //유효성 검증 에러 발생시
         model.addAttribute("productId", productId);
         return "product/item";
      }
      
      switch(productId) {
         case 0 : 
            itemController = "redirect:/shop/groupBuying/addItem2.do?productId=" + productId;
            break;
         case 1 :
            itemController = "redirect:/shop/auction/addItem2.do?productId=" + productId;
            break;
         case 2 : 
            itemController = "redirect:/shop/secondHand/addItem2.do?productId=" + productId;
            break;
      }
   
      return itemController;
   }
   
   @RequestMapping("/shop/item/index.do") //go index(remove sessions)
   public String goIndex(@RequestParam("productId") int productId)
   {
      String url = "";
      
      switch(productId) {
         case 0 : 
               url = "redirect:/shop/groupBuying/index.do";
               break;
         case 1 :
               url = "redirect:/shop/auction/index.do";
               break;
         case 2 : 
               url = "redirect:/shop/secondHand/index.do";
               break;
      }
      
      return url;
   }
   
}