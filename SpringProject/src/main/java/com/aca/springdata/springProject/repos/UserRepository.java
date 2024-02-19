package com.aca.springdata.springProject.repos;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aca.springdata.springProject.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, BigDecimal> {
	
	List<User> findByUserId(BigDecimal userId);
	List<User> findByName(String name);
	List<User> findByEmail(String email);

}
