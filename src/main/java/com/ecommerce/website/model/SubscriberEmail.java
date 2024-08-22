package com.ecommerce.website.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "subscriberemail")
public class SubscriberEmail  extends Time{
	
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
