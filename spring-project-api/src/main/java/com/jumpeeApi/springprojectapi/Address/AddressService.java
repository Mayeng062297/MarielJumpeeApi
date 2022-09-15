package com.jumpeeApi.springprojectapi.Address;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumpeeApi.springprojectapi.error.NotFoundException;
import com.jumpeeApi.springprojectapi.model.User;


@Service
public class AddressService {
	
	@Autowired
	  AddressRepository addressRepository;
	
	public List<Address> getAddress() {		//GET
		return addressRepository.findAll();
	}
	
	public Address getAddressById(long id) {	//GET BY ID
		return this.addressRepository.findById(id).orElseThrow(() -> new NotFoundException("Address does not exist"));
	}
	
	
	public Address save(Address address) {	//POST CREATE ADDRESS OK
		return this.addressRepository.save(address);
	}
	
	public Address updateAddress(Long id, Address address) {	//PUT ADDRESS 
	Address inDB = addressRepository.getOne(id);
	inDB.setAddress(address.getAddress());
	inDB.setContactperson(address.getContactperson());
	inDB.setContactnumber(address.getContactnumber());
		return this.addressRepository.save(inDB);
	}
	
	public void deleteAddress(long id) {	//DELETE 
		addressRepository.deleteById(id);
		
	}
	
	}


/*
public Address save(Address address, long id) {	//CREATE ADDRESS OK
	address.setUser_id(id);
	return this.addressRepository.save(address);
}
*/


