package com.secondhand.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.secondhand.controller.item.ItemForm;

@Component
public class ItemFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ItemForm.class.isAssignableFrom(clazz);
	}
    
	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("validator");
		ItemForm itemForm = (ItemForm)target; 
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "unitCost", "required");
		
		if(itemForm.getDescription().length() > 0 && itemForm.getDescription().length() < 10) {
			errors.rejectValue("description", "tooShortDesc");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "unitCost", "required");
		
		if(itemForm.getUnitCost() <= 0) {
			errors.rejectValue("unitCost", "tooSmall");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "qty", "required");
		if(itemForm.getQty() <= 0) {
			errors.rejectValue("qty", "tooSmall");
		}
		
		return;
	}

}