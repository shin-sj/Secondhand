package com.secondhand.service.facade;

import java.util.List;

import com.secondhand.domain.*;

/*
 * SecondhandFacade
 * 
 * Account / Orders / Category 
 * */
public interface SecondhandFacade {

	/////////////////////////////////////////////////////////////////////////
	/* Account */
	/////////////////////////////////////////////////////////////////////////
	Account getAccountByUserId(String userId);

	Account getAccountByUserIdAndPassword(String userId, String password);

	void insertAccount(Account account);

	void updateAccount(Account account);
	
	Account getAccountByUsername(String username);

	/////////////////////////////////////////////////////////////////////////
	/* Category */
	/////////////////////////////////////////////////////////////////////////
	List<Category> getCategoryList();

	Category getCategory(int categoryId);
	

	/////////////////////////////////////////////////////////////////////////
	/* Orders */
	/////////////////////////////////////////////////////////////////////////
	List<Orders> getOrdersByUserId(String userId);
	
	List<Orders> getOrdersByUsername(String username);

	Orders getOrder(int orderId);
	
	void insertOrder(Orders order);
}