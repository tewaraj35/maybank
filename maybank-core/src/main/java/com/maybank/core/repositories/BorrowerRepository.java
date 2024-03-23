package com.maybank.core.repositories;

import com.maybank.core.domain.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Tewaraj
 */
public interface BorrowerRepository extends JpaRepository<Borrower, UUID> {

    Optional<Borrower> findByEmail(String email);
}
