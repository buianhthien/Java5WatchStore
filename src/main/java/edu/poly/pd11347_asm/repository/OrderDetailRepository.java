package edu.poly.pd11347_asm.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import edu.poly.pd11347_asm.models.OrderDetail;


@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
