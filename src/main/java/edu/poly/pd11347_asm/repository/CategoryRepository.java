package edu.poly.pd11347_asm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.poly.pd11347_asm.models.Category;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Page<Category> findByCategoryNameContaining(String categoryName, Pageable pageable);

    @Query("select c from Category c where c.categoryName like ?1")
    Page<Category> search(String keyword, Pageable pageable);

    Page<Category> findByCategoryNameContainingIgnoreCase(String categoryName, Pageable pageable);

}
