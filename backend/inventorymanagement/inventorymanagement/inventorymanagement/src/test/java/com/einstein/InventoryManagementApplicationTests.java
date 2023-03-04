package com.einstein;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;
import java.util.ArrayList;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.einstein.model.Product;
import com.einstein.model.ProductStatus;
import com.einstein.model.User;
import com.einstein.repository.ProductRepository;
import com.einstein.repository.UserRepository;
import com.einstein.service.ProductServiceImpl;
//import static org.junit.Assert.*;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;

@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
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
	    public void testDeleteProduct() {
	        
	        Product product = new Product();
	        product.setProductId(1);
	        when(productRepository.findById(1)).thenReturn(Optional.of(product));
	        productService.deleteProduct(1);
	        verify(productRepository, times(1)).deleteById(1);
	    }
	    
	    @Test
	    public void testSendEmail() {
	        // Set up test data
	        Product product = new Product();
	        product.setProductName("Test Product");
	        product.setPrice(10);
	        product.setQuantity(1);
	        product.setStatus(ProductStatus.LOWSTOCK);

	        User user = new User();
	        user.setUserName("Test User");
	        user.setEmailId("subashriram13@gmail.com");

	        // Set up mock behavior
	        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));

	        // Call the method
	        productService.sendEmail(product);

	        // Verify that the send method of JavaMailSender is called with the expected message
	        ArgumentCaptor<SimpleMailMessage> argument = ArgumentCaptor.forClass(SimpleMailMessage.class);
	        Mockito.verify(javaMailSender).send(argument.capture());
	        SimpleMailMessage mailMessage = argument.getValue();
	        assertEquals(user.getEmailId(), mailMessage.getTo()[0]);
	        assertEquals("Product Low Stock Reminder", mailMessage.getSubject());
	        assertEquals("Dear " + user.getUserName() + ",\n\n" +
	                "Product Named:- " + product.getProductName() +
	                ", Pricing: ₹" + product.getPrice() +
	                " with quantity: " + product.getQuantity() +
	                " and therefore the status of the product is " + product.getStatus() +
	                ". Please reorder the product, to gain the stocks.", mailMessage.getText());
	    }
	    @Test
	    public void testListAll() {
	        MockitoAnnotations.initMocks(this);
	        List<Product> expectedProducts = new ArrayList<>();
	        Product product1 = new Product();
	        product1.setProductId(1);
	        product1.setProductName("Product 1");
	        product1.setPrice(10);
	        product1.setQuantity(5);
	        expectedProducts.add(product1);
	        Product product2 = new Product();
	        product2.setProductId(2);
	        product2.setProductName("Product 2");
	        product2.setPrice(20);
	        product2.setQuantity(8);
	        expectedProducts.add(product2);
	        when(productRepository.findAll()).thenReturn(expectedProducts);
	        List<Product> actualProducts = productService.listAll(null);
	        assertEquals(expectedProducts, actualProducts);
	        when(productRepository.search(ArgumentMatchers.anyString())).thenReturn(expectedProducts);
	        actualProducts = productService.listAll("test");
	        assertEquals(expectedProducts, actualProducts);
	    }

	    
	@Test
	void applicationStarts() throws IOException {
		InventoryManagementApplication.main(new String[] {});
		assertTrue(true);
	}

}
