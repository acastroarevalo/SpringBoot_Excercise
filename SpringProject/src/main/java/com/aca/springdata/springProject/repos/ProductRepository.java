package com.aca.springdata.springProject.repos;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aca.springdata.springProject.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, BigDecimal> {
	
	List<Product> findByProductId(BigDecimal productId);
	List<Product> findByName(String name);
	List<Product> findByPrice(double price);

}
