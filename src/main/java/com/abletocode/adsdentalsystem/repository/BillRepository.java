package com.abletocode.adsdentalsystem.repository;

import com.abletocode.adsdentalsystem.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
}
