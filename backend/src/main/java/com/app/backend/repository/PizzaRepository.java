package com.app.backend.repository;

import com.app.backend.domain.pizza.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    Optional<Pizza> findPizzaByName(String name);
    List<Pizza> findAll();
    @Transactional
    @Modifying
    @Query("UPDATE Pizza p " + "SET p.price = ?1 " + "WHERE p.id = ?2")
    int updatePizzaPrice(int price, Long id);
}
