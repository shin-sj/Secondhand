package com.secondhand.controller.item;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.secondhand.controller.mypage.UserSession;
import com.secondhand.domain.Account;
import com.secondhand.domain.GroupBuying;
import com.secondhand.domain.Item;
import com.secondhand.domain.Tag;
import com.secondhand.service.facade.MyPageFacade;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.secondhand.service.GroupBuyingFormValidator;
import com.secondhand.service.facade.ItemFacade;

@Controller
@SessionAttributes({"GroupBuying", "groupBuyingList"})
public class GroupBuyingFormController {
   
   private static final String ADD_GroupBuying_FORM = "product/addGroupBuying";
   private static final String CHECK_FORM3 = "product/checkGroupBuying";
   private static final String DetailPage = "product/viewGroupBuying";
   
   @Autowired
   private ItemFacade itemFacade; 
   
   @Autowired
   private MyPageFacade myPageFacade;
   
   @ModelAttribute("GroupBuying")        
   public GroupBuyingForm formBacking() {  // accessor method 기본값들로 폼 초기화
      return new GroupBuyingForm();
   }
   
   @RequestMapping("/shop/groupBuying/listItem.do") 
    public String groupBuyingList(@RequestParam("productId") int productId, ModelMap modelMap, 
    		Model model,  HttpServletRequest rq) throws ParseException {
     
	  HttpSession session = rq.getSession(false);
	   
	  Account account = null;
	  if (session.getAttribute("userSession") != null) {
	       UserSession userSession = (UserSession)session.getAttribute("userSession") ; //로그인상태이면 대학정보 가져온다 
	       if (userSession != null) { 
	               account = userSession.getAccount();
	       }
	  }
	  
      PagedListHolder<GroupBuying> groupBuyingList = new PagedListHolder<GroupBuying>(this.itemFacade.getGroupBuyingList(account));
      groupBuyingList.setPageSize(4);	//페이지 넘김 처리
      
      for(GroupBuying groupBuying : itemFacade.getGroupBuyingList(account)) {	//만일 재고가 0인 상품이 있을경우, 공동구매 진행 마감
	      if(groupBuying.getQty() == 0) { itemFacade.soldOutGroupBuying(groupBuying.getItemId());  }
      }
      
      model.addAttribute("productId", productId);
      modelMap.put("groupBuyingList", groupBuyingList);
      return "product/groupBuying";
   }   
   
   @RequestMapping("/shop/groupBuying/listItem2.do")
   public String groupBuyingList2 (@ModelAttribute("groupBuyingList") PagedListHolder<GroupBuying> groupBuyingList, 
		   @RequestParam("pageName") String page, @RequestParam("productId") int productId, ModelMap modelMap, Model model) throws Exception {
	   
      if ("next".equals(page)) {	//공동구매 목록에서  next 클릭시 다음 목록 페이지로 이동
         groupBuyingList.nextPage();
      }
      else if ("previous".equals(page)) {	//공동구매 목록에서  previous 클릭시 이전 목록 페이지로 이동
         groupBuyingList.previousPage();
      }
      
      model.addAttribute("productId", productId);
      modelMap.put("groupBuyingList", groupBuyingList);
      return "product/groupBuying";
   }
   
   @RequestMapping(value="/shop/groupBuying/addItem2.do", method = RequestMethod.GET)
   public String step2(@ModelAttribute("GroupBuying") GroupBuyingForm groupBuyingForm, @RequestParam("productId") int productId, 
		   Model model, HttpServletRequest rq) {
	   
      HttpSession session = rq.getSession(false);
      if(session.getAttribute("status") != null) {   //수정일 경우
         int status = (int) session.getAttribute("status");
         int itemId = status;
         
         GroupBuying gb = itemFacade.getGroupBuyingItem(itemId);
         groupBuyingForm.setListPrice(gb.getListPrice());   //할인가 기존값으로 초기화
      }
      
      model.addAttribute("productId", productId);
      return ADD_GroupBuying_FORM;   // addGroupBuying.jsp
   }

   @RequestMapping(value="/shop/groupBuying/gobackItem.do", method = RequestMethod.GET)      // go back to item.jsp
   public String backToItem(@ModelAttribute("GroupBuying") GroupBuyingForm groupBuyingForm, 
         @RequestParam("productId") int productId) {
	   
      return "redirect:/shop/item/addItem.do?productId=" + productId;   // step1(item.jsp) form step2(addGroupBuying.jsp)
   }
   
