package com.secondhand.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.secondhand.domain.Item;
import com.secondhand.domain.Orders;

public interface ItemDao {

	// insert Item
	void insertItem(Item item);
	
	// update Item
	void updateItem(Item item);
	
	// getItem
	Item getItem(int itemId);
	
	// delete Item
	public void deleteItem(int itemId) throws DataAccessException;
	
	// update Quantity
	void updateQuantity(Orders order) throws DataAccessException;
	
	// get Quantity
	int getQuantity(int itemId, int productId) throws DataAccessException;

	// check Item in Stock
	boolean isItemInStock(int itemId, int productId) throws DataAccessException;

	String getUserIdByItemId(int itemId) throws DataAccessException;

	// ViewCount + 1
	void updateViewCount(int viewCount, int itemId);
	
	List<Item> getItemByPId(int productId); 
	
	List<Item> getItemByTitle(String title);
}
