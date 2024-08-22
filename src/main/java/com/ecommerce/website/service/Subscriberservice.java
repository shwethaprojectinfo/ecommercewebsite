package com.ecommerce.website.service;

import org.springframework.stereotype.Service;

import com.ecommerce.website.model.Subscription;
import com.ecommerce.website.request.SubscriptionRequest;

public interface Subscriberservice {

	public Subscription save(SubscriptionRequest subscriptionRequest);

}
