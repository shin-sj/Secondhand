package com.secondhand.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Bidder implements Serializable {
	/* Private Fields */
	private int itemId;
	private String bidder;
		
	/* JavaBeans Properties */
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getBidder() {
		return bidder;
	}
	public void setBidder(String bidder) {
		this.bidder = bidder;
	}
	
	@Override
	public String toString() {
		return "Bidder [itemId=" + itemId + ", bidder=" + bidder + "]";
	}
}