   @PostMapping("/shop/groupBuying/step3.do")      // go checkGroupBuying.jsp
   public String goCheck(@ModelAttribute("GroupBuying") GroupBuyingForm groupBuyingForm, BindingResult result,
         HttpServletRequest rq, ItemForm itemForm, Model model, ModelMap modelMap) {   
	   
      HttpSession session = rq.getSession(false);
      new GroupBuyingFormValidator().validate(groupBuyingForm, result);
      
      if(session.getAttribute("itemForm") != null) {
         itemForm = (ItemForm) session.getAttribute("itemForm");
      }
      
      if(itemForm.getUnitCost() >= groupBuyingForm.getListPrice()) {   //판매가는 정가보다 낮은 가격이어야 함
         result.rejectValue("listPrice", "changeGb");
      }
      
      if (result.hasErrors()) {   //유효성 검증 에러 발생시
         model.addAttribute("productId", itemForm.getProductId());
         return ADD_GroupBuying_FORM;
      }
   
      int calDiscount;   //할인율
      double listPrice = groupBuyingForm.getListPrice(); //정가
      double unitCost = itemForm.getUnitCost();	//할인가
      
      calDiscount = (int) ((listPrice - unitCost) / listPrice * 100);   //할인율 계산

      groupBuyingForm.setDiscount(calDiscount);
       
      String deadLine = groupBuyingForm.getDate() + " " + groupBuyingForm.getTime() + ":00";
      groupBuyingForm.setDeadLine(deadLine);
      
      model.addAttribute(itemForm);
      modelMap.addAttribute("tags", itemForm.getTags());
      return CHECK_FORM3;      // step3(CHECK_FORM3)
   }
   
   @PostMapping("/shop/groupBuying/detailItem.do")      // step3 -> done
   public String done(@ModelAttribute("GroupBuying") GroupBuyingForm groupBuyingform, 
         ItemForm itemformSession, BindingResult result, Model model, HttpServletRequest rq, 
         SessionStatus sessionStatus, ModelMap modelMap) throws ParseException {
      int status = 0;
      
      HttpSession session = rq.getSession(false);
      itemformSession = (ItemForm) session.getAttribute("itemForm");
      
      if(session.getAttribute("status") != null) {   //수정으로 들어온 경우
         status = (int) session.getAttribute("status");
      }
      
      UserSession userSession = (UserSession) session.getAttribute("userSession");
      String suppId = userSession.getAccount().getUserId();
          
      //put itemformSession to item
      Item item = new Item(itemformSession.getUnitCost(), itemformSession.getTitle(), itemformSession.getDescription(), 
    		  itemformSession.getQty(), suppId, itemformSession.getProductId());
      
      if(status != 0) {   //수정인 경우
         item.setItemId(status);   //status == itemId
         item.setViewCount(itemformSession.getViewCount());   //기존의 조회수 그대로 적용
      
         List<Tag> tags = itemFacade.getTagByItemId(status);   //기존 태그 호출
         System.out.println("tag size : " + tags.size());   //0
         
         if(tags.size() > 0) {   //기존 태그 전부 삭제
            itemFacade.deleteTag(status);
            tags.removeAll(tags);   
         }
      }
      
      //generate tags(only have tagName) if(tag != null && "") then addTags
      for(int i = 0; i < itemformSession.getTags().size(); i++) {
         item.makeTags(itemformSession.getTags().get(i));  
      }
      
      GroupBuying gb = new GroupBuying(item, groupBuyingform.getDiscount(), groupBuyingform.getListPrice(), groupBuyingform.getDeadLine());
      
      if(status != 0) { itemFacade.updateGroupBuying(gb); }  //update DB- transaction
      else { itemFacade.insertGroupBuying(gb); }  // insert DB - transaction
      
      sessionStatus.setComplete();   // groupBuying, editStatus session close
      session.removeAttribute("itemForm");   //itemForm session close
      session.removeAttribute("status");
      
      SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
      Date date = dt.parse(groupBuyingform.getDeadLine()); 
      
      itemFacade.groupBuyingScheduler(date);
     
      return "redirect:/shop/groupBuying/viewItem.do?itemId=" + gb.getItemId() + "&productId=" + item.getProductId();
   }
   
