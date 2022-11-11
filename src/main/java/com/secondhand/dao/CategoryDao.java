package com.secondhand.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.secondhand.domain.Category;

public interface CategoryDao {

	// select Category
	List<Category> getCategoryList() throws DataAccessException;

	// catId를 받아 Category 가져오기
	Category getCategory(int catId) throws DataAccessException;
}
