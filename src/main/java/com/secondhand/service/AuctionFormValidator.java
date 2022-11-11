package com.secondhand.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.secondhand.controller.item.AuctionForm;


@Component
public class AuctionFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AuctionForm.class.isAssignableFrom(clazz);
	}
 
	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("validator");
		AuctionForm auctionForm = (AuctionForm)target; 
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startPrice", "required");
		if(auctionForm.getStartPrice() <= 0) {
			errors.rejectValue("startPrice", "tooSmall");
		}
		
		String date = auctionForm.getDate();
		String time = auctionForm.getTime();
		
		if(date == null || time == null || date.trim().isEmpty() || time.trim().isEmpty()) {
			errors.rejectValue("deadLine", "required");
		}
		
		String deadLine = auctionForm.getDate() + " " + auctionForm.getTime() + ":00";
		auctionForm.setDeadLine(deadLine);
		
		Date curTime = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String present = transFormat.format(curTime);
		
		int compare = -1;
		compare = present.compareTo(deadLine);
		
		if(auctionForm.getDate() != null && auctionForm.getTime() != null && !auctionForm.getDate().trim().isEmpty() && !auctionForm.getTime().trim().isEmpty()) {
			if(compare >= 0) {
				errors.rejectValue("deadLine", "lastDate");
			}
		}
	}

}