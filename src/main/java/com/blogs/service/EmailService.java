package com.blogs.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class EmailService implements ApplicationEventPublisherAware{
	
	private ApplicationEventPublisher publisher;
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		// TODO Auto-generated method stub
		this.publisher = publisher;
	}

}
