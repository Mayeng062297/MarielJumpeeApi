package com.jumpeeApi.springprojectapi.PRODUCT;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.jumpeeApi.springprojectapi.jsonview.View;

@Entity
@Table(name = "products")
public class Product {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="product_id")
		private long productId;
		
		@JsonView(View.Base.class)
		private String productName;
		
		@JsonView(View.Base.class)
		private String category;
		
		@JsonView(View.Base.class)
		private String brand;
		
		@JsonView(View.Base.class)
		private int price;
		
		@JsonView(View.Base.class)
		private String quantity;

		
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

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public String getQuantity() {
			return quantity;
		}

		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}
				 		
}
