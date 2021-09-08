package com.blogs.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.blogs.model.User;

public class UserValidator implements Validator{
	@Override
	public boolean supports(Class<?> classZ){
		return User.class.equals(classZ);
	}
	
	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "username", "user.username.empty");
		ValidationUtils.rejectIfEmpty(e, "name", "user.name.empty");
		ValidationUtils.rejectIfEmpty(e, "email", "user.email.empty");
		ValidationUtils.rejectIfEmpty(e, "password", "user.password.empty");
		ValidationUtils.rejectIfEmpty(e, "retypePassword", "user.retypePassword.empty");
		User user = (User) obj;
		if (user.getUsername().length()<5) {
			e.rejectValue("username", "user.username.short");
		}
		if (user.getName().length()<5) {
			e.rejectValue("name", "user.name.short");
		}
		if (user.getPassword().length()<6 || user.getPassword().length()>64) {
			e.rejectValue("password", "user.password.length");
		}
		if (!user.getRetypePassword().equals(user.getPassword())) {
			e.rejectValue("retypePassword", "user.retypePassword.not.equal.password");
		}
	}
}
