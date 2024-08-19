package ru.evg.sbertask.service;

import org.springframework.stereotype.Service;
import ru.evg.sbertask.entity.Product;
import ru.evg.sbertask.exception.BadRequestException;
import ru.evg.sbertask.exception.ResourceNotFoundException;
import ru.evg.sbertask.repository.ProductRepository;

import java.util.List;

/**
 * Сервис для управления продуктами.
 * Предоставляет методы для создания, получения, обновления и удаления продуктов.
 */
@Service
public class ProductService {
    private final ProductRepository productRepository;

    /**
     * Конструктор для инициализации ProductService.
     *
     * @param productRepository репозиторий для работы с продуктами
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Получает список всех продуктов.
     *
     * @return список всех продуктов
     * @throws ResourceNotFoundException если нет продуктов
     */
    public List<Product> getAllProducts(){
        List<Product> list = productRepository.findAll();
        if (list.isEmpty()){
            throw new ResourceNotFoundException("Нет продуктов");
        }
        return list;
    }

    /**
     * Получает продукт по его идентификатору.
     *
     * @param id идентификатор продукта
     * @return продукт с указанным идентификатором
     * @throws ResourceNotFoundException если продукт с указанным идентификатором не найден
     */
    public Product getById(Long id){
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Продукт с id: " + id + " не найден"));
    }

    /**
     * Создает новый продукт.
     *
     * @param product продукт для создания
     * @return созданный продукт
     * @throws BadRequestException если поля продукта пустые
     */
    public Product createProduct(Product product){
        if (product.getName() == null || product.getDescription() == null || product.getPrice() == null){
            throw new BadRequestException("Поля не должны быть пустыми");
        }
        return productRepository.save(product);
    }

    /**
     * Обновляет существующий продукт.
     *
     * @param id идентификатор продукта для обновления
     * @param product обновленный продукт
     * @return обновленный продукт
     * @throws ResourceNotFoundException если продукт с указанным идентификатором не найден
     */
    public Product updateProduct(Long id, Product product){
        Product existProduct = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Продукт с id: " + id + " не найден"));
        existProduct.setName(product.getName());
        existProduct.setPrice(product.getPrice());
        existProduct.setDescription(product.getDescription());
        return productRepository.save(existProduct);
    }

    /**
     * Удаляет продукт по его идентификатору.
     *
     * @param id идентификатор продукта для удаления
     * @throws ResourceNotFoundException если продукт с указанным идентификатором не найден
     */
    public void deleteProduct(Long id){
        Product existProduct = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Продукт с id: " + id + " не найден"));

        productRepository.delete(existProduct);
    }
}
