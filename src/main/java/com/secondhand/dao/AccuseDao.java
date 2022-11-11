package com.secondhand.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.secondhand.domain.Accuse;

public interface AccuseDao {

	void insertAccuse(Accuse accuse) throws DataAccessException;
	
	int countAccuseById(String accountId) throws DataAccessException;
	
	String isAccuseAlready(String attacker, String victim) throws DataAccessException;

	List<Accuse> selectAccuseList(String victim) throws DataAccessException;
}
