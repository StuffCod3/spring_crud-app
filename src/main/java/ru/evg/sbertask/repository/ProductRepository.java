package ru.evg.sbertask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.evg.sbertask.entity.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
