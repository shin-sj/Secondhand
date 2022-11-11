package com.secondhand.dao;

import org.springframework.dao.DataAccessException;

public interface SequenceDao {
	// 시퀀스 받아오기
	public int getSequence(String name) throws DataAccessException;
	
	// 시퀀스 수정하기
	public void updateSequence(int nextId, int name) throws DataAccessException;
}
