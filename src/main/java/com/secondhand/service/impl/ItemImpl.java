package com.secondhand.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.secondhand.dao.AuctionDao;
import com.secondhand.dao.GroupBuyingDao;

import com.secondhand.dao.ItemDao;
import com.secondhand.dao.SecondHandDao;
import com.secondhand.dao.TagDao;
import com.secondhand.domain.Account;
import com.secondhand.domain.Auction;
import com.secondhand.domain.Bidder;
import com.secondhand.domain.GroupBuying;

import com.secondhand.domain.Item;
import com.secondhand.domain.Orders;
import com.secondhand.domain.SecondHand;
import com.secondhand.domain.Tag;
import com.secondhand.service.facade.ItemFacade;

/*
 * ItemFacade
 * 
 * Item / GroupBuying / SecondHand / Auction / Tag
 * */
@Repository
@Service("itemImpl")
@Transactional
public class ItemImpl implements ItemFacade {

	@Autowired
	private ItemDao itemDao;
	@Autowired
	private GroupBuyingDao groupBuyingDao;
	@Autowired
	private SecondHandDao secondHandDao;
	@Autowired
	private AuctionDao auctionDao;
	@Autowired
	private TagDao tagDao;
	@Autowired // applicationContext.xml占쎈퓠 占쎌젟占쎌벥占쎈쭆 scheduler 揶쏆빘猿쒐몴占 雅뚯눘 뿯 獄쏆룇 벉
	private ThreadPoolTaskScheduler scheduler;

	@Override
	public void updateQuantity(Orders order) {
		// TODO Auto-generated method stub
		itemDao.updateQuantity(order);
	}

	@Override
	public int getQuantity(int itemId, int productId) {
		// TODO Auto-generated method stub
		switch (productId) {
		case 0:
			return groupBuyingDao.getQuantity(itemId, productId);
		case 1:
			return auctionDao.getQuantity(itemId, productId);
		case 2:
			return secondHandDao.getQuantity(itemId, productId);
		default:
			System.err.println("getQuantity Error !!");
			return -1;
		}
	}

	@Override
	public void deleteItem(int itemId) {
		// TODO Auto-generated method stub
		itemDao.deleteItem(itemId);
	}

	// 뜝 럩逾졿ㅀ袁ъ삕 占썩뫁伊숃뜎洹⑥삕 뜮 쉻 삕 뵳占
	@Override
	public boolean isItemInStock(int itemId, int productId) {
		// TODO Auto-generated method stub
		switch (productId) {
		case 0:
			return groupBuyingDao.isItemInStock(itemId, productId);
		case 1:
			return auctionDao.isItemInStock(itemId, productId);
		case 2:
			return secondHandDao.isItemInStock(itemId, productId);
		default:
			System.err.println("isItemInStock Error !!");
			return false;
		}
	}

	public List<Item> getItemByTitle(String title) {
		return itemDao.getItemByTitle(title);
	}
	

	@Override
	public void insertGroupBuying(GroupBuying GroupBuying) {
		// TODO Auto-generated method stub
		groupBuyingDao.insertGroupBuying(GroupBuying);
	}

	@Override
	public void updateGroupBuying(GroupBuying GroupBuying) {
		// TODO Auto-generated method stub
		groupBuyingDao.updateGroupBuying(GroupBuying);
	}

	@Override
	public List<GroupBuying> getGroupBuyingList(Account account) {
		// TODO Auto-generated method stub
		return groupBuyingDao.getGroupBuyingList(account);
	}

	
	@Override
	public GroupBuying getGroupBuyingItem(int itemId) {
		// TODO Auto-generated method stub
		return groupBuyingDao.getGroupBuyingItem(itemId);
	}

	public void groupBuyingScheduler(Date deadLine) {
		Runnable updateTableRunner = new Runnable() {
			@Override
			public void run() {
				System.out.println("close메소드 실행");
				Date curTime = new Date();
				groupBuyingDao.closeGroupBuying(curTime); // state 변경
			}
		};
		scheduler.schedule(updateTableRunner, deadLine);

		System.out.println("GB updateTableRunner has been scheduled to execute at " + deadLine);
	}
	
	//기한 마감시 삭제
	public void closeGroupBuying(Date curTime) {
		groupBuyingDao.closeGroupBuying(curTime);
	}

	//품절시 삭제
	public void soldOutGroupBuying(int itemId) {
		groupBuyingDao.soldOutGroupBuying(itemId);
	}
	
