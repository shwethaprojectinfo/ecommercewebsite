package com.ecommerce.website.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.website.model.Subscription;
import com.ecommerce.website.repo.SubscriptionRepo;
import com.ecommerce.website.request.SubscriptionRequest;
import com.ecommerce.website.service.Subscriberservice;

@Service
public class Subscriptionimpl implements Subscriberservice {

	@Autowired
	private SubscriptionRepo subscriptionRepo;
	
	 @Autowired
	    private JavaMailSender javaMailSender;

	@Override
	public Subscription save(SubscriptionRequest subscriptionRequest) {

		Subscription subscriber = new Subscription();

		subscriber.setName(subscriptionRequest.getName());
		subscriber.setEmail(subscriptionRequest.getEmail());
		subscriber.setMobilenumber(subscriptionRequest.getMobilenumber());
		subscriber.setAddress(subscriptionRequest.getAddress());
		
		subscriptionRepo.saveAndFlush(subscriber);
		
		return subscriber;
	}
	
	

}
