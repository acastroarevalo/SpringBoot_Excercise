package com.aca.springdata.springProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aca.springdata.springProject.entities.OrderHistory;
import com.aca.springdata.springProject.repos.OrderHistoryRepository;

@RestController
@RequestMapping("/api")
public class OrderHistoryController {

	@Autowired
	private OrderHistoryRepository repository;
	
	@GetMapping("/orders")
	public List<OrderHistory> getOrders(){
		return repository.findAll();
	}
	
	@PostMapping("/orders")
	public OrderHistory saveOrder(@RequestBody OrderHistory order) {
		return repository.save(order);
	}
	
}
