package edu.poly.pd11347_asm.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import edu.poly.pd11347_asm.models.Order;


@Repository 
public interface OrderRepository extends JpaRepository<Order, Long> {

}
