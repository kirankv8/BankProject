package com.hutech.cnrb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hutech.cnrb.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

Account findByAdharcardNumber(Long adharcardNumber);

Account findByAccountNumber(String accountNumber);

List<Account> findByBankId(String bankId);
}
