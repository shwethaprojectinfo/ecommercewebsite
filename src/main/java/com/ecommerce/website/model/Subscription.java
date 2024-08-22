package com.ecommerce.website.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "subscription")
public class Subscription extends Time{

	private String name;
	
	@Email
	private String email;
	
	private String address;
	
//	@Size(min=0,max=10, message = "Mobile number Should be 0 to 9 and 10 Digit")
//	@Pattern(regexp="(^$|[0-9]{10})", message = "Mobile number Should be 0 to 9 and 10 Digit")
	private String mobilenumber;
	
	public Subscription() {
		super();
	}
	
	public Subscription(String name, String email, String address, String mobilenumber) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		this.mobilenumber=mobilenumber;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
