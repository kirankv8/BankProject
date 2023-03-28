package com.hutech.cnrb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hutech.cnrb.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {

Bank findByBankName(String bankName);

Bank findByBankId(String bankId);

	


}
