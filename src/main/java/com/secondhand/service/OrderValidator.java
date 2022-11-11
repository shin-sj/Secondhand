package com.secondhand.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.secondhand.domain.Orders;


@Component
public class OrderValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Orders.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		validateCreditCard((Orders) obj, errors);
		validateBillingAddress((Orders) obj, errors);
		validateShippingAddress((Orders) obj, errors);
	}

	public void validateCreditCard(Orders order, Errors errors) {
		errors.setNestedPath("order");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creditCard", "CCN_REQUIRED", "FAKE (!) credit card number required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expiryDate", "EXPIRY_DATE_REQUIRED", "Expiry date is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardType", "CARD_TYPE_REQUIRED", "Card type is required.");
		errors.setNestedPath("");
	}

	public void validateBillingAddress(Orders order, Errors errors) {
		errors.setNestedPath("order");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billToUsername", "FIRST_NAME_REQUIRED", "Billing Info: name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billAddr1", "ADDRESS_REQUIRED", "Billing Info: address1 is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billAddr2", "ADDRESS_REQUIRED", "Billing Info: address2 is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billCity", "CITY_REQUIRED", "Billing Info: city is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billZip", "ZIP_REQUIRED", "Billing Info: zip/postal code is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billCountry", "COUNTRY_REQUIRED", "Billing Info: country is required.");
		errors.setNestedPath("");
	}

	public void validateShippingAddress(Orders order, Errors errors) {
		errors.setNestedPath("order");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipToUsername", "FIRST_NAME_REQUIRED", "Shipping Info: name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipAddr1", "ADDRESS_REQUIRED", "Shipping Info: address is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipAddr2", "ADDRESS_REQUIRED", "Shipping Info: address is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipCity", "CITY_REQUIRED", "Shipping Info: city is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipZip", "ZIP_REQUIRED", "Shipping Info: zip/postal code is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipCountry", "COUNTRY_REQUIRED", "Shipping Info: country is required.");
		errors.setNestedPath("");
	}
}