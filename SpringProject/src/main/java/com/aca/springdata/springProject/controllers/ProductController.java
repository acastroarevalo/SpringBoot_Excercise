package com.aca.springdata.springProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aca.springdata.springProject.entities.Product;
import com.aca.springdata.springProject.repos.ProductRepository;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	ProductRepository repository;
	
	@GetMapping("/products")
	public List<Product> getProducts(){
		return repository.findAll();
	}
	
	@GetMapping("/products/price/{price}")
	public List<Product> getProductByPrice(@PathVariable("price") Double price) {
		return repository.findByPrice(price);
	}
	
	@GetMapping("/products/name/{name}")
	public Product getProductByName(@PathVariable("name") String name) {
		return repository.findByName(name).get(0);
	}
	
	@PostMapping("/products")
	public Product saveProduct(@RequestBody Product product) {
		List<Product> names = repository.findByName(product.getName());
		if (names.isEmpty()) {
			return repository.save(product);
		}else {
			System.out.println("----------------------------------------------\n"
					+ "EXISTING PRODUCT\n"
					+ "----------------------------------------------");
			Product existing_product = repository.findByName(product.getName()).get(0);
			long newInv = existing_product.getTotalProductInventory() + 1;
			existing_product.setTotalProductInventory(newInv);
			return repository.save(existing_product);
		}
	}
	
	@PatchMapping("/products/{name}")
	public Product deleteProduct(@PathVariable String name) {
		Product product = repository.findByName(name).get(0);
		product.setStatus(false);
		return repository.save(product);
	}
}
