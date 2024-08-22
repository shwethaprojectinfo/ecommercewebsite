package com.ecommerce.website.service;

import javax.validation.Valid;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import com.ecommerce.website.model.SubscriberEmail;
import com.ecommerce.website.request.EmailRequest;

@Component
public interface EmailService {

	public void sendEmail(String recipient, String subject, String body);

	public void sendEmailToUser(String ownerEmail, String subject, String body);

	public void sendEmailToOwner(String userEmail, String subject, String body);

	public SubscriberEmail save(EmailRequest emailRequest);
	
	public void sendEmailToUserAndOwner(String userEmail, String ownerEmail, String subject, String body);
}
