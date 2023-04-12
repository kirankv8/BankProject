package com.hutech.cnrb.service;

import com.hutech.cnrb.entity.Bank;
import com.hutech.cnrb.exceptionhandler.ResourceNotFoundException;
import com.hutech.cnrb.exceptionhandler.UserNameAlreadyExistException;

public interface BankService {

	Bank saveBank(Bank bank);

	double getBankBalance(String bankId) throws ResourceNotFoundException;

}
