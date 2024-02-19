package com.aca.springdata.springProject.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aca.springdata.springProject.entities.User;
import com.aca.springdata.springProject.repos.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@GetMapping("/users")
	public List<User> getUsers(){
		return repository.findAll();
	}
	
	@GetMapping("/users/email/{email}")
	public User getUserByEmail(@PathVariable("email") String email) {
		return repository.findByEmail(email).get(0);
	}
	
	@GetMapping("/users/name/{name}")
	public List<User> getUserByName(@PathVariable("name") String name) {
		return repository.findByName(name);
	}
	
	@PostMapping("/users")
	public User saveUser(@RequestBody User user) {
		List<User> emails = repository.findByEmail(user.getEmail());
		if (emails.isEmpty()) {
			return repository.save(user);
		}else {
			System.out.println("----------------------------------------------\n"
					+ "USER EMAIL TAKEN\n"
					+ "----------------------------------------------");
		}
		return null;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable("id") BigDecimal id) {
		repository.deleteById(id);
	}
}
