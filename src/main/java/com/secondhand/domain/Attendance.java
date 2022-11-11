package com.secondhand.domain;

import java.io.Serializable;
import java.util.Date;

/*
 * Event - Attendance Domain Class
 * */
@SuppressWarnings("serial")
public class Attendance  implements Serializable {
	
	private String userId; 			// (FK) userId
	private int catId;				// (FK) catId 
	private Date attendance_date;	// 출석체크한 날짜
	
	// Constructor
	public Attendance() {
	}
	
	// getter & setter
	public Date getAttendance_date() {
		return attendance_date;
	}
	public void setAttendance_date(Date attendance_date) {
		this.attendance_date = attendance_date;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}
	
}
