package com.einstein.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

//import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int productId;
	@Column(nullable = false)
	private String productName;
	@Column(nullable = false)
	private int price;
	@Column(nullable = false)
	private int quantity;
	@Column(nullable = false)
	private String validity ;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ProductStatus status=ProductStatus.DUMMY;
}