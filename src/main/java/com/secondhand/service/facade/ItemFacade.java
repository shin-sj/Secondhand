package com.secondhand.service.facade;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.secondhand.domain.Account;
import com.secondhand.domain.Auction;
import com.secondhand.domain.Bidder;
import com.secondhand.domain.GroupBuying;
import com.secondhand.domain.Item;
import com.secondhand.domain.Orders;
import com.secondhand.domain.SecondHand;
import com.secondhand.domain.Tag;

/*
 * ItemFacade
 * 
 * Item / GroupBuying / SecondHand / Auction / Tag 
 * */
public interface ItemFacade {

   /////////////////////////////////////////////////////////////////////////
   /* Item */
   /////////////////////////////////////////////////////////////////////////
   
   void insertItem(Item item);
   
   void updateItem(Item item);
   
   Item getItem(int itemId);
   
   public void deleteItem(int itemId);
   
   void updateQuantity(Orders order);
   
   int getQuantity(int itemId, int productId);

   boolean isItemInStock(int itemId, int productId);

   String getUserIdByItemId(int itemId);
   
   void updateViewCount(int viewCount, int itemId);
   
   List<Item> getItemByPId(int productId);
   
   List<Item> getItemByTitle(String title);
   
   /////////////////////////////////////////////////////////////////////////
   /* GroupBuying */
   /////////////////////////////////////////////////////////////////////////
   public void insertGroupBuying(GroupBuying GroupBuying);
   
   public void updateGroupBuying(GroupBuying GroupBuying);
   
   List<GroupBuying> getGroupBuyingList(Account account);
   
   GroupBuying getGroupBuyingItem(int itemId);
   
   public void groupBuyingScheduler(Date deadLine);
   
   public void closeGroupBuying(Date curTime);
   
 //품절시 삭제
   public void soldOutGroupBuying(int itemId);
   
   /////////////////////////////////////////////////////////////////////////
   /* SecondHand */
   /////////////////////////////////////////////////////////////////////////   
   List<SecondHand> getSecondHandList(Account account);
   
   SecondHand getSecondHandItem(int itemId);
   
   public void insertSecondHand(SecondHand secondHand);
   
   public void updateSecondHand(SecondHand secondHand);
   
   /////////////////////////////////////////////////////////////////////////
   /* Auction */
   /////////////////////////////////////////////////////////////////////////
   List<Auction> getAuctionList(Account account);
   
   public void insertAuction(Auction auction);
    
   public void updateAuction(Auction auction);

	public Auction getAuctionById(int itemId);
	
	public void testScheduler(Date deadLine);
	
	public void updateAuctionUnitCost(int unitCost, int itemId);
	
	public void updateBidder(String bidder, int itemId);
	
	public void insertBidder(Bidder bidder);
	
	public String isBidderExist(int itemId);
	
	public void updateBidPrice(int unitCost, int itemId);
	
	public List<Bidder> getBidderList();
	
	/////////////////////////////////////////////////////////////////////////
	/* Tag */
	/////////////////////////////////////////////////////////////////////////
	List<Tag> getTagList ();
	
	List<Tag> getTagByTagId(int tagId);
	
	List<Tag> getTagByItemId(int itemId);	
	
	List<Tag> getTagByTagName(String tagName);	
	
	void insertTag(Tag tag);	
	
	void deleteTag(int itemId);
}
