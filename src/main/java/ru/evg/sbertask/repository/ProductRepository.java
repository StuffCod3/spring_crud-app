package ru.evg.sbertask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.evg.sbertask.entity.Product;

/**
 * Репозиторий для работы с продуктами.
 * Этот интерфейс предоставляет методы для выполнения операций CRUD (создание, чтение, обновление, удаление)
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
