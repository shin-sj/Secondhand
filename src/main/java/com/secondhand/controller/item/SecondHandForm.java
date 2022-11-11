package com.secondhand.controller.item;

public class SecondHandForm {

	int itemId;
	String discount;  //라디오 버튼 커맨득객체에서는  String타입, 도메인에서는 int타입이다.
	int listPrice;
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getListPrice() {
		return listPrice;
	}
	public void setListPrice(int listPrice) {
		this.listPrice = listPrice;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
}
