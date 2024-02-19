package com.aca.springdata.springProject.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
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
	
	@GetMapping("/wishlist/{userId}")
	public List<Wishlist> getWishByUser(@PathVariable("userId") BigDecimal id){
		return repository.findByUser(userRepository.findByUserId(id).get(0));
	}
	
	@PostMapping("/wishlist/{userId}/{productId}")
	public Wishlist saveWish(@PathVariable("userId") int userId, @PathVariable("productId") int productId) {
		Wishlist wish = new Wishlist();
		wish.setUser(userRepository.findByUserId(BigDecimal.valueOf(userId)).get(0));
		wish.setProduct(productRepository.findByProductId(BigDecimal.valueOf(productId)).get(0));
		return repository.save(wish);
	}
	
	@DeleteMapping("/wishlist/{id}")
	public void deleteWish(@PathVariable("id") BigDecimal id) {
		repository.deleteById(id);
	}
	
	@DeleteMapping("/wishlist/product/{productId}")
	public void deleteWishByProduct(@PathVariable("productId") long id) {
		//List<Wishlist> productWishes = new ArrayList<Wishlist>();
		List<Wishlist> wishes = repository.findAll();
		for(int i=0; i<wishes.size();i++) {
			System.out.println(wishes.get(i).getProduct().getProductId());
			if(wishes.get(i).getProduct().getProductId().equals(BigDecimal.valueOf(id))) {
				repository.delete(wishes.get(i));
			}
		}
	}
	
	@DeleteMapping("/wishlist/user/{userId}")
	public void deleteWishByUser(@PathVariable("userId") long id) {
		//List<Wishlist> productWishes = new ArrayList<Wishlist>();
		List<Wishlist> wishes = repository.findAll();
		for(int i=0; i<wishes.size();i++) {
			System.out.println(wishes.get(i).getUser().getUserId());
			if(wishes.get(i).getUser().getUserId().equals(BigDecimal.valueOf(id))) {
				repository.delete(wishes.get(i));
			}
		}
	}
	
}
