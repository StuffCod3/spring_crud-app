package ru.evg.sbertask.controller;

import org.springframework.web.bind.annotation.*;
import ru.evg.sbertask.entity.Product;
import ru.evg.sbertask.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> all(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product byId(@PathVariable("id") Long id){
        return productService.getById(id);
    }

    @PostMapping
    public Product create(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }
}
