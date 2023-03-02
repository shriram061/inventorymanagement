package com.einstein;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import com.einstein.model.Product;
import com.einstein.model.ProductStatus;
import com.einstein.repository.ProductRepository;
import com.einstein.repository.UserRepository;
import com.einstein.service.ProductServiceImpl;
//import static org.junit.Assert.*;

@SpringBootTest
class InventoryManagementApplicationTests {

	 @Mock
	    private ProductRepository productRepository;

	    @Mock
	    private UserRepository userRepository;

	    @Mock
	    private JavaMailSender javaMailSender;

	    @InjectMocks
	    private ProductServiceImpl productService;

	    @Before(value = "")
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);
	    }
	    @Test
	    public void testSaveProductWithEmptyQuantity() {
	        Product product = new Product();
	        product.setProductId(1);
	        product.setProductName("Product 1");
	        product.setPrice(100);
	        product.setQuantity(5);
	        product.setValidity("2-2-2032");
	        when(productRepository.save(any(Product.class))).thenReturn(product);

	        Product savedProduct = productService.saveProduct(product);

	        
	        assertNotNull(savedProduct);
	        assertEquals(ProductStatus.INSTOCK, savedProduct.getStatus());
	    }

	    @Test
	    public void testGetAllProduct() {
	        when(productRepository.findAll()).thenReturn(Arrays.asList(new Product(), new Product()));

	        List<Product> productList = productService.getAllProduct();

	        assertNotNull(productList);
	        assertEquals(2, productList.size());
	    }
	    
	    @Test
	    public void testUpdateProduct() {
	        Product product = new Product();
	        product.setProductId(1);
	        product.setProductName("Product 1");
	        product.setPrice(1000);
	        product.setQuantity(6);
	        product.setValidity("5-5-2024");
	        when(productRepository.findByProductId(1)).thenReturn(product);
	        when(productRepository.save(any(Product.class))).thenReturn(product);

	        Product updatedProduct = productService.updateProduct(product, 1);

	        assertNotNull(updatedProduct);
	        assertEquals(ProductStatus.INSTOCK, updatedProduct.getStatus());
	    }
	@Test
	void applicationStarts() throws IOException {
		InventoryManagementApplication.main(new String[] {});
		assertTrue(true);
	}

}
