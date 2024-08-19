package ru.evg.sbertask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.evg.sbertask.entity.Product;
import ru.evg.sbertask.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    Logger logger = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> all(){
        logger.info("Пришел запрос на получение всех продуктов");
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product byId(@PathVariable("id") Long id){
        logger.info("Пришел запрос на вывод продукта с id: {}", id);
        return productService.getById(id);
    }

    @PostMapping
    public Product create(@RequestBody Product product){
        logger.info("Пришел запрос на создание продукта: {}", product);
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable("id") Long id, @RequestBody Product product){
        logger.info("Пришел запрос на обновление продукта с id: {}", id);
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        logger.info("Пришел запрос на удаление продукта с id: {}", id);
        productService.deleteProduct(id);
    }
}
