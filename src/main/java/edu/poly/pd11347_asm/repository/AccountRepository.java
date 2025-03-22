package edu.poly.pd11347_asm.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import edu.poly.pd11347_asm.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Page<Account> findByUsernameContaining(String name, Pageable pageable);

    @Query("select a from Account a where a.fullname like ?1")
    Page<Account> search(String keyword, Pageable pageable);

}
