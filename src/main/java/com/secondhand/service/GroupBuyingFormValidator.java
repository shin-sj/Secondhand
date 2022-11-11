package com.secondhand.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.secondhand.controller.item.GroupBuyingForm;


@Component
public class GroupBuyingFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return GroupBuyingForm.class.isAssignableFrom(clazz);
	}
 
	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("validator");
		GroupBuyingForm groupBuyingForm = (GroupBuyingForm)target; 
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "listPrice", "required");
		if(groupBuyingForm.getListPrice() <= 0) {
			errors.rejectValue("listPrice", "tooSmall");
		}
		
		String date = groupBuyingForm.getDate();
		String time = groupBuyingForm.getTime();
		
		if(date == null || time == null || date.trim().isEmpty() || time.trim().isEmpty()) {
			errors.rejectValue("deadLine", "required");
		}
		
		String deadLine = groupBuyingForm.getDate() + " " + groupBuyingForm.getTime() + ":00";
		groupBuyingForm.setDeadLine(deadLine);
		
		Date curTime = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String present = transFormat.format(curTime);
		
		int compare = -1;
		compare = present.compareTo(deadLine);
		
		if(groupBuyingForm.getDate() != null && groupBuyingForm.getTime() != null && !groupBuyingForm.getDate().trim().isEmpty() && !groupBuyingForm.getTime().trim().isEmpty()) {
			if(compare >= 0) {
				errors.rejectValue("deadLine", "lastDate");
			}
		}
	}

}