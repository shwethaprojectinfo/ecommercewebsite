package com.ecommerce.website.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.website.model.Subscription;
import com.ecommerce.website.repo.EmailRepo;
import com.ecommerce.website.repo.SubscriptionRepo;
import com.ecommerce.website.request.EmailRequest;
import com.ecommerce.website.request.SubscriptionRequest;
import com.ecommerce.website.service.EmailService;
import com.ecommerce.website.service.Subscriberservice;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/Subscribe")
//@Configuration
public class SubscriptionController{

	@Autowired
	private SubscriptionRepo subscriptionRepo;

	@Autowired
	private Subscriberservice subscriberService;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private EmailRepo emailRepo;

	@Autowired
	private EmailService emailService;
	
	

	@PostMapping("/signup")
	public ResponseEntity<Object> addSubscriber(@Valid @RequestBody SubscriptionRequest subscriptionRequest)
			throws MessagingException {

		Map<String, Object> response = new HashMap<String, Object>();

		if (subscriptionRepo.existsByEmail(subscriptionRequest.getEmail())) {
			return ResponseEntity.badRequest().body("{\"error\": \"Email already exists\"}");
		}
		if (subscriptionRepo.existsByMobilenumber(subscriptionRequest.getMobilenumber())) {
			return ResponseEntity.badRequest().body("{\"error\": \"Mobilenumber already exists\"}");
		}

		subscriberService.save(subscriptionRequest);

		// Send Email to Customer
		if (Objects.nonNull(subscriptionRequest.getEmail())
				&& (Objects.nonNull(subscriptionRequest.getMobilenumber()))) {

			String from = "smartclothingfahsionwebsite@gmail.com";
			String to = subscriptionRequest.getEmail().toString();

			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				helper.setSubject("Welcome To SmartClothing");
				helper.setFrom(from);
				helper.setTo(to);
				// boolean html = true;
				// helper.setText("<b>Hello</b>" + " " + "<h3>" + subscriptionRequest.getName()+
				// "</h3>" + " "+ "<br>" + "</br><b>You are Signup successfully Completed</b>",
				// html);

				String content = "<b>Hello</b>" + " " + "<h3>" + subscriptionRequest.getName() + "</h3>" + "  " + "<br>"
						+ "</br><b>You are Signup successfully to our website SmartClothing</b>"
						+ "<br><img src='https://scontent.fblr4-4.fna.fbcdn.net/v/t1.6435-9/118919986_118931733269261_8484734847285632777_n.jpg?stp=cp0_dst-jpg_e15_p320x320_q65&_nc_cat=104&ccb=1-7&_nc_sid=85a577&_nc_ohc=SPSXhjKja2cAX-XAwr1&_nc_ht=scontent.fblr4-4.fna&oh=00_AfD7Z8kZvIg-Y-zZwzHTvGEjZ496NjgGYGL0nt3EOdcMTg&oe=64D493CD'/>";
				helper.setText(content, true);
				mailSender.send(message);
			} catch (MailSendException e) {
				e.printStackTrace();
				response.put("Email", "Failed");
				return ResponseEntity.ok().body(response);
			}
		} else {
			response.put("Email and Mobile", "Email and Mobile Number Must not be Blank! ");
			return ResponseEntity.ok().body(response);
		}

		// Send Email to Designer
		if (Objects.nonNull(subscriptionRequest.getEmail())
				&& (Objects.nonNull(subscriptionRequest.getMobilenumber()))) {

			String from = "smartclothingfahsionwebsite@gmail.com";
			String to = "shwe79780@gmail.com";

			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				helper.setSubject("New Customer Signed Up to our Website");
				helper.setFrom(from);
				helper.setTo(to);
				String content = "<b>Customer Details</b>" + "<br>" + "<i>" + subscriptionRequest.getName() + "</i>"
						+ "<br>" + "<i>" + subscriptionRequest.getMobilenumber() + "</i>" + "<br>" + "<i>"
						+ subscriptionRequest.getEmail() + "</i>" + "<br>"
						+ "<br><img src='https://scontent.fblr4-4.fna.fbcdn.net/v/t1.6435-9/118919986_118931733269261_8484734847285632777_n.jpg?stp=cp0_dst-jpg_e15_p320x320_q65&_nc_cat=104&ccb=1-7&_nc_sid=85a577&_nc_ohc=SPSXhjKja2cAX-XAwr1&_nc_ht=scontent.fblr4-4.fna&oh=00_AfD7Z8kZvIg-Y-zZwzHTvGEjZ496NjgGYGL0nt3EOdcMTg&oe=64D493CD'/>";
				helper.setText(content, true);

				mailSender.send(message);

			} catch (MailSendException e) {
				e.printStackTrace();
				response.put("Email", "Failed");
				return ResponseEntity.ok().body(response);
			}

		} else {
			response.put("Email and Mobile", "Email and Mobile Number Must not be Blank! ");
			return ResponseEntity.ok().body(response);
		}

		response.put("signup", "Success");

		return ResponseEntity.ok().body(response);

	}

	// ------------------------------------------------------------------------
