package com.jumpeeApi.springprojectapi.PRODUCT;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="product_id")
		private long productId;
		private String productName;
		private String stock;
		private int price;
		
		/*	//NOT NEED
		//Constructor
		public Product() {}

		public Product(long productId, String productName, String stock, int price) {
			super();
			this.productId = productId;
			this.productName = productName;
			this.stock = stock;
			this.price = price;
		}
		*/

		//GETTERS AND SETTERS
		public long getProductId() {
			return productId;
		}

		public void setProductId(long productId) {
			this.productId = productId;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public String getStock() {
			return stock;
		}

		public void setStock(String stock) {
			this.stock = stock;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}			 		
}