	@Override
	public List<SecondHand> getSecondHandList(Account account) {
		return secondHandDao.getSecondHandList(account);
	}

	@Override
	public SecondHand getSecondHandItem(int itemId) {
		return secondHandDao.getSecondHandItem(itemId);
	}

	@Override
	public void insertSecondHand(SecondHand secondHand) {
		secondHandDao.insertSecondHand(secondHand);
	}

	@Override
	public void updateSecondHand(SecondHand secondHand) {
		secondHandDao.updateSecondHand(secondHand);
	}

	@Override
	public List<Auction> getAuctionList(Account account) {
		// TODO Auto-generated method stub
		return auctionDao.getAuctionList(account);
	}

	@Override
	public void insertAuction(Auction auction) {
		// TODO Auto-generated method stub
		auctionDao.insertAuction(auction);
	}

	@Override
	public void updateAuction(Auction auction) {
		// TODO Auto-generated method stub
		auctionDao.updateAuction(auction);
	}

	@Override
	public Auction getAuctionById(int itemId) {
		// TODO Auto-generated method stub
		return auctionDao.getAuctionById(itemId);
	}

	public void testScheduler(Date deadLine) {
		Runnable updateTableRunner = new Runnable() {
			@Override
			public void run() {
				Date curTime = new Date();
				auctionDao.closeAuction(curTime);

				List<Auction> auctionList = auctionDao.getAuctionList(null);
				for (int i = 0; i < auctionList.size(); i++) {
					if (auctionList.get(i).getAuctionState() == 1) {
						int unitCost = auctionList.get(i).getUnitCost();
						int itemId = auctionList.get(i).getItemId();

						auctionDao.updateBidPrice(unitCost, itemId);
					}
				}
			}
		};
		scheduler.schedule(updateTableRunner, deadLine);

		System.out.println("updateTableRunner has been scheduled to execute at " + deadLine);
	}

	public void updateAuctionUnitCost(int unitCost, int itemId) {
		auctionDao.updateAuctionUnitCost(unitCost, itemId);
	}

	public void updateBidder(String bidder, int itemId) {
		auctionDao.updateBidder(bidder, itemId);
	}

	public void insertBidder(Bidder bidder) {
		auctionDao.insertBidder(bidder);
	}

	public String isBidderExist(int itemId) {
		return auctionDao.isBidderExist(itemId);
	}

	public void updateBidPrice(int unitCost, int itemId) {
		auctionDao.updateBidPrice(unitCost, itemId);
	}

	public List<Bidder> getBidderList() {
		return auctionDao.getBidderList();
	}

	@Override
	public List<Tag> getTagList() {
		// TODO Auto-generated method stub
		return tagDao.getTagList();
	}

	@Override
	public List<Tag> getTagByTagId(int tagId) {
		// TODO Auto-generated method stub
		return tagDao.getTagByTagId(tagId);
	}

	@Override
	public List<Tag> getTagByItemId(int itemId) {
		// TODO Auto-generated method stub
		return tagDao.getTagByItemId(itemId);
	}

	@Override
	public List<Tag> getTagByTagName(String tagName) {
		// TODO Auto-generated method stub
		return tagDao.getTagByTagName(tagName);
	}

	@Override
	public void insertTag(Tag tag) {
		// TODO Auto-generated method stub
		tagDao.insertTag(tag);
	}

	@Override
	public void deleteTag(int itemId) {
		// TODO Auto-generated method stub
		tagDao.deleteTag(itemId);
	}

	@Override
	public void insertItem(Item item) {
		// TODO Auto-generated method stub
		itemDao.insertItem(item);
	}

	@Override
	public void updateItem(Item item) {
		// TODO Auto-generated method stub
		itemDao.updateItem(item);
	}

	@Override
	public Item getItem(int itemId) {
		// TODO Auto-generated method stub
		return itemDao.getItem(itemId);
	}

	@Override
	public String getUserIdByItemId(int itemId) {
		return itemDao.getUserIdByItemId(itemId);
	}

	@Override
	public void updateViewCount(int viewCount, int itemId) {
		// TODO Auto-generated method stub
		itemDao.updateViewCount(viewCount, itemId);
	}

	@Override
	public List<Item> getItemByPId(int productId) {
		// TODO Auto-generated method stub
		return itemDao.getItemByPId(productId);
	}

}