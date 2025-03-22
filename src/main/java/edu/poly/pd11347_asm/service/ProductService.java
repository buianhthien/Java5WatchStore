package edu.poly.pd11347_asm.service;

import org.springframework.stereotype.Service;
import edu.poly.pd11347_asm.models.Product;

@Service
public interface ProductService {
    void add(Product product);

    void delete(Long productId);

    Product findById(Long productId);

    Iterable<Product> findAll();

    void update(Product product);


}
