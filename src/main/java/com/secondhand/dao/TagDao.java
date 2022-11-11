package com.secondhand.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.secondhand.domain.Tag;


public interface TagDao {
	

	List<Tag> getTagList () throws DataAccessException;


	List<Tag> getTagByTagId(int tagId) throws DataAccessException;


	List<Tag> getTagByItemId(int itemId) throws DataAccessException;	


	List<Tag> getTagByTagName(String tagName) throws DataAccessException;	


	void insertTag(Tag tag) throws DataAccessException;

	void insertTagList(List<Tag> tags) throws DataAccessException;
	

	void deleteTag(int itemId) throws DataAccessException;
}
