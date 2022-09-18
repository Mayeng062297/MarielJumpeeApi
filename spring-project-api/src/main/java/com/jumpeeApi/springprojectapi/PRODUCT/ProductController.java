package com.jumpeeApi.springprojectapi.PRODUCT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.jumpeeApi.springprojectapi.jsonview.View;


@RestController
public class ProductController {

	 @Autowired
	 ProductService productService;
	 
	@GetMapping("/admin-access")	
	@PreAuthorize("hasRole('ROLE_admin')")	//FOR SPRING SECURITY AS ADMIN ACCESS
	public String securedAdmin() {
		return "Hi Admin, Welcome to Admin Page";
	}
	
	@GetMapping("/display-product")	//GET METHOD DISPLAY ALL PRODUCT
	@JsonView(View.Base.class)
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
		return "Hi admin, You just added a product";
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

	
	
//PAGINATION CONTROLLER	
	//PAGINATION max 9 product per page
	@GetMapping("/display-product-max9")	
	Page<Product> getProductPageMax9(@PageableDefault(sort = "productId", size = 9)Pageable page){
		return productService.getProductPageMax9(page);
	}
	
	
	//PAGINATION sorting by ID ascending and descending, should specify the pageNumber,sort by productId,asc or desc  on request
	@GetMapping("/product-sorting")	
	Page<Product> getProductPage(@PageableDefault( size = 9)Pageable page){
		return productService.getProductSorting(page);
	}
	
	
	
//FILTERING CONTROLLER by Name, Category, Brand and price		
	//FILTERING by ProductName
	@GetMapping("/product/name/{productName}")
	public List<Product> getProductByName(@PathVariable String productName) {
	return productService.getProductByName(productName);
	}
	
	//FILTERING by Category
	@GetMapping("/product/category/{category}")
	public List<Product> getProductByCategory(@PathVariable String category) {
	return productService.getProductByCategory(category);
	}	
	
	//FILTERING by Brand
	@GetMapping("/product/brand/{brand}")
	public List<Product> getProductByBrand(@PathVariable String brand) {
	return productService.getProductByBrand(brand);
	}	
		
	//FILTERING by price
	@GetMapping("/product/price/{price}")
	public List<Product> getProductByPrice(@PathVariable int price) {
		return productService.getProductByPrice(price);
	}
		
}








/*
//OK PAGINATION should specify the pageSize and pageNumber on request
@GetMapping("/display-product-pagination1")	
Page<Product> getProductPage(@RequestParam int pageSize, @RequestParam(required = false, defaultValue ="0") int pageNumber )
//Page<Product> getProductPage(Pageable page)
{
	return productService.getProductPage(pageNumber,pageSize);
	//return productService.getProductPage(page);
}
*/	
