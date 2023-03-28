package com.hutech.cnrb.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hutech.cnrb.entity.Transactions;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {

	Transactions findFirstByAccountNumberOrderByDateAsc(String accountNumber);

	List<Transactions> findByDescriptionAndAccountNumber(String description, String accountNumber);

	@Query("SELECT t FROM Transactions t WHERE t.date BETWEEN :startDate AND :endDate AND t.accountNumber = :accountNumber")
	List<Transactions> findByDateAndAccountNumber(LocalDate startDate, LocalDate endDate, String accountNumber);

	Optional<Transactions> findFirstByAccountNumberOrderByDateDesc(String accountNumber);

}
