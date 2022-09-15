package com.jumpeeApi.springprojectapi.Address;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AddressController {

	  @Autowired
	  AddressService addressService;
	  

	  @GetMapping("/display-address")	//GET METHOD DISPLAY ALL ADDRESS
		List<Address> getAddress() {
			return addressService.getAddress();
		}
	  			
		@GetMapping("/display-address/{id}")	//GET METHOD DISPLAY ADDRESS BY ID
		public Address getAddress(@PathVariable long id) {
			return this.addressService.getAddressById(id);
		}
		
		
		@PostMapping("/add-address")	//POST METHOD ADD ADDRESS
		public String createAddress(@RequestBody Address address)
		{
			addressService.save(address);		
			return "Hi, You added an address";
		}
		
		
		@PutMapping("/update-address/{id}")	//PUT METHOD for updating specific field on address table
		public Address updateAddress(@RequestBody Address address, @PathVariable long id) 
		{	
		return this.addressService.updateAddress(id, address); 	//response complete user details
		}
		
		@DeleteMapping("/delete-address/{id}")	//DELETE METHOD Address ID deletion 
		public String deleteAddress(@PathVariable long id) {
		addressService.deleteAddress(id);
		return "Delete address with the id:" +id;
		}
}



		/*
		@PostMapping("/add-address/{id}")	//POST METHOD ADD ADDRESS BY ID TRY LANG
		public String createAddress(@PathVariable long id, @RequestBody Address address)
		{
			addressService.save(address,id);		
			return "Hi, You added an address";
		}
		*/
		
		

		





		/*
		
//OLD
		  @GetMapping("/displayAddress")
		  public List<User> findAllOrders() {
			  return userRepository.findAll();
		  }
			

	  
	  @PostMapping("/addAddress")
	 public User addAddress(@RequestBody UserAddressDTO request ){
	    return userRepository.save(request.getUser());
	  }
	  
}	  
	  /*
	  @GetMapping("/api/1.0/articles")
	  List<Address> getAddress(){
	    return addressService.getAllAddress();
	  }

	  

	  @DeleteMapping("/api/1.0/articles/{id}")
	  void deleteAddress(@PathVariable long id){
		  addressService.deleteAddress(id);
	  }
	  
*/
