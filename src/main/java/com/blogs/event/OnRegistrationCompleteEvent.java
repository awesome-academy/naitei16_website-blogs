package com.blogs.event;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.blogs.model.User;
import com.blogs.model.VerificationToken;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {
	private String appUrl;
	private Locale locale;
	private User user;
	private VerificationToken token;

	public OnRegistrationCompleteEvent(User user, Locale locale, String appUrl, VerificationToken token) {
		super(user);

		this.user = user;
		this.locale = locale;
		this.appUrl = appUrl;
		this.token = token;
	}

}