//
//	@PostMapping("/emailSubscription")
//	public ResponseEntity<Map<String, Object>> findByEmail(@RequestBody Subscription subscriber)
//			throws MessagingException, UnsupportedEncodingException {
//
//		String from = "smartclothingfahsionwebsite@gmail.com";
//		String to = subscriber.getEmail().toString();
//
//		MimeMessage message = mailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(message);
//
//		helper.setSubject("This is an HTML email");
//		helper.setFrom(from);
//		helper.setReplyTo("82966shwe@gmail.com", to);
//		helper.setTo(from);
//		boolean html = true;
//		helper.setText("<b>Hello </b>,</br><i>Welcome to my new website jingalala</i>", html);
//		mailSender.send(message);
//
//		Map<String, Object> response = new HashMap<String, Object>();
//		response.put("check", "ghdsfhgsd");
//
//		return ResponseEntity.ok().body(response);
//	}

	
	
	
	
	@PostMapping("/subscription")
	public ResponseEntity<Object> addSubscriber(@Valid @RequestBody EmailRequest emailRequest)
			throws MessagingException {

		if (emailRepo.existsByEmail(emailRequest.getEmail())) {
			return new ResponseEntity<>("Email already exist", HttpStatus.BAD_REQUEST);
//			return ResponseEntity.badRequest().body("{\"error\": \"Email already exists\"}");
		}
		emailService.save(emailRequest);

		if (Objects.nonNull(emailRequest.getEmail())) {

			String from = "smartclothingfahsionwebsite@gmail.com";
			String to = emailRequest.getEmail().toString();

			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				helper.setSubject("Welcome To SmartClothing");
				helper.setFrom(from);
				helper.setTo(to);

				String content = "<b>Hello</b>" + " " + "<h3>" + emailRequest.getEmail() + "</h3>" + "  " + "<br>"
						+ "</br><b>You are Successfully Subscribe to our website SmartClothing</b>"+"  " + "<br>"+"</br><b>Thank You for visiting our Website</b>"
						+ "<br><img src='https://scontent.fblr4-4.fna.fbcdn.net/v/t1.6435-9/118919986_118931733269261_8484734847285632777_n.jpg?stp=cp0_dst-jpg_e15_p320x320_q65&_nc_cat=104&ccb=1-7&_nc_sid=85a577&_nc_ohc=SPSXhjKja2cAX-XAwr1&_nc_ht=scontent.fblr4-4.fna&oh=00_AfD7Z8kZvIg-Y-zZwzHTvGEjZ496NjgGYGL0nt3EOdcMTg&oe=64D493CD'/>";
				helper.setText(content, true);
				mailSender.send(message);
			} catch (MailSendException e) {
				e.printStackTrace();
				
//				response.put("Email sent", "Failed");
//				return ResponseEntity.badRequest().body("{\"error\": \"Email sent failed\"}");
				return new ResponseEntity<>("Failed to sent Email", HttpStatus.BAD_REQUEST);
			}
		} else {
//			response.put("Email ", "Email Must not be Blank! ");
			return new ResponseEntity<>("Email Must not be Blank!", HttpStatus.BAD_REQUEST);
//			return ResponseEntity.badRequest().body("{\"error\": \"Email must not be Blank\"}");
		}

		// Send Email to Designer
		if (Objects.nonNull(emailRequest.getEmail())) {

			String from = "smartclothingfahsionwebsite@gmail.com";
			String to = "shwe79780@gmail.com";

			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				helper.setSubject("New Customer Subscribe to our Website");
				helper.setFrom(from);
				helper.setTo(to);
				String content = "<b>Customer Details</b>" + "<br>" + "<i>" + emailRequest.getEmail() + "</i>" + "<br>"
						+ "</i>"
						+ "<br><img src='https://scontent.fblr4-4.fna.fbcdn.net/v/t1.6435-9/118919986_118931733269261_8484734847285632777_n.jpg?stp=cp0_dst-jpg_e15_p320x320_q65&_nc_cat=104&ccb=1-7&_nc_sid=85a577&_nc_ohc=SPSXhjKja2cAX-XAwr1&_nc_ht=scontent.fblr4-4.fna&oh=00_AfD7Z8kZvIg-Y-zZwzHTvGEjZ496NjgGYGL0nt3EOdcMTg&oe=64D493CD'/>";
				helper.setText(content, true);
				mailSender.send(message);

			} catch (MailSendException e) {
				e.printStackTrace();
//				response.put("Email sent", "Failed");
				return new ResponseEntity<>("Failed to sent Email", HttpStatus.BAD_REQUEST);
//				return ResponseEntity.badRequest().body("{\"error\": \"Email sent failed\"}");
				
			}

		} else {
//			response.put("Email", "Email Must not be Blank! ");
			return new ResponseEntity<>("Email Must not be Blank!", HttpStatus.BAD_REQUEST);
//			return ResponseEntity.badRequest().body("{\"error\": \"Email must not be Blank\"}");
		}

		Map<String, String> response = new HashMap<>();
		response.put("Success", "Subscription Successful!");

		return ResponseEntity.ok().body(response);
//		return ResponseEntity.badRequest().body("{\"error\": \"Thank You For Subscribing\"}");

	}
	
	
	
	
}
