package ru.evg.sbertask.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.evg.sbertask.entity.Product;
import ru.evg.sbertask.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    Logger logger = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        List<Product> list = productRepository.findAll();
        logger.info("!Вывод всех продуктов!");
        for (Product p : list){
            logger.info(p.toString());
        }
        return list;
    }

    public Product getById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product){
        try {
            logger.info("Продукт создан {}", product.toString());
            return productRepository.save(product);
        } catch (Exception e){
            logger.error("Ошибка создания подукта {}", e.getMessage());
            return null;
        }
    }

    public Product updateProduct(Long id, Product product){
        Optional<Product> existProduct = productRepository.findById(id);
        if (existProduct.isPresent()){
            product.setId(id);
            logger.info("Продукт обновлен {}", product.toString());
            return productRepository.save(product);
        } else {
            logger.warn("Не удалось обновить, продукт с id {} не найден", id);
            return null;
        }
    }

    public void deleteProduct(Long id){
        Optional<Product> existProduct = productRepository.findById(id);
        if (existProduct.isPresent()){
            productRepository.deleteById(id);
        } else {
            logger.warn("Не удалось удалить, продукт с id {} не найден", id);
        }
    }
}
