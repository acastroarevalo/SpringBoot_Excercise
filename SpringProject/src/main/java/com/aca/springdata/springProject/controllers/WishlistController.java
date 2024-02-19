package com.aca.springdata.springProject.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aca.springdata.springProject.entities.Wishlist;
import com.aca.springdata.springProject.repos.ProductRepository;
import com.aca.springdata.springProject.repos.UserRepository;
import com.aca.springdata.springProject.repos.WishlistRepository;

@RestController
@RequestMapping("/api")
public class WishlistController {

	@Autowired
	private WishlistRepository repository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/wishlist")
	public List<Wishlist> getWishlist(){
		return repository.findAll();
	}
	
	@PostMapping("/wishlist/{userId}/{productId}")
	public Wishlist saveOrder(@PathVariable("userId") int userId, @PathVariable("productId") int productId) {
		Wishlist wish = new Wishlist();
		wish.setUser(userRepository.findByUserId(BigDecimal.valueOf(userId)).get(0));
		wish.setProduct(productRepository.findByProductId(BigDecimal.valueOf(productId)).get(0));
		return repository.save(wish);
	}
	
	@DeleteMapping("/wishlist/{id}")
	public void deleteOrder(@PathVariable("id") BigDecimal id) {
		repository.deleteById(id);
	}
	
	
}
