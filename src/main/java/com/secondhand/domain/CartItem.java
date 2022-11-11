package com.secondhand.domain;

import java.io.Serializable;

/*
 * Secondhand - CartItem Domain Class
 * */
@SuppressWarnings("serial")
public class CartItem implements Serializable {

	/* Private Fields */

	private Item item;
	private int quantity;
	private boolean inStock;
	private int orderId;
	
	/* JavaBeans Properties */

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getTotalPrice() {
		if (item != null) {
			return item.getUnitCost() * quantity;
		} else {
			return 0;
		}
	}

	/* Public methods */

	public void incrementQuantity() {
		quantity++;
	}
}
