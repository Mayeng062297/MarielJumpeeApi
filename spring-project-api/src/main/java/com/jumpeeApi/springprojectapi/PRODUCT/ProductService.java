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
	
	//PUT PRODUCT ON SPECIFIC FIELD
	public Product updateProduct(Long id, Product product) {	
	Product inDB = productRepository.getOne(id);
	inDB.setProductName(product.getProductName());
	inDB.setStock(product.getStock());
	inDB.setPrice(product.getPrice());
		return this.productRepository.save(inDB);
	}
	
	//deleting a product
	public void deleteProduct(long id) {	
		productRepository.deleteById(id);
	}
	
	//PRODUCT PAGINATION
	public Page<Product> getProductPage(int pageNumber, int pageSize ) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		return productRepository.findAll(page);
	}
}
