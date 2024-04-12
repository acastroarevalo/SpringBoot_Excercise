package com.aca.springdata.springProject.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	public List<Wishlist> getWishlist() throws Exception{
		if (repository.findAll().isEmpty()) throw new Exception("Empty List");
		return repository.findAll();
	}
	
	@GetMapping("/wishlist/{userId}")
	public List<Wishlist> getWishByUser(@PathVariable("userId") BigDecimal id) throws Exception{
		if (repository.findByUser(userRepository.findByUserId(id).get(0)).isEmpty()) throw new Exception("Object Not Found");
		return repository.findByUser(userRepository.findByUserId(id).get(0));
	}
	
	@PostMapping("/wishlist/{userId}/{productId}")
	public Wishlist saveWish(@PathVariable("userId") int userId, @PathVariable("productId") int productId) {
		
		List<Wishlist> wishes = repository.findAll();
		for(int i=0; i<wishes.size();i++) {
			if( wishes.get(i).getProduct().getProductId().equals(BigDecimal.valueOf(productId)) & 
					wishes.get(i).getUser().getUserId().equals(BigDecimal.valueOf(userId)) ) {
				System.out.println("EXISTING PRODUCT IN WISHLIST");
				return null;
			}
		}
		
		Wishlist wish = new Wishlist();
		wish.setUser(userRepository.findByUserId(BigDecimal.valueOf(userId)).get(0));
		wish.setProduct(productRepository.findByProductId(BigDecimal.valueOf(productId)).get(0));
		return repository.save(wish);
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/wishlist/{id}")
	public void deleteWish(@PathVariable("id") BigDecimal id) throws Exception {
		if (repository.findById(id).isEmpty()) throw new Exception("Empty List");
		repository.deleteById(id);
	}
	
	@DeleteMapping("/wishlist/product/{productId}")
	public void deleteWishByProduct(@PathVariable("productId") long id) throws Exception {
		if (repository.findByProduct(productRepository.findByProductId(BigDecimal.valueOf(id)).get(0)).isEmpty()) throw new Exception("Object Not Found");
		List<Wishlist> wishes = repository.findAll();
		for(int i=0; i<wishes.size();i++) {
			System.out.println(wishes.get(i).getProduct().getProductId());
			if(wishes.get(i).getProduct().getProductId().equals(BigDecimal.valueOf(id))) {
				repository.delete(wishes.get(i));
			}
		}
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/wishlist/user/{userId}")
	public void deleteWishByUser(@PathVariable("userId") long id) throws Exception {
		if (repository.findByUser(userRepository.findByUserId(BigDecimal.valueOf(id)).get(0)).isEmpty()) throw new Exception("Object Not Found");
		List<Wishlist> wishes = repository.findAll();
		for(int i=0; i<wishes.size();i++) {
			System.out.println(wishes.get(i).getUser().getUserId());
			if(wishes.get(i).getUser().getUserId().equals(BigDecimal.valueOf(id))) {
				repository.delete(wishes.get(i));
			}
		}
	}
	
}
