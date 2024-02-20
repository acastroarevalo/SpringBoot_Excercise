package com.aca.springdata.springProject.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
	public List<User> getUsers() throws Exception{
		if (repository.findAll().isEmpty()) throw new Exception("Empty List");
		return repository.findAll();
	}
	
	@GetMapping("/users/email/{email}")
	public User getUserByEmail(@PathVariable("email") String email) throws Exception {
		if (repository.findByEmail(email).isEmpty()) throw new Exception("Object not found");
		return repository.findByEmail(email).get(0);
	}
	
	@GetMapping("/users/name/{name}")
	public List<User> getUserByName(@PathVariable("name") String name) throws Exception {
		if (repository.findByName(name).isEmpty()) throw new Exception("Object not found");
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
	
	@PatchMapping("/users/{id}/{email}/{aoe}")
	public User updateUser(@PathVariable("id") BigDecimal id, @PathVariable("email") String email, @PathVariable("aoe") String aoe) throws Exception {
		if (repository.findByUserId(id).isEmpty()) throw new Exception("Object not found");
		User user = repository.findByUserId(id).get(0);
		user.setAreaOfInterest(aoe);
		user.setEmail(email);
		return repository.save(user);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable("id") BigDecimal id) throws Exception {
		if (repository.findByUserId(id).isEmpty()) throw new Exception("Object not found");
		repository.deleteById(id);
	}
	
	@DeleteMapping("/users/all")
	public void deleteAllUsers() throws Exception {
		if (repository.findAll().isEmpty()) throw new Exception("Empty List");
		repository.deleteAll();
	}
}
