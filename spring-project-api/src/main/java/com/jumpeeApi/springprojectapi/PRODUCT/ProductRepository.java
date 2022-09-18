package com.jumpeeApi.springprojectapi.PRODUCT;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long>{

	
//filtering finding field	
public List<Product> findByProductName(String productName);
public List<Product> findByCategory(String category);
public List<Product> findByBrand(String brand);
public List<Product> findByPrice(int price);
	
}
