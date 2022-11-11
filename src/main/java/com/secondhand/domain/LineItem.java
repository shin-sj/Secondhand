package com.secondhand.domain;

import java.io.Serializable;

/*
 * Secondhand - LineItem Domain Class
 * */
@SuppressWarnings("serial")
public class LineItem implements Serializable {

  /* Private Fields */
  private int orderId;
  private int lineNum;
  private int itemId;
  private int quantity;
  private int unitPrice;
  private Item item;

  /* Constructors */

  public LineItem() {}

  public LineItem(int lineNum, CartItem cartItem) {
    this.orderId = cartItem.getOrderId();
	this.lineNum = lineNum;
    this.itemId = cartItem.getItem().getItemId();
    this.quantity = cartItem.getQuantity();
    this.unitPrice = cartItem.getItem().getUnitCost();
    this.item = cartItem.getItem();
  }

  /* JavaBeans Properties */
  
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public int getLineNum() {
		return lineNum;
	}
	
	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}
	
	public int getItemId() {
		return itemId;
	}
	
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public int getTotalPrice() {
		return this.unitPrice * this.quantity;
	}
	
	public int getOrderId() {
		return orderId;
	}
	
	public int getUnitPrice() {
		return unitPrice;
	}

	@Override
	public String toString() {
		return "LineItem [orderId=" + orderId + ", lineNum=" + lineNum + ", itemId=" + itemId + ", quantity=" + quantity
				+ ", item=" + item + ", getOrderId()=" + getOrderId() + ", getLineNum()=" + getLineNum()
				+ ", getItemId()=" + getItemId() + ", getQuantity()=" + getQuantity() + ", getItem()=" + getItem()
				+ ", getTotalPrice()=" + getTotalPrice() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}
