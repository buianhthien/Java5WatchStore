package edu.poly.pd11347_asm.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.poly.pd11347_asm.models.Category;
@Service
public interface CategoryService {
    void add(Category category);

    void delete(Long categoryId);

    Category findById(Long categoryId);

    Iterable<Category> findAll();
    
    void update(Category category);

    Page<Category> findAll( Pageable pageable);

    Page<Category> search(String keyword, Pageable pageable);
}
