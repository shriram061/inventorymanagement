package com.einstein.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.einstein.model.EmailId;
import com.einstein.model.Product;
import com.einstein.model.ProductStatus;
import com.einstein.model.User;
import com.einstein.repository.EmailRepository;
import com.einstein.repository.ProductRepository;
import com.einstein.repository.UserRepository;


@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productrepo;

	@Autowired
	UserRepository userrepo;
	@Autowired
	private EmailRepository emailIdRepository;
	@Autowired
	JavaMailSender javamailsender;
	
	
	@Override
	public Product saveProduct(Product product) {
		if (product.getQuantity() < 0) {
	        throw new IllegalArgumentException("Product quantity cannot be negative.");
	    }
		if (product.getPrice() < 0) {
	        throw new IllegalArgumentException("Product price cannot be negative.");
	        
	    }
		if (product.getQuantity() == 0) {
			product.setStatus(ProductStatus.EMPTY);
			sendEmail(product);
		} else if ((product.getQuantity() <= 4) && (product.getQuantity() > 0)) {
			product.setStatus(ProductStatus.LOWSTOCK);
			sendEmail(product);
		} else {
			product.setStatus(ProductStatus.INSTOCK);
		}
		Product item = productrepo.save(product);

		return item;
	}

	@Override
	public List<Product> getAllProduct() {
		return productrepo.findAll();
	}

	@Override
	public Product updateProduct(Product products, int id) {
		Product product = productrepo.findByProductId(id);
		product.setProductId(products.getProductId());
		product.setProductName(products.getProductName());
		if (products.getPrice() < 0) {
	        throw new IllegalArgumentException("Price cannot be negative.");
	        
	    }else {
		product.setPrice(products.getPrice());}
		if (products.getQuantity() < 0) {
	        throw new IllegalArgumentException("Quantity cannot be negative.");
	    }else {
		product.setQuantity(products.getQuantity());}
		product.setValidity(products.getValidity());
	
		if (products.getQuantity() == 0) {
			product.setStatus(ProductStatus.EMPTY);
			
		} else if ((products.getQuantity() <= 4) && (products.getQuantity() > 0)) {
			product.setStatus(ProductStatus.LOWSTOCK);
			
		} else {
			product.setStatus(ProductStatus.INSTOCK);
		}
		
		
		if (products.getQuantity() == 0) {
			
			sendEmail(product);
		} else if ((products.getQuantity() <= 4) && (products.getQuantity() > 0)) {
			
			sendEmail(product);
		} else {
			
		}

		productrepo.save(product);
		return product;

	}

	@Override
	public void deleteProduct(int id) {
		productrepo.deleteById(id);
	}

	
	@Override
	public List<Product> listAll(String keyword) {
		if (keyword != null) {
			return productrepo.search(keyword);
		}
		
		return productrepo.findAll();
	}
	@Override
	public List<Product> listAllId(int id) {
		
		if (id != 0) {
			return productrepo.searchId(id);
		}
		return productrepo.findAll();
	}
	
	public void sendEmail(Product product) {
//		EmailId emailIdObject = emailIdRepository.findById(1).orElse(null);
		for (int i = 1; i <= 100; i++) { 
//		String ownerEmail = (emailIdObject != null) ? emailIdObject.getEmailId() : null;
		
		
		User emailIdObject = userrepo.findById(i).orElse(null);
		if (emailIdObject != null) {
		String ownerEmail = (emailIdObject != null) ? emailIdObject.getEmailId() : null;

//		String ownerEmail = user.getEmailId();
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(ownerEmail);
				mailMessage.setSubject("Product Low Stock Reminder");
				mailMessage.setText("Dear " + emailIdObject.getUserName() + ",\n\n" +"Product Named:- " + product.getProductName() + ", Pricing: ₹"+product.getPrice() + " with quantity: " + product.getQuantity() + " and therefore the status of the product is "+ product.getStatus()+". Please reorder the product, to gain the stocks.");
				javamailsender.send(mailMessage);
	}}}

	@Override
	public Product getProduct(int id) {
		
		return productrepo.findByProductId(id);
	}
}
