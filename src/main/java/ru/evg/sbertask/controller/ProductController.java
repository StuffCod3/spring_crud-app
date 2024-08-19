package ru.evg.sbertask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.evg.sbertask.entity.Product;
import ru.evg.sbertask.service.ProductService;

import java.util.List;

/**
 * Контроллер для управления продуктами.
 * Этот контроллер обрабатывает HTTP-запросы, связанные с продуктами, и взаимодействует с
 * {@link ProductService} для выполнения операций CRUD (создание, чтение, обновление, удаление).
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    Logger logger = LoggerFactory.getLogger(ProductController.class);

    /**
     * Конструктор для инициализации ProductController.
     *
     * @param productService сервис для работы с продуктами
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Обрабатывает GET-запрос для получения всех продуктов.
     *
     * @return список всех продуктов
     */
    @GetMapping("/all")
    public List<Product> all(){
        logger.info("Пришел запрос на получение всех продуктов");
        return productService.getAllProducts();
    }

    /**
     * Обрабатывает GET-запрос для получения продукта по его идентификатору.
     *
     * @param id идентификатор продукта
     * @return продукт с указанным идентификатором
     */
    @GetMapping("/{id}")
    public Product byId(@PathVariable("id") Long id){
        logger.info("Пришел запрос на вывод продукта с id: {}", id);
        return productService.getById(id);
    }

    /**
     * Обрабатывает POST-запрос для создания нового продукта.
     *
     * @param product продукт, который нужно создать
     * @return созданный продукт
     */
    @PostMapping
    public Product create(@RequestBody Product product){
        logger.info("Пришел запрос на создание продукта: {}", product);
        return productService.createProduct(product);
    }

    /**
     * Обрабатывает PUT-запрос для обновления существующего продукта.
     *
     * @param id идентификатор продукта для обновления
     * @param product обновленные данные продукта
     * @return обновленный продукт
     */
    @PutMapping("/{id}")
    public Product update(@PathVariable("id") Long id, @RequestBody Product product){
        logger.info("Пришел запрос на обновление продукта с id: {}", id);
        return productService.updateProduct(id, product);
    }

    /**
     * Обрабатывает DELETE-запрос для удаления продукта по его идентификатору.
     *
     * @param id идентификатор продукта для удаления
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        logger.info("Пришел запрос на удаление продукта с id: {}", id);
        productService.deleteProduct(id);
    }
}
