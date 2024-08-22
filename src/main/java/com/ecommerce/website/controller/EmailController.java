//package com.ecommerce.website.controller;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import javax.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mail.MailSendException;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ecommerce.website.repo.EmailRepo;
//import com.ecommerce.website.request.EmailRequest;
//import com.ecommerce.website.service.EmailService;
//
//
//
//
//public class EmailController {
//
//	@Autowired
//	private JavaMailSender mailSender;
//
//	@Autowired
//	private EmailRepo emailRepo;
//
//	@Autowired
//	private EmailService emailService;
//
//	@PostMapping("/subscription")
//	public ResponseEntity<Object> addSubscriber(@Valid @RequestBody EmailRequest emailRequest)
//			throws MessagingException {
//
//		Map<String, Object> response = new HashMap<String, Object>();
//
//		if (emailRepo.existsByEmail(emailRequest.getEmail())) {
//			return ResponseEntity.badRequest().body("{\"error\": \"Email already exists\"}");
//		}
//		emailService.save(emailRequest);
//
//		if (Objects.nonNull(emailRequest.getEmail())) {
//
//			String from = "smartclothingfahsionwebsite@gmail.com";
//			String to = emailRequest.getEmail().toString();
//
//			try {
//				MimeMessage message = mailSender.createMimeMessage();
//				MimeMessageHelper helper = new MimeMessageHelper(message, true);
//				helper.setSubject("Welcome To SmartClothing");
//				helper.setFrom(from);
//				helper.setTo(to);
//				// boolean html = true;
//				// helper.setText("<b>Hello</b>" + " " + "<h3>" + subscriptionRequest.getName()+
//				// "</h3>" + " "+ "<br>" + "</br><b>You are Signup successfully Completed</b>",
//				// html);
//
//				String content = "<b>Hello</b>" + " " + "<h3>" + emailRequest.getEmail() + "</h3>" + "  " + "<br>"
//						+ "</br><b>You are Signup successfully to our website SmartClothing</b>"
//						+ "<br><img src='https://scontent.fblr4-4.fna.fbcdn.net/v/t1.6435-9/118919986_118931733269261_8484734847285632777_n.jpg?stp=cp0_dst-jpg_e15_p320x320_q65&_nc_cat=104&ccb=1-7&_nc_sid=85a577&_nc_ohc=SPSXhjKja2cAX-XAwr1&_nc_ht=scontent.fblr4-4.fna&oh=00_AfD7Z8kZvIg-Y-zZwzHTvGEjZ496NjgGYGL0nt3EOdcMTg&oe=64D493CD'/>";
//				helper.setText(content, true);
//				mailSender.send(message);
//			} catch (MailSendException e) {
//				e.printStackTrace();
//				response.put("Email", "Failed");
//				return ResponseEntity.ok().body(response);
//			}
//		} else {
//			response.put("Email and Mobile", "Email and Mobile Number Must not be Blank! ");
//			return ResponseEntity.ok().body(response);
//		}
//
//		// Send Email to Designer
//		if (Objects.nonNull(emailRequest.getEmail())) {
//
//			String from = "smartclothingfahsionwebsite@gmail.com";
//			String to = "shwe79780@gmail.com";
//
//			try {
//				MimeMessage message = mailSender.createMimeMessage();
//				MimeMessageHelper helper = new MimeMessageHelper(message, true);
//				helper.setSubject("New Customer Signed Up to our Website");
//				helper.setFrom(from);
//				helper.setTo(to);
//				String content = "<b>Customer Details</b>" + "<br>" + "<i>" + emailRequest.getEmail() + "</i>" + "<br>"
//						+ "</i>"
//						+ "<br><img src='https://scontent.fblr4-4.fna.fbcdn.net/v/t1.6435-9/118919986_118931733269261_8484734847285632777_n.jpg?stp=cp0_dst-jpg_e15_p320x320_q65&_nc_cat=104&ccb=1-7&_nc_sid=85a577&_nc_ohc=SPSXhjKja2cAX-XAwr1&_nc_ht=scontent.fblr4-4.fna&oh=00_AfD7Z8kZvIg-Y-zZwzHTvGEjZ496NjgGYGL0nt3EOdcMTg&oe=64D493CD'/>";
//				helper.setText(content, true);
//				mailSender.send(message);
//
//			} catch (MailSendException e) {
//				e.printStackTrace();
//				response.put("Email", "Failed");
//				return ResponseEntity.ok().body(response);
//			}
//
//		} else {
//			response.put("Email", "Email Must not be Blank! ");
//			return ResponseEntity.ok().body(response);
//		}
//
//		response.put("signup", "Success");
//
//		return ResponseEntity.ok().body(response);
//
//	}
//
////	@PostMapping("/emailForSubscription")
////	public String findByEmail(@RequestBody SubscriberEmail subscriber) throws MessagingException {
////		
////		String from = "smartclothingfahsionwebsite@gmail.com";
////		String to = subscriber.getEmail().toString();
////		 
////		MimeMessage message = mailSender.createMimeMessage();
////		MimeMessageHelper helper = new MimeMessageHelper(message);
////		 
////		helper.setSubject("This is an HTML email");
////		helper.setFrom(from);
////		helper.setTo(to);
////		helper.setFrom(to);
////		helper.setTo(from);
////		 
////		boolean html = true;
////		helper.setText("<b>Hello everyone</b>,<br><i>Welcome to my new website</i>", html);
////		 
////		mailSender.send(message);
////		return to;
////	}
//
//}
