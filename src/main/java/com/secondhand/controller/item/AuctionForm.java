package com.secondhand.controller.item;

public class AuctionForm {
	/* Private Fields */
	private int itemId;
	private int startPrice;
	private String date;
	private String time;
	private String deadLine;
	
	/*getter setter*/
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
		
	public int getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(int startPrice) {
		this.startPrice = startPrice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public String getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}
	
	@Override
	public String toString() {
		return "AuctionForm [itemId=" + itemId + ", startPrice=" + startPrice + ", date=" + date + ", time=" + time
				+ ", deadLine=" + deadLine + "]";
	}
	
}
