package com.ecommerce.website.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.website.model.SubscriberEmail;

public interface EmailRepo  extends JpaRepository<SubscriberEmail, Long>{
	
	
//	public String findByEmail(@RequestBody SubscriberEmail subscriber);
	Boolean existsByEmail(String email);
}
