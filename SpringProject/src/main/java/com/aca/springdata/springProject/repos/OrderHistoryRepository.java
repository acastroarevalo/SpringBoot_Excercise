package com.aca.springdata.springProject.repos;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aca.springdata.springProject.entities.OrderHistory;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, BigDecimal> {

}
