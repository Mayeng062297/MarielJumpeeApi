package com.jumpeeApi.springprojectapi.Address;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "address")
public class Address {

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name="address_id")
	  private long addressId;
	  private String address;
	  private String contactperson;
	  private String contactnumber;
	  private long user_id;
	
	public Address() {}
		 
	  
	 
	public Address(long addressId, String address, String contactperson, String contactnumber, long user_id) {
		super();
		this.addressId = addressId;
		this.address = address;
		this.contactperson = contactperson;
		this.contactnumber = contactnumber;
		this.user_id = user_id;
	}
	  
	  //getters and setters  
public long getAddressId() {
	return addressId;
}


public void setAddressId(long addressId) {
	this.addressId = addressId;
}


public String getAddress() {
	return address;
}


public void setAddress(String address) {
	this.address = address;
}


public String getContactperson() {
	return contactperson;
}


public void setContactperson(String contactperson) {
	this.contactperson = contactperson;
}


public String getContactnumber() {
	return contactnumber;
}


public void setContactnumber(String contactnumber) {
	this.contactnumber = contactnumber;
}


public long getUser_id() {
	return user_id;
}


public void setUser_id(long user_id) {
	this.user_id = user_id;
}


}

	  

	