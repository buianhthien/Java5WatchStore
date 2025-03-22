package edu.poly.pd11347_asm.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.poly.pd11347_asm.models.Product;
import edu.poly.pd11347_asm.models.Category;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategory_CategoryId(Long categoryId, Pageable pageable);

    List<Product> findByCategory(Category category);

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:keyword%")
    Page<Product> search(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT p, c.categoryName FROM Product p JOIN p.category c")
    List<Object[]> getProductsWithCategoryName();

    @Query("SELECT p FROM Product p ORDER BY p.productId DESC")
    Page<Product> findNewProducts(Pageable pageable);

    List<Product> findTop4ByOrderByPriceAsc();

    List<Product> findTop4ByOrderByPriceDesc();

    List<Product> findByProductNameContainingIgnoreCase(String keyword);

}
