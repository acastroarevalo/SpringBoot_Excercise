package com.aca.springdata.springProject.repos;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aca.springdata.springProject.entities.Product;
import com.aca.springdata.springProject.entities.User;
import com.aca.springdata.springProject.entities.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, BigDecimal> {
	
	List<Wishlist> findByUser(User user);
	List<Wishlist> findByProduct(Product product);

}
