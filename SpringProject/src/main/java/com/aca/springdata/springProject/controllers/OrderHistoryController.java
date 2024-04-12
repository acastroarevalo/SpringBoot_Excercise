package com.aca.springdata.springProject.controllers;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aca.springdata.springProject.entities.OrderHistory;
import com.aca.springdata.springProject.repos.OrderHistoryRepository;
import com.aca.springdata.springProject.repos.UserRepository;

@RestController
@RequestMapping("/api")
public class OrderHistoryController {

	@Autowired
	private OrderHistoryRepository repository;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/orders")
	public List<OrderHistory> getOrders() throws Exception{
		if (repository.findAll().isEmpty()) throw new Exception("Empty List");
		return repository.findAll();
	}
	
	@PostMapping("/orders/{userId}")
	public OrderHistory createOrder(@PathVariable("userId") int userId, @RequestBody OrderHistory order) {
		Timestamp time = new Timestamp(new Date().getTime());
		order.setOrderDate(time);
		order.setUser(userRepository.findByUserId(BigDecimal.valueOf(userId)).get(0));
		return repository.save(order);
	}
	
	@DeleteMapping("/orders/{id}")
	public void deleteOrder(@PathVariable("id") BigDecimal id) throws Exception {
		if (repository.findById(id).isEmpty()) throw new Exception("Object Not Found");
		repository.deleteById(id);
	}
	
}
