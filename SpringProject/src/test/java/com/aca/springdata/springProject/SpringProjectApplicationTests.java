package com.aca.springdata.springProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.aca.springdata.springProject.entities.OrderHistory;
import com.aca.springdata.springProject.entities.Product;
import com.aca.springdata.springProject.entities.User;
import com.aca.springdata.springProject.repos.OrderHistoryRepository;
import com.aca.springdata.springProject.repos.ProductRepository;
import com.aca.springdata.springProject.repos.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringProjectApplicationTests {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderHistoryRepository orderRepository;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void testCreateUser() {
		User user = new User();
		//user.setUserId(1);
		user.setName("Al");
		user.setLastName("Acorn");
		user.setEmail("acorns@gmail.com");
		user.setBio("aac");
		user.setAreaOfInterest("acorns");
		userRepository.save(user);
	}
	
	@Test
	public void testDeleteUser() {
		userRepository.deleteById(BigDecimal.valueOf(1));
	}
	
	@Test
	public void testCreateProduct() {
		Product product = new Product();
		product.setName("Apple");
		product.setPrice(5d);
		product.setDescription("Red");
		product.setTotalProductInventory(1);
		product.setStatus(true);
		
		File file = new File("C:\\Users\\acastroarevalo\\Documents\\Notes\\blue.png");
		byte fileContent[] = new byte[(int)file.length()];
		
		try {
			FileInputStream inputStream = new FileInputStream(file);
			inputStream.read(fileContent);
			product.setImage(fileContent);
			inputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Product> names = productRepository.findByName(product.getName());
		if (names.isEmpty()) {
			productRepository.save(product);
		}else {
			System.out.println("----------------------------------------------\n"
					+ "EXISTING PRODUCT\n"
					+ "----------------------------------------------");
			Product existing_product = productRepository.findByName(product.getName()).get(0);
			long newInv = existing_product.getTotalProductInventory() + 1;
			existing_product.setTotalProductInventory(newInv);
			productRepository.save(existing_product);
		}
	}
	
	
	
	/*@Test
	public void testCreateOrder() {
		OrderHistory order = new OrderHistory();
		Timestamp time = new Timestamp(new Date().getTime());
		order.setOrderDate(time);
		order.setUser(userRepository.findByUserId(BigDecimal.valueOf(1)).get(0));
		order.setProduct(productRepository.findByProductId(BigDecimal.valueOf(5)).get(0));
		
		orderRepository.save(order);
	}*/
}
