package com.secondhand.domain;

import java.io.Serializable;

/*
 * Secondhand - Item(Auction) Domain Class
 * */
@SuppressWarnings("serial")
public class Auction extends Item implements Serializable {
	/* Private Fields */
	private int itemId;
	private int auctionState;
	private String deadLine;			// 경매 마감일
	private int startPrice;			// 경매 시작가
	private int bidPrice;
	
	public Auction() {
		super();
	}
	
	public Auction(Item item, int auctionState, String deadLine, int startPrice, int bidPrice) {
		super(item.getItemId(), item.getUnitCost(), item.getTitle(), item.getDescription(), item.getViewCount(), item.getQty(), item.getTags(), 
				item.getUserId(), item.getProductId());
		this.itemId = item.getItemId();
		this.auctionState = auctionState;
		this.deadLine = deadLine;
		this.startPrice = startPrice;
		this.bidPrice = bidPrice;
	}

	/* JavaBeans Properties */
	public int getItemId() { return itemId; }
	public void setItemId(int itemId) { this.itemId = itemId; }
	
	public int getAuctionState() { return auctionState; }
	public void setAuctionState(int auctionState) { this.auctionState = auctionState; }
	
	public String getDeadLine() { return deadLine; }
	public void setDeadLine(String deadLine) { this.deadLine = deadLine; }
	
	public int getStartPrice() { return startPrice; }
	public void setStartPrice(int startPrice) { this.startPrice = startPrice; }
	
	public int getBidPrice() { return bidPrice; }
	public void setBidPrice(int bidPrice) { this.bidPrice = bidPrice; }
	
	@Override
	public String toString() {
		return "Auction [itemId=" + itemId + ", auctionState=" + auctionState + ", deadLine=" + deadLine
				+ ", startPrice=" + startPrice + ", bidPrice=" + bidPrice + "]";
	}
}
