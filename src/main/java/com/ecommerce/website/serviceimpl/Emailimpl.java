package com.ecommerce.website.serviceimpl;

import com.ecommerce.website.model.SubscriberEmail;
import com.ecommerce.website.repo.EmailRepo;
import com.ecommerce.website.request.EmailRequest;
import com.ecommerce.website.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Emailimpl implements EmailService {

	@Autowired
	private EmailRepo emailRepo;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public SubscriberEmail save(EmailRequest emailRequest) {

		SubscriberEmail subscriber = new SubscriberEmail();
		subscriber.setEmail(emailRequest.getEmail());
		emailRepo.saveAndFlush(subscriber);
		return subscriber;
	}

	@Override
	public void sendEmail(String recipient, String subject, String body) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendEmailToUser(String ownerEmail, String subject, String body) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendEmailToOwner(String userEmail, String subject, String body) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendEmailToUserAndOwner(String userEmail, String ownerEmail, String subject, String body) {
		// TODO Auto-generated method stub
		
	}
	

}
