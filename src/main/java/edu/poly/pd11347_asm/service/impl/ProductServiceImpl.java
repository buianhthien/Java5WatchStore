package edu.poly.pd11347_asm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.poly.pd11347_asm.models.Product;
import edu.poly.pd11347_asm.repository.ProductRepository;
import edu.poly.pd11347_asm.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void add(Product product) {
        if (product != null) {
            productRepository.save(product);
        }
    }

    @Override
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product findById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void update(Product product) {
        if (product != null && product.getProductId() != null) {
            productRepository.save(product);
        }
    }

}
