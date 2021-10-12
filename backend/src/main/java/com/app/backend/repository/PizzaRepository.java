package com.app.backend.repository;

import com.app.backend.domain.pizza.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    Optional<Pizza> findPizzaByName(String name);
    List<Pizza> findAll();
}
