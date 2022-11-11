package com.secondhand.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/*
 * Secondhand - Order Domain Class
 * */
@SuppressWarnings("serial")
public class Orders implements Serializable {

	/* Private Fields */
	private int orderId;
	private String userId;
	private String courier;
	private double totalPrice;
	private String creditCard;
	private String expiryDate;
	private String cardType;
	private String locale;
	private Date orderDate;
	private String shipAddr1;
	private String shipAddr2;
	private String shipCity;
	private String shipZip;
	private String shipCountry;
	private String shipToUsername;
	private String billAddr1;
	private String billAddr2;
	private String billCity;
	private String billZip;
	private String billCountry;
	private String billToUsername;
	private String status;
	private List<LineItem> lineItems = new ArrayList<LineItem>();

	/* JavaBeans Properties */

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCourier() {
		return courier;
	}

	public void setCourier(String courier) {
		this.courier = courier;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getShipAddr1() {
		return shipAddr1;
	}

	public void setShipAddr1(String shipAddr1) {
		this.shipAddr1 = shipAddr1;
	}

	public String getShipAddr2() {
		return shipAddr2;
	}

	public void setShipAddr2(String shipAddr2) {
		this.shipAddr2 = shipAddr2;
	}

	public String getShipCity() {
		return shipCity;
	}

	public void setShipCity(String shipCity) {
		this.shipCity = shipCity;
	}

	public String getShipZip() {
		return shipZip;
	}

	public void setShipZip(String shipZip) {
		this.shipZip = shipZip;
	}

	public String getShipCountry() {
		return shipCountry;
	}

	public void setShipCountry(String shipCountry) {
		this.shipCountry = shipCountry;
	}

	public String getShipToUsername() {
		return shipToUsername;
	}

	public void setShipToUsername(String shipToUsername) {
		this.shipToUsername = shipToUsername;
	}

	public String getBillAddr1() {
		return billAddr1;
	}

	public void setBillAddr1(String billAddr1) {
		this.billAddr1 = billAddr1;
	}

	public String getBillAddr2() {
		return billAddr2;
	}

	public void setBillAddr2(String billAddr2) {
		this.billAddr2 = billAddr2;
	}

	public String getBillCity() {
		return billCity;
	}

	public void setBillCity(String billCity) {
		this.billCity = billCity;
	}

	public String getBillZip() {
		return billZip;
	}

	public void setBillZip(String billZip) {
		this.billZip = billZip;
	}

	public String getBillCountry() {
		return billCountry;
	}

	public void setBillCountry(String billCountry) {
		this.billCountry = billCountry;
	}

	public String getBillToUsername() {
		return billToUsername;
	}

	public void setBillToUsername(String billToUsername) {
		this.billToUsername = billToUsername;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	/* Public Methods */

	public void initOrder(Account account, Cart cart, String status) { //���⼭ status�� OrderStatus���̺��� ������ 
	    userId = account.getUserId();
	    orderDate = new Date();
	
	    shipToUsername = account.getUsername();
	    shipAddr1 = account.getAddr1();
	    shipAddr2 = account.getAddr2();
	    shipCity = account.getCity();
	    shipZip = account.getZip();
	    shipCountry = account.getCountry();
	
	    billToUsername = account.getUsername();
	    billAddr1 = account.getAddr1();
	    billAddr2 = account.getAddr2();
	    billCity = account.getCity();
	    billZip = account.getZip();
	    billCountry = account.getCountry();
	
	    totalPrice = cart.getSubTotal();
	
	    creditCard = "999 9999 9999 9999";
	    expiryDate = "12/03";
	    cardType = "Visa";
	    courier = "UPS";
	    locale = "CA";
	    status = "P";
	
	    Iterator<CartItem> i = cart.getAllCartItems();
	    while (i.hasNext()) {
	      CartItem cartItem = (CartItem) i.next();
	      addLineItem(cartItem);
	    }
	}
	
	public void addLineItem(CartItem cartItem) {
	    LineItem lineItem = new LineItem(lineItems.size() + 1, cartItem);
	    addLineItem(lineItem);
	}
	
	public void addLineItem(LineItem lineItem) {
	    lineItems.add(lineItem);
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", userId=" + userId + ", courier=" + courier + ", totalPrice="
				+ totalPrice + ", creditCard=" + creditCard + ", expiryDate=" + expiryDate + ", cardType=" + cardType
				+ ", locale=" + locale + ", orderDate=" + orderDate + ", shipAddr1=" + shipAddr1 + ", shipAddr2="
				+ shipAddr2 + ", shipCity=" + shipCity + ", shipZip=" + shipZip + ", shipCountry=" + shipCountry
				+ ", shipToUsername=" + shipToUsername + ", billAddr1=" + billAddr1 + ", billAddr2=" + billAddr2
				+ ", billCity=" + billCity + ", billZip=" + billZip + ", billCountry=" + billCountry
				+ ", billToUsername=" + billToUsername + ", status=" + status + ", lineItems=" + lineItems.toString() + "]";
	}
	
}