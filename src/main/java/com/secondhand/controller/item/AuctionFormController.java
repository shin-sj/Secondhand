package com.secondhand.controller.item;

import java.io.IOException;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.secondhand.controller.mypage.UserSession;
import com.secondhand.domain.Account;
import com.secondhand.domain.Auction;
import com.secondhand.domain.Bidder;
import com.secondhand.domain.Item;
import com.secondhand.domain.Tag;
import com.secondhand.service.AuctionFormValidator;
import com.secondhand.service.facade.ItemFacade;
import com.secondhand.service.facade.MyPageFacade;

@Controller
@SessionAttributes({"Auction", "auctionList", "resultBidder"})
public class AuctionFormController {
   private static final String ADD_Auction_FORM = "product/addAuction";
   private static final String GoAddItemFORM = "redirect:/shop/item/addItem.do?productId=";
   private static final String CHECK_FORM3 = "product/checkAuction";
   
   private ItemFacade itemFacade;
   private MyPageFacade myPageFacade;
   
   private int myItemId;
   private int myProductId;
   
   @Autowired
   public void setItemFacade(ItemFacade itemFacade) {
      this.itemFacade = itemFacade;
   }
   
   @Autowired
   public void setMyPageFacade(MyPageFacade myPageFacade) {
      this.myPageFacade = myPageFacade;
   }
   
   @ModelAttribute("Auction")		  
	public AuctionForm formBacking() {  // accessor method 
		System.out.println("formBacking");
		
		return new AuctionForm();
	}
   
   //경매 리스트 보여주기
   @RequestMapping("/shop/auction/listItem.do") 
   public String auctionList(@RequestParam("productId") int productId, ModelMap model,HttpServletRequest rq) {
      myProductId = productId;
      HttpSession session = rq.getSession(false);
      
      Account account = null;
      if (session.getAttribute("userSession") != null) {
             UserSession userSession = (UserSession)session.getAttribute("userSession") ;
             if (userSession != null) {  //로그인상태이면 대학정보 가져온다 
                account = userSession.getAccount();
             }
      }
      
      List<Auction> al = new ArrayList<Auction>();
      al = this.itemFacade.getAuctionList(account);
      
      PagedListHolder<Auction> auctionList = new PagedListHolder<Auction>(al);
      auctionList.setPageSize(4);
      
      //낙찰자 리스트 구하기 (jsp 에서는 경매가 종료된(auctionState = 1) 상태여야 bidder 을 보여줄 수 있게 해야한다.
      List<Bidder> bidderList = new ArrayList<Bidder>();
      bidderList = itemFacade.getBidderList();
      
      List<Bidder> bl = new ArrayList<Bidder>();
     
      Bidder noBidder = new Bidder();
      noBidder.setItemId(-1);
      noBidder.setBidder("<no Bidder>");
       
      //경매 리스트 사이즈와 같은 크기의 낙찰자 리스트 생성
      for(int i = 0; i < al.size(); i++) {
    	  bl.add(i, noBidder);
      }
      
      //낙찰자가 있으면 삽입
      for(int i = 0; i < al.size(); i++) {
    	  for(int j = 0; j < bidderList.size(); j++) {
    		  if (al.get(i).getItemId() == bidderList.get(j).getItemId()) {
    			  bl.set(i, bidderList.get(j));
    		  }
    	  }
      }
      
      PagedListHolder<Bidder> resultBidder = new PagedListHolder<Bidder>(bl);
      resultBidder.setPageSize(4);    
      
      model.addAttribute("productId", productId);
      model.addAttribute("auctionList", auctionList);
      model.addAttribute("resultBidder", resultBidder);

      return "product/auction";
   }
   
   
	@RequestMapping("shop/auction/listItem2.do")
	public String auctionList2 (
			@ModelAttribute("auctionList") PagedListHolder<Auction> auctionList,
			@ModelAttribute("resultBidder") PagedListHolder<Auction> resultBidder,
			@RequestParam("pageName") String page, ModelMap model) throws Exception {
		if ("next".equals(page)) {
			auctionList.nextPage();
			resultBidder.nextPage();
		}
		else if ("previous".equals(page)) {
			auctionList.previousPage();
			resultBidder.previousPage();
		}
		model.addAttribute("productId", myProductId);
	    model.addAttribute("auctionList", auctionList);
	    model.addAttribute("resultBidder", resultBidder);
		return "product/auction";
	}
	
