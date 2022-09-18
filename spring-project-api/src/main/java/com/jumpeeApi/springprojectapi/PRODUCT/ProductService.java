package com.jumpeeApi.springprojectapi.PRODUCT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jumpeeApi.springprojectapi.error.NotFoundException;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	public List<Product> getProduct() {		//GET all products
		return productRepository.findAll();
	}
	
	public Product getProductById(long id) {	//GET products BY ID and not found exception
		return this.productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product does not exist"));
	}
	
	
	public Product save(Product product) {	//POST create product 
		return this.productRepository.save(product);
	}
	
	
	public Product updateProduct(Long id, Product product) {	//PUT PRODUCT ON SPECIFIC FIELD
	Product inDB = productRepository.getOne(id);
	inDB.setProductName(product.getProductName());
	inDB.setCategory(product.getCategory());
	inDB.setBrand(product.getBrand());
	inDB.setPrice(product.getPrice());
	inDB.setQuantity(product.getQuantity());
		return this.productRepository.save(inDB);
	}
	
	public void deleteProduct(long id) {	//deleting a product
		productRepository.deleteById(id);
	}



	
//PAGINATION AND SORTING SERVICE
	//PAGINATION max9 product per page
	public Page<Product> getProductPageMax9(Pageable page ) {
		return productRepository.findAll(page);
	}
		
	//PAGINATION sorting by ID ascending and descending
	public Page<Product> getProductSorting(Pageable page ) {
		return productRepository.findAll(page);
	}
	


	
	
//FILTERING SERVICES	
	//FILTERING by ProductName
	public List<Product> getProductByName(String productName) {
		return productRepository.findByProductName(productName);
	}
	
	//FILTERING by Category
	public List<Product> getProductByCategory(String category) {
		return productRepository.findByCategory(category);
	}

	//FILTERING by Brand
	public List<Product> getProductByBrand(String brand) {
		return productRepository.findByBrand(brand);
		
	}
	
	//FILTERING by price
	public List<Product> getProductByPrice(int price) {
		return productRepository.findByPrice(price);
	}




	 
}









/*	
//OK PAGINATION should specify the pageSize and pageNumber on request
	public Page<Product> getProductPage(int pageNumber,int pageSize ) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return productRepository.findAll(page);
	}
*/	
