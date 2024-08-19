package ru.evg.sbertask.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.evg.sbertask.entity.Product;
import ru.evg.sbertask.exception.BadRequestException;
import ru.evg.sbertask.exception.ResourceNotFoundException;
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
        List<Product> list = productRepository.findAll();
        if (list.isEmpty()){
            throw new ResourceNotFoundException("Нет продуктов");
        }
        return list;
    }

    public Product getById(Long id){
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Продукт с id: " + id + " не найден"));
    }

    public Product createProduct(Product product){
        if (product.getName() == null || product.getDescription() == null || product.getPrice() == null){
            throw new BadRequestException("Поля не должны быть пустыми");
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product){
        Product existProduct = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Продукт с id: " + id + " не найден"));
        existProduct.setName(product.getName());
        existProduct.setPrice(product.getPrice());
        existProduct.setDescription(product.getDescription());
        return productRepository.save(existProduct);
    }

    public void deleteProduct(Long id){
        Product existProduct = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Продукт с id: " + id + " не найден"));

        productRepository.delete(existProduct);
    }
}
