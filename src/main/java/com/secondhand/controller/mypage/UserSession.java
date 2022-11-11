package com.secondhand.controller.mypage;

import java.io.Serializable;
import org.springframework.beans.support.PagedListHolder;

import com.secondhand.domain.Account;
import com.secondhand.domain.Product;


@SuppressWarnings("serial")
public class UserSession implements Serializable {

	private Account account;

	private PagedListHolder<Product> myList;

	public UserSession(Account account) {
		this.account = account;
	}

	public Account getAccount() {
		return account;
	}

	public void setMyList(PagedListHolder<Product> myList) {
		this.myList = myList;
	}

	public PagedListHolder<Product> getMyList() {
		return myList;
	}

	@Override
	public String toString() {
		return "UserSession [account=" + account + ", myList=" + myList + "]";
	}
	
	
}
