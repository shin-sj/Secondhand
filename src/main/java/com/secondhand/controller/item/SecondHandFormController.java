package com.secondhand.controller.item;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.secondhand.controller.mypage.UserSession;
import com.secondhand.domain.Item;
import com.secondhand.domain.SecondHand;
import com.secondhand.domain.Tag;
import com.secondhand.service.SecondHandFormValidator;
import com.secondhand.service.facade.ItemFacade;

@Controller /* SecondHandForm 커맨드 객체를 관리하는 컨트롤러 */
@SessionAttributes("secondHandForm")
public class SecondHandFormController {
   
   private ItemFacade itemFacade;
   
   @Autowired
   public void setItemFacade(ItemFacade itemFacade) {
      this.itemFacade = itemFacade;
   }

   @ModelAttribute("secondHandForm")
   public SecondHandForm formBacking() { //accessor method
	   SecondHandForm secondHandForm = new SecondHandForm();
	   //필드 초기화 할것 있으면 여기서 하기 
	   return secondHandForm;
   }
   
   @ModelAttribute("radioKind")
   public String[] referenceData() {
	   return new String[] {"Yes", "No"};
   }
   

   @RequestMapping("/shop/secondHand/index.do") //go index(remove sessions)
   public String goIndex(SessionStatus sessionStatus, HttpServletRequest rq)
   {
      HttpSession session = rq.getSession(false);
      
      sessionStatus.setComplete();// secondHand session close
      session.removeAttribute("itemForm");   //itemForm session close
      session.removeAttribute("status");      //edit flag Session close
      
      return "redirect:/shop/index.do";
   }
   
   @GetMapping("/shop/secondHand/addItem2.do")
   public String step2(@ModelAttribute("secondHandForm") SecondHandForm secondHandForm, 
            @RequestParam("productId") int productId, Model model, HttpServletRequest rq) {
	   	
		HttpSession session = rq.getSession(false);
		if(session.getAttribute("status") != null) {
			int status = (int) session.getAttribute("status");
			SecondHand sh = itemFacade.getSecondHandItem(status);
			secondHandForm.setListPrice(sh.getListPrice());
		
			if(sh.getDiscount() == 0) { //흥정 불가능 
				secondHandForm.setDiscount("No");
			}
		}
        model.addAttribute("productId", productId);
        return  "product/addSecondHand";  
   }
   
   @PostMapping("/shop/secondHand/step3.do")      // step2 -> step3
   public String goCheck(
		   @ModelAttribute("secondHandForm") SecondHandForm secondHandForm, BindingResult result,
		   Model model, HttpServletRequest rq) {   
	   
	    HttpSession session = rq.getSession(false);
	    new SecondHandFormValidator().validate(secondHandForm, result);
	    ItemForm itemForm = (ItemForm)session.getAttribute("itemForm");
	    
		if(secondHandForm.getListPrice() <= itemForm.getUnitCost()) {
			result.rejectValue("listPrice", "mustBig");
		}
		
		if (result.hasErrors()) {	//유효성 검증 에러 발생시
			model.addAttribute("productId", itemForm.getProductId());
			return "product/addSecondHand";
		}
	   
	   model.addAttribute("tags", itemForm.getTags());
	   return "product/checkSecondHand";      // step3(CHECK_FORM3)
   }
   
   @GetMapping("/shop/secondHand/gobackItem.do")      // item.jsp
   public String backToStep1(@ModelAttribute("secondHandForm") SecondHandForm secondHandForm, 
			@RequestParam("productId") int productId) {
      return "redirect:/shop/item/addItem.do?productId="+productId; // step1 form view(item.jsp)
   }
   
	@PostMapping("/shop/secondHand/done.do")		// step3 -> done
	public String done(@ModelAttribute("secondHandForm") SecondHandForm secondHandForm,
			BindingResult result, Model model, HttpServletRequest rq, 
			SessionStatus sessionStatus) {
		
		int status = 0;
		
		HttpSession session = rq.getSession(false);
		ItemForm itemform = (ItemForm) session.getAttribute("itemForm");
		if(session.getAttribute("status") != null) {
			status = (int) session.getAttribute("status");
		}else {
			System.out.println("status가 null일 때 ");
		}
		
		UserSession userSession = (UserSession)session.getAttribute("userSession");
		String suppId = userSession.getAccount().getUserId();
		System.out.println("done-suppId : " + suppId );	
	
		//put itemform to item domain 세션에 저장된 커맨드객체를 도메인에 저장하기 
		Item item = new Item(itemform.getUnitCost(), itemform.getTitle(), 
				itemform.getDescription(), itemform.getQty(), suppId, itemform.getProductId());
		
		if(status != 0) { // 수정할 때 
			item.setItemId(status);
			item.setViewCount(itemform.getViewCount());
			itemFacade.updateItem(item);
	
			List<Tag> tags = itemFacade.getTagByItemId(status);
			System.out.println("tag size : " + tags.size());	//0
			
			if(tags.size() > 0) {
				itemFacade.deleteTag(status);
				tags.removeAll(tags);
			}
		}
		//generate tags(only have tagName)      
	    for(int i = 0; i < itemform.getTags().size(); i++) {
	         item.makeTags(itemform.getTags().get(i));   //if(tag != null && "") then addTags
	    }
	    
		//put secondHandForm to SecondHand domain 세션에 있는거 도메인에 저장 
		String discount = secondHandForm.getDiscount();
		SecondHand secondHand = null;
		if(discount.equals("Yes")) {
			secondHand = new SecondHand(item, 1 ,secondHandForm.getListPrice());
		}else if(discount.equals("No")){
			secondHand = new SecondHand(item, 0 ,secondHandForm.getListPrice());
		}
		
		if(status != 0) { //수정일 때 
			itemFacade.updateSecondHand(secondHand);
		}else {  // 처음 디비에 저장할 떄 ,, 트랜젝션으로 묶음
			itemFacade.insertSecondHand(secondHand);	// insert DB 세개의 다른 도메인들을 가져옴 근데 아이템은 세컨핸드가 상속해서 따로 안가져와도댐 
		}
	
		sessionStatus.setComplete();	// secondHand, editStatus session close
		session.removeAttribute("itemForm");	//itemForm session close
		session.removeAttribute("status");
		
		return "redirect:/shop/secondHand/viewItem.do?itemId=" + secondHand.getItemId() + "&productId=" + item.getProductId();
	} 
	
	@RequestMapping("/shop/secondHand/edit.do") //edit Item
	public String editItem(@RequestParam("itemId") int itemId, HttpServletRequest rq)
	{
		HttpSession session = rq.getSession(true);
	
		//itemId로 디비에서 세컨핸드와 태그 가져와서 도메인에 저장함 
		SecondHand sh = itemFacade.getSecondHandItem(itemId);
	
		ItemForm itemForm = new ItemForm();
		itemForm.setItemId(itemId);
		itemForm.setUnitCost(sh.getUnitCost());
		itemForm.setTitle(sh.getTitle());
		itemForm.setDescription(sh.getDescription());
		itemForm.setViewCount(sh.getViewCount());
		itemForm.setQty(sh.getQty());
		itemForm.setUserId(sh.getUserId());
		itemForm.setProductId(sh.getProductId());
		itemForm.setTags(sh.getTags());
		  
		session.setAttribute("status", itemId); 
		session.setAttribute("itemForm", itemForm); 
		
		return "redirect:/shop/item/addItem.do?productId=" + sh.getProductId();
	}
	
   
}