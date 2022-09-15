package com.jumpeeApi.springprojectapi.PRODUCT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductController {

	 @Autowired
	 ProductService productService;
	 
	@GetMapping("/admin-access")	
	@PreAuthorize("hasRole('ROLE_admin')")		//FOR SPRING SECURITY AS ADMIN ACCESS
	public String securedAdmin() {
		return "Welcome to admin page";
	}
	
	@GetMapping("/display-product")	//GET METHOD DISPLAY ALL PRODUCT
	List<Product> getProduct() {
		return productService.getProduct();
	}
  			
	@GetMapping("/display-product/{id}")	//GET METHOD DISPLAY PRODUCT BY ID
	public Product getProduct(@PathVariable long id) {
		return this.productService.getProductById(id);
	}
	
	
	@PostMapping("/add-product")	//POST METHOD ADD PRODUCT
	@PreAuthorize("hasRole('ROLE_admin')")
	public String createProduct(@RequestBody Product product)
	{
		productService.save(product);		
		return "Hi admin, You added a product";
	}
	
	
	@PutMapping("/update-product/{id}")	//PUT METHOD for updating specific or all fields on product table
	@PreAuthorize("hasRole('ROLE_admin')")
	public Product updateProduct(@RequestBody Product product, @PathVariable long id) 
	{	
	return this.productService.updateProduct(id, product); 	//response updated product details
	}
	
	@DeleteMapping("/delete-product/{id}")	//DELETE METHOD Product by ID
	@PreAuthorize("hasRole('ROLE_admin')")
	public String deleteProduct(@PathVariable long id) {
	productService.deleteProduct(id);
	return "Deleted Product with the ID: " +id;
	}	
	
	/*
	//PAGINATION
	@GetMapping("/display-product-pagination")	
	//Page<Product> getProductPage(@RequestParam int pageSize, @RequestParam(required = false, defaultValue ="0") int pageNumber)
	Page<Product> getProductPage(Pageable page){
		return productService.getProductPage(page);
	}
	*/
}
