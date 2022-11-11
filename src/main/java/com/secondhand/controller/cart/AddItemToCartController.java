package com.secondhand.controller.cart;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.secondhand.domain.Cart;
import com.secondhand.domain.Item;
import com.secondhand.service.facade.ItemFacade;


@Controller
@SessionAttributes("sessionCart")
public class AddItemToCartController { 

	private ItemFacade itemFacade;
	
	@Autowired
	public void setItemFacade(ItemFacade itemFacade) {
		this.itemFacade = itemFacade;
	}

	@ModelAttribute("sessionCart")
	public Cart createCart() {
		return new Cart();
	}
	
	@RequestMapping("/shop/addItemToCart.do")
	public ModelAndView handleRequest(
			@RequestParam("workingItemId") int workingItemId,
			@RequestParam("productId") int productId,
			@ModelAttribute("sessionCart") Cart cart 
			) throws Exception {
		if (cart.containsItemId(workingItemId)) {
			cart.incrementQuantityByItemId(workingItemId);
		}
		else {
			// isInStock is a "real-time" property that must be updated
			// every time an item is added to the cart, even if other
			// item details are cached.
			System.out.println("workingItemId : " + workingItemId);
			System.out.println("productId : " + productId);
			
			Item item = null;
			switch(productId) {
				case 0:
					item = this.itemFacade.getGroupBuyingItem(workingItemId);
					break;
				case 1:
					item = this.itemFacade.getAuctionById(workingItemId);
					break;
				case 2:
					item = this.itemFacade.getSecondHandItem(workingItemId);
					break;
			}
			
			boolean isInStock = this.itemFacade.isItemInStock(workingItemId, item.getProductId());
			cart.addItem(item, isInStock);
		}
		return new ModelAndView("order/Cart", "cart", cart);
	}
}