   @RequestMapping("/shop/auction/viewItem.do") 
   public String auctionView(@RequestParam("itemId") int itemId, 
		   @RequestParam("productId") int productId, HttpServletRequest rq,
		   Model model, Model modelMap) {
	  
	   String victim = null;
	   String isAccuse = "false";
	   HttpSession session = rq.getSession(false);
	   if(session.getAttribute("userSession") != null) {
		   UserSession userSession = (UserSession) session.getAttribute("userSession");
		   if (userSession != null) {// attacker = 판매자 아이디, victim = 세션 유저 아이디
				victim = userSession.getAccount().getUserId();
				String attacker = this.itemFacade.getUserIdByItemId(itemId);
				isAccuse = this.myPageFacade.isAccuseAlready(attacker, victim);
			}
	   }
		
	  System.out.println("<경매 상세 페이지>"); 
	  
	  Auction auction = this.itemFacade.getAuctionById(itemId);

	  itemFacade.updateViewCount(auction.getViewCount() + 1, itemId);
	  
	  myItemId = itemId;
	  
	  List<Tag> tags = new ArrayList<Tag>();
	  tags = itemFacade.getTagByItemId(auction.getItemId());
	  
	  if (auction.getAuctionState() == 1) {//낙찰되었으면 낙찰자 보내기
		  String bidder = itemFacade.isBidderExist(itemId);
		  model.addAttribute("bidder", bidder);
	  }
	  model.addAttribute("productId", productId);
      model.addAttribute("auction", auction);
      modelMap.addAttribute("tags", tags);
      model.addAttribute("isAccuse", isAccuse);

      return "product/viewAuction";
   }

   
   /*로그인 필요*/
   @RequestMapping("/shop/auction/participateItem.do") 
   public String auctionParticipate(HttpServletRequest rq, ModelMap model, ItemForm itemformSession) {
	   int status = 0;
	   
	   HttpSession session = rq.getSession(false);
	   itemformSession = (ItemForm) session.getAttribute("itemForm");
		if(session.getAttribute("status") != null) {
			status = (int) session.getAttribute("status");
			System.out.println("status" + status);
		}
		if(session.getAttribute("userSession") != null) {
			UserSession userSession = (UserSession) session.getAttribute("userSession");
			System.out.println(userSession);
		}
		
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		String suppId = userSession.getAccount().getUserId();
	   
		if(session.getAttribute("itemForm") != null) {
			System.out.println("itemformSession: " + itemformSession);	//print itemformSession toString
		}  
	   
		//폼에서 입력 가격 받아오기 
	   int price = Integer.parseInt(rq.getParameter("price"));
	   
	   String rslt = itemFacade.isBidderExist(myItemId);

	   if (rslt == null) {//낙찰자가 없으면 낙찰자 테이블에 itemId, bidder 넣어주기		   
		   Bidder bidder = new Bidder();
		   bidder.setItemId(myItemId);
		   bidder.setBidder(suppId); 
		   
		   itemFacade.insertBidder(bidder);
	   }
	   else {//이미 낙찰자가 있으면 낙찰자 테이블에 bidder 수정		   
		   itemFacade.updateBidder(suppId, myItemId); 
	   }
	   
	   //파라미터로 받아온 입력 가격(price)을 item 테이블의 unitCost 필드에 update 해주기
	   itemFacade.updateAuctionUnitCost(price, myItemId);
	   
	   return "redirect:/shop/auction/viewItem.do?itemId=" + myItemId + "&productId=" + myProductId;
   }
   
   
   @RequestMapping(value="/shop/auction/addItem2.do", method = RequestMethod.GET)
   public String step2(
         @ModelAttribute("Auction") AuctionForm auctionForm, 
         @RequestParam("productId") int productId, Model model, HttpServletRequest rq) {
      
      HttpSession session = rq.getSession(false);
		if(session.getAttribute("status") != null) {
			System.out.println("from edit - addItem2.do");	
		}
		
      model.addAttribute("productId", productId);
      return ADD_Auction_FORM;   // addAuction.jsp
   }
 
   @RequestMapping(value="/shop/auction/gobackItem.do", method = RequestMethod.GET)		// go back to item.jsp
	public String backToItem(@ModelAttribute("Auction") AuctionForm auctionForm, 
			@RequestParam("productId") int productId) {
		
		return GoAddItemFORM + productId;	// step1(item.jsp) form step2(addAuction.jsp)
	}
	
	
	@PostMapping("/shop/auction/step3.do")		// step2 -> step3
	public String goCheck(@ModelAttribute("Auction") AuctionForm auctionForm, BindingResult result,
			HttpServletRequest rq, ItemForm itemForm, Model model) throws ParseException {	
		System.out.println("step3.do(before check form)");
		HttpSession session = rq.getSession(false);
		
		new AuctionFormValidator().validate(auctionForm, result);
		
		itemForm = (ItemForm) session.getAttribute("itemForm");
		if(session.getAttribute("itemForm") != null) {
			System.out.println("itemformSession: " + itemForm);	//print itemformSession toString
			System.out.println(itemForm.getTags());
		}
		
		if (result.hasErrors()) {	//유효성 검증 에러 발생시
			model.addAttribute("productId", itemForm.getProductId());
			return ADD_Auction_FORM;
		}
		
		model.addAttribute("tags", itemForm.getTags());
		model.addAttribute(itemForm);
		
		Date date = new Date();
		model.addAttribute(date);
		
		return CHECK_FORM3;		// step3(CHECK_FORM3)
	}
	
