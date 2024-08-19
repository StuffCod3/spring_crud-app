package ru.evg.sbertask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.evg.sbertask.entity.Product;
import ru.evg.sbertask.repository.ProductRepository;
import ru.evg.sbertask.service.ProductService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testGetAllProducts() {
        List<Product> list = getProducts();

        when(productRepository.findAll()).thenReturn(list);

        List<Product> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(2, list.size());
    }

    @Test
    void testGetByIdProducts() {
        Long id = 2L;
        Product product = getProduct();

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        Product result = productService.getById(id);

        assertNotNull(result);
        assertEquals(product.getId(), result.getId());
    }

    @Test
    void testCreateProduct() {
        Product product = new Product(null, "Product 1", "Description 1", 10.0);
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.createProduct(product);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getName());
        assertNotNull(result.getDescription());
        assertNotNull(result.getPrice());
        assertEquals(product.getName(), result.getName());
    }

    private List<Product> getProducts(){
        Product product1 = new Product(1L, "Product 1", "Description 1", 10.0);
        Product product2 = new Product(2L, "Product 2", "Description 2", 20.0);
        return List.of(product1, product2);
    }

    private Product getProduct(){
        return new Product(1L, "Product 1", "Description 1", 10.0);
    }
}
