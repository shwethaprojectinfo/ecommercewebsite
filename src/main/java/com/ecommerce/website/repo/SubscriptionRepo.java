package com.ecommerce.website.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.website.model.Subscription;

public interface SubscriptionRepo extends JpaRepository<Subscription, Long>{
	
	Subscription findByEmail(String email);
	
	Boolean existsByEmail(String email);
	
	Boolean existsByMobilenumber(String phone);

}
