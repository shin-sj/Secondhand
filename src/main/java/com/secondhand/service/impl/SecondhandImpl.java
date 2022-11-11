package com.secondhand.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.secondhand.dao.*;
import com.secondhand.domain.*;
import com.secondhand.service.facade.SecondhandFacade;

/*
 * SecondhandImpl
 * 
 * Account / Orders / Category
 * */

@Service
@Transactional
public class SecondhandImpl implements SecondhandFacade { 
	
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private OrderDao orderDao;

	@Override
	public Account getAccountByUserId(String userId) {
		// TODO Auto-generated method stub
		return accountDao.getAccountByUserId(userId);
	}

	@Override
	public Account getAccountByUserIdAndPassword(String userId, String password) {
		// TODO Auto-generated method stub
		return accountDao.getAccountByUserIdAndPassword(userId, password);
	}

	@Override
	public Account getAccountByUsername(String username) {
		// TODO Auto-generated method stub
		return accountDao.getAccountByUsername(username);
	}

	@Override
	public void insertAccount(Account account) {
		// TODO Auto-generated method stub
		accountDao.insertAccount(account);
	}

	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub
		accountDao.updateAccount(account);
	}

	@Override
	public List<Category> getCategoryList() {
		// TODO Auto-generated method stub
		return categoryDao.getCategoryList();
	}

	@Override
	public Category getCategory(int categoryId) {
		// TODO Auto-generated method stub
		return categoryDao.getCategory(categoryId);
	}

	@Override
	public List<Orders> getOrdersByUserId(String userId) {
		// TODO Auto-generated method stub
		return orderDao.getOrdersByUserId(userId);
	}
	
	@Override
	public Orders getOrder(int orderId) {
		// TODO Auto-generated method stub
		return orderDao.getOrder(orderId);
	}

	@Override
	public void insertOrder(Orders order) {
		//itemDao.updateQuantity(order);	    
		orderDao.insertOrder(order);
	}

	@Override
	public List<Orders> getOrdersByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	 
}