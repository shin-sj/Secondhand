package com.secondhand.controller.item;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.secondhand.domain.Item;
import com.secondhand.domain.Tag;
import com.secondhand.service.facade.ItemFacade;

@Controller
@SessionAttributes({"tagList", "resultList"})
public class SearchController {
	private ItemFacade itemFacade;
	String searchWord; //검색어
	String sKind; 	   //검색 종류
	
	@Autowired
	public void setItemFacade(ItemFacade itemFacade) {
		this.itemFacade = itemFacade;
	}
	 
	@RequestMapping("/shop/search/viewItem.do") 
	public String searchList(@RequestParam(value="tagName", required=false) String itemTag, HttpServletRequest rq, ModelMap model) {
		System.out.println("** Search Controller **");
		
		sKind = null;
		
		if (rq.getParameter("sKind") != null) {
			sKind = rq.getParameter("sKind");
		}
		else if (rq.getParameter("sKind") == null) {//검색 종류가 없으면(제목/태그 검색한게 아니면) 게시물에서 태그 클릭해서 검색한 것
			sKind = "tag";
		}
		
		if (sKind.equals("tag")) {//태그 검색일 때 
			if (itemTag != null) {//빈칸 삭제
				searchWord = itemTag.replaceAll(" ", "");
			}
			else {
				searchWord = rq.getParameter("word").replaceAll(" ", "");
			}
			
			if (searchWord != null) {//검색어가 없을 때
				if (!StringUtils.hasLength(searchWord)) {
					model.addAttribute("message", "검색어를 입력해주세요.");
					return "error";
				}
			}
			
			//검색어와 일치하는 태그 리스트 가져오기
			List<Tag> tagList = new ArrayList<Tag>();
			tagList = this.itemFacade.getTagByTagName(searchWord);
			
		    //태그 리스트랑 같은 크기의 item 배열 생성
		    List<Item> resultList = new ArrayList<Item>();
		    
		    for(int i = 0; i < tagList.size(); i++) {
		    	Item item = itemFacade.getItem(tagList.get(i).getItemId());
		    	resultList.add(i, item); 
		    }
		    
		    PagedListHolder<Item> rl = new PagedListHolder<Item>(resultList);
		    rl.setPageSize(4);
		    
		    if (resultList.size() == 0) {
		    	model.addAttribute("noResult", 1);
		    }

			model.addAttribute("resultList", rl);
		} else if (sKind.equals("title")) {//제목 검색일 때
			searchWord = rq.getParameter("word");
			
			if (searchWord != null) {
				if (!StringUtils.hasLength(searchWord)) {
					model.addAttribute("message", "검색어를 입력해주세요.");
					return "error";
				}
			}
			
			List<Item> titleList = new ArrayList<Item>();
			titleList = this.itemFacade.getItemByTitle(searchWord);
			
			System.out.println("타이틀 검색 결과");
			for(int i = 0; i < titleList.size(); i++) {
				System.out.println(titleList.get(i).toString());
			}
			
			PagedListHolder<Item> rl = new PagedListHolder<Item>(titleList);
			rl.setPageSize(4);
			
			if (titleList.size() == 0) {
		    	model.addAttribute("noResult", 1);
		    }
			
			model.addAttribute("resultList", rl);
		}
		
		model.addAttribute("sKind", sKind);
		model.addAttribute("searchWord", searchWord);
		return "product/search";
	}
	
	@RequestMapping("/shop/search/viewItem2.do") 
	public String searchList2(
			@ModelAttribute("resultList") PagedListHolder<Item> resultList,
			@RequestParam("pageName") String page,
			ModelMap model) {
		if ("next".equals(page)) {
			resultList.nextPage();
		}
		else if ("previous".equals(page)) {
			resultList.previousPage();
		}
		
		model.addAttribute("sKind", sKind);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("resultList", resultList);
		
		return "product/search";
	}
	
	
	@RequestMapping("/shop/search/selectItem.do") 
	public String selectItem(@RequestParam("itemId") int itemId, ModelMap model) {
		System.out.println("** 검색 페이지에서 아이템 아이디 선택 **");
		System.out.println(itemId);
		
		Item item = this.itemFacade.getItem(itemId);
		System.out.println(item.toString());
		
		int productId = item.getProductId();
		
		System.out.println("productId : " + productId);
		
		String url = null;
		switch(productId) {//productId 에 따라 다른 url로 보내기
		case 0: 
			url = "redirect:/shop/groupBuying/viewItem.do?itemId=" + itemId + "&productId=" + productId;
			break;
		case 1: 
			url = "redirect:/shop/auction/viewItem.do?itemId=" + itemId + "&productId=" + productId;
			break;
		case 2:
			url = "redirect:/shop/secondHand/viewItem.do?itemId=" + itemId + "&productId=" + productId;
			break;
		}
		
		return url;
	}
}