	@PostMapping("/shop/auction/detailItem.do")		// step3 -> done
	public String done(@ModelAttribute("Auction") AuctionForm auctionForm,
			ItemForm itemformSession, BindingResult result, Model model, HttpServletRequest rq, 
			SessionStatus sessionStatus, ModelMap modelMap) throws ParseException {
		
		int status = 0;
		
		HttpSession session = rq.getSession(false);
		itemformSession = (ItemForm) session.getAttribute("itemForm");
		if(session.getAttribute("status") != null) {
			status = (int) session.getAttribute("status");
			System.out.println("status" + status);
		}
		if(session.getAttribute("userSession") != null) {
			UserSession userSession = (UserSession) session.getAttribute("userSession");
			System.out.println(userSession);
		}
		
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		String suppId = userSession.getAccount().getUserId();
				
		if(session.getAttribute("itemForm") != null) {
			System.out.println("itemformSession: " + itemformSession);	//print itemformSession toString
		}
		System.out.println(auctionForm);
	
		//put itemformSession to item
		Item item = new Item(itemformSession.getUnitCost(), itemformSession.getTitle(), 
				itemformSession.getDescription(), itemformSession.getQty(), suppId, 	//�씤�꽣�뀎�꽣 ��怨� �삤�땲源� suppId 臾댁“嫄� �엳�쓬
				itemformSession.getProductId());
		
		  
		if(status != 0) {
			item.setItemId(status);   //status == itemId
	         item.setViewCount(itemformSession.getViewCount());   //기존의 조회수 그대로 적용
	      
	         List<Tag> tags = itemFacade.getTagByItemId(status);   //기존 태그 호출
	         System.out.println("tag size : " + tags.size());   //0
	         
	         if(tags.size() > 0) {   //기존 태그 전부 삭제
	            itemFacade.deleteTag(status);
	            tags.removeAll(tags);   
	         }
		}
		
		//generate tags(only have tagName)      
	    for(int i = 0; i < itemformSession.getTags().size(); i++) {
	    	item.makeTags(itemformSession.getTags().get(i));   //if(tag != null && "") then addTags
	    }
	    
		Auction auction = new Auction(item, 0, auctionForm.getDeadLine(), auctionForm.getStartPrice(), 0);
	
		if(status != 0) {
			itemFacade.updateAuction(auction);
		}
		else {
			itemFacade.insertAuction(auction);	// insert DB
		}
		
		sessionStatus.setComplete();	// Auction session close
		session.removeAttribute("itemForm");	//itemForm session close
		
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		Date date = dt.parse(auctionForm.getDeadLine()); 
		
		itemFacade.testScheduler(date);
		
		return "redirect:/shop/auction/viewItem.do?itemId=" + auction.getItemId() + "&productId=" + item.getProductId();
	}
	
	//경매 수정
   @RequestMapping("/shop/auction/updateItem.do") 
   public String auctionUpdate(ItemForm itemForm, Item item, @RequestParam("itemId") int itemId, HttpServletRequest rq) {
	   HttpSession session = rq.getSession(true);
	   session.setAttribute("itemForm", itemForm);
	   session.setAttribute("status", itemId);

	   Auction auction = itemFacade.getAuctionById(itemId);
	   
	   ItemForm itemFormSession = (ItemForm) session.getAttribute("itemForm");
	   
	   itemFormSession.setUnitCost(auction.getUnitCost());
	   itemFormSession.setTitle(auction.getTitle());
	   itemFormSession.setDescription(auction.getDescription());
	   itemFormSession.setQty(auction.getQty());
	   itemFormSession.setViewCount(auction.getViewCount());
	   itemFormSession.setTags(auction.getTags());
	   itemFormSession.setProductId(1);
	   
	   return "redirect:/shop/item/addItem.do?productId=" + itemFormSession.getProductId();
   }

  
   //경매 삭제
   @RequestMapping("/shop/auction/deleteItem.do") 
   public String auctionDelete(@RequestParam("itemId") int itemId, ModelMap model, HttpServletResponse response) throws IOException {  
	   this.itemFacade.deleteItem(itemId);
	   
	   return "redirect:/shop/auction/listItem.do?productId=" + myProductId;
   }
   

   @RequestMapping("/shop/auction/testSchedulerItem.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			@RequestParam("keyword")
			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm") Date deadLine) throws Exception {
		itemFacade.testScheduler(deadLine);
		return new ModelAndView("Scheduled", "deadLine", deadLine);	
	}
   
   @RequestMapping("/shop/auction/index.do") //go index(remove sessions)
   public String goIndex(SessionStatus sessionStatus, HttpServletRequest rq)
   {
      HttpSession session = rq.getSession(false);
      
      sessionStatus.setComplete();// auction session close
      session.removeAttribute("itemForm");   //itemForm session close
      session.removeAttribute("status");      //edit flag Session close
      
      return "redirect:/shop/index.do";
   }
}