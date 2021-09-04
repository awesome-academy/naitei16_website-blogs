package com.blogs.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.blogs.event.OnRegistrationCompleteEvent;
import com.blogs.model.User;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		this.sendEmail(event);
	}

	private void sendEmail(OnRegistrationCompleteEvent event) {
		User user = event.getUser();
		String token = event.getToken().getToken();
		String baseUrl = event.getAppUrl();

		String recipientAddress = user.getEmail();
		String subject = "Web blogs : Registration Confirmation link:";
		String confirmationUrl = baseUrl + "/register/regitrationConfirm?token=" + token;

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setSubject(subject);
		email.setText("\r\n" + confirmationUrl);
		mailSender.send(email);
	}

}
