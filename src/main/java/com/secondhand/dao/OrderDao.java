package com.secondhand.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.secondhand.domain.Orders;

public interface OrderDao {

	// 해당 사용자의 주문 목록 가져오기 (userId)
	List<Orders> getOrdersByUserId(String userId) throws DataAccessException;
	
	// orderId 를 매개변수로 받아 해당하는 Order 객체 반환하기
	Orders getOrder(int orderId) throws DataAccessException;
	
	// Order 추가
	void insertOrder(Orders order) throws DataAccessException;
}
