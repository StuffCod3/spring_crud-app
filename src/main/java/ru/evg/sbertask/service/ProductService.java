package ru.evg.sbertask.service;

import org.springframework.stereotype.Service;
import ru.evg.sbertask.entity.Product;
import ru.evg.sbertask.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product){
        Optional<Product> existProduct = productRepository.findById(id);
        if (existProduct.isPresent()){
            product.setId(id);
            return productRepository.save(product);
        } else {
            throw new RuntimeException("Нет такого продукта");
        }
    }

    public void deleteProduct(Long id){
        Optional<Product> existProduct = productRepository.findById(id);
        if (existProduct.isPresent()){
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Нет такого продукта");
        }
    }
}
