package com.secondhand.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.secondhand.domain.Account;

public interface AccountDao {

	// userId로 Account 가져오기
	Account getAccountByUserId(String userId) throws DataAccessException;
	
	// username으로 Account 가져오기
	Account getAccountByUsername(String username) throws DataAccessException;
	
	// userId랑 password로 Account 가져오기
	Account getAccountByUserIdAndPassword(String userId, String password) throws DataAccessException;
	
	// 계정 추가
	void insertAccount(Account account) throws DataAccessException;
	
	// 계정 수정
	void updateAccount(Account account) throws DataAccessException;
}