   @RequestMapping("/shop/groupBuying/viewItem.do") //detail Page(조회수++)
   public String viewGroupBuying(@RequestParam("itemId") int itemId, HttpServletRequest rq,
		   @RequestParam("productId") int productId, Model model, Model modelMap) throws ParseException
   {
      String victim = null;
      String isAccuse = "false";
      
      HttpSession session = rq.getSession(false);
      
      if(session.getAttribute("userSession") != null) {         
         UserSession userSession = (UserSession) session.getAttribute("userSession");
         victim = userSession.getAccount().getUserId();
         String attacker = this.itemFacade.getUserIdByItemId(itemId);
         isAccuse = this.myPageFacade.isAccuseAlready(attacker, victim);
      }
      
      GroupBuying groupBuying = itemFacade.getGroupBuyingItem(itemId);
      itemFacade.updateViewCount(groupBuying.getViewCount() + 1, itemId);
      
      List<Tag> tags = new ArrayList<Tag>();
      tags = itemFacade.getTagByItemId(groupBuying.getItemId());
      
      model.addAttribute("gb", groupBuying);
      model.addAttribute("productId", productId);
      model.addAttribute("isAccuse", isAccuse);
      modelMap.addAttribute("tags", tags);
      
      return DetailPage;
   }
   
   @RequestMapping("/shop/groupBuying/edit.do") //edit Item
   public String editItem(@RequestParam("itemId") int itemId, ItemForm itemForm, Item item, HttpServletRequest rq)
   {
      HttpSession session = rq.getSession(true);
      session.setAttribute("itemForm", itemForm);
      session.setAttribute("status", itemId);

      GroupBuying gb = itemFacade.getGroupBuyingItem(itemId);
      
      ItemForm itemFormSession = (ItemForm) session.getAttribute("itemForm");

      itemFormSession.setUnitCost(gb.getUnitCost());
      itemFormSession.setTitle(gb.getTitle());
      itemFormSession.setDescription(gb.getDescription());
      itemFormSession.setQty(gb.getQty());
      itemFormSession.setViewCount(gb.getViewCount());
      itemFormSession.setTags(gb.getTags());
   
      return "redirect:/shop/item/addItem.do?productId=" + itemFormSession.getProductId();
   }
   
   @RequestMapping("/shop/groupBuying/delete.do") //edit Item
   public void deleteItem(@RequestParam("itemId") int itemId, @RequestParam("productId") int productId, 
		   HttpServletResponse response) throws IOException
   {
	   itemFacade.deleteItem(itemId);//상품 삭제
	   PrintWriter out = response.getWriter();

	   out.println("<script>");
	   out.print("alert('DELETED !');");
	   out.print("location.href='listItem.do?itemId=" + itemId + "&productId=" + productId + "';");	
	   out.println("</script>");
	   out.flush();
	   out.close();
   }
   
   @RequestMapping("/shop/groupBuying/index.do") //go index(remove sessions)
   public String goIndex(SessionStatus sessionStatus, HttpServletRequest rq)
   {
      HttpSession session = rq.getSession(false);
      
      sessionStatus.setComplete();// groupBuying session close
      session.removeAttribute("itemForm");   //itemForm session close
      session.removeAttribute("status");      //edit flag Session close
      
      return "redirect:/shop/index.do";
   }
   
   @RequestMapping("/shop/groupBuying/goCart.do") //go index(remove sessions)
   public String goIndex(@RequestParam("workingItemId") int workingItemId, @RequestParam("productId") int productId)
   { return "redirect:/shop/addItemToCart.do?workingItemId=" + workingItemId + "&productId=" + productId; }
   
   @RequestMapping("/shop/groupBuying/joint.do") //joint GroupBuying
   public void jointGroupBuying(@RequestParam("workingItemId") int workingItemId, @RequestParam("productId") int productId, 
         HttpServletResponse response) throws IOException
   {
      PrintWriter out=response.getWriter();
      
      out.println("<script>");
      out.print("if (confirm('Do you want to participate in GroupBuying?') == true){");
      out.print("location.href='goCart.do?workingItemId=" + workingItemId + "&productId=" + productId + "';}");
      out.print("else{location.href='viewItem.do?itemId=" + workingItemId + "&productId=" + productId + "';}");   //공동구매 진행 취소
      out.println("</script>");
      out.flush();
      out.close();
   }
   
}