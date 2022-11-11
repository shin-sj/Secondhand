package com.secondhand.domain;

import java.io.Serializable;

/*
 * Secondhand - Item(SecondHand) Domain Class
 * */
@SuppressWarnings("serial")
public class SecondHand extends Item implements Serializable {

	private int itemId; //FK 
	private int discount;
	private int listPrice;
		
	public SecondHand() {
		super();
	}
	
	public SecondHand(Item item, int discount, int listPrice) {
	      super(item.getItemId(), item.getUnitCost(), item.getTitle(), item.getDescription(), item.getViewCount(), item.getQty(), item.getTags(), 
	            item.getUserId(), item.getProductId());
	      this.itemId = item.getItemId();
	      this.discount = discount;
	      this.listPrice = listPrice;
	   }
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getListPrice() {
		return listPrice;
	}
	public void setListPrice(int listPrice) {
		this.listPrice = listPrice;
	}

	@Override
	public String toString() {
		return "SecondHand [itemId=" + itemId + ", discount=" + discount + ", listPrice=" + listPrice + "]";
	}

	
	
}
