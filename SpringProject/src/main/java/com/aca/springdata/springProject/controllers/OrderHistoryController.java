package com.aca.springdata.springProject.controllers;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aca.springdata.springProject.entities.OrderHistory;
import com.aca.springdata.springProject.entities.Product;
import com.aca.springdata.springProject.repos.OrderHistoryRepository;
import com.aca.springdata.springProject.repos.ProductRepository;
import com.aca.springdata.springProject.repos.UserRepository;

@RestController
@RequestMapping("/api")
public class OrderHistoryController {

	@Autowired
	private OrderHistoryRepository repository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/orders")
	public List<OrderHistory> getOrders(){
		return repository.findAll();
	}
	
	@PostMapping("/orders/{userId}/{productId}")
	public OrderHistory saveOrder(@PathVariable("userId") int userId, @PathVariable("productId") int productId) {
		
		OrderHistory order = new OrderHistory();
		Timestamp time = new Timestamp(new Date().getTime());
		Product buyItem = productRepository.findByProductId(BigDecimal.valueOf(productId)).get(0);
		
		if (buyItem.getTotalProductInventory() > 0) {
			/*SimpleDateFormat dateFormat = new SimpleDateFormat();
			String strTime = dateFormat.format(time);*/
			order.setOrderDate(time);
			order.setUser(userRepository.findByUserId(BigDecimal.valueOf(userId)).get(0));
			order.setProduct(buyItem);
			buyItem.setTotalProductInventory(buyItem.getTotalProductInventory() - 1);
			return repository.save(order);
		}else {
			System.out.printf("----------------------------------------------\n"
					+ "NO MORE PRODUCT (%s) IN STOCK\n"
					+ "----------------------------------------------\n", buyItem.getName());
			return null;
		}
		
	}
	
	@DeleteMapping("/orders/{id}")
	public void deleteOrder(@PathVariable("id") BigDecimal id) {
		repository.deleteById(id);
	}
	
}
