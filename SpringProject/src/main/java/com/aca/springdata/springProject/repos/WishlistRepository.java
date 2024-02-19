package com.aca.springdata.springProject.repos;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aca.springdata.springProject.entities.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, BigDecimal> {

}
