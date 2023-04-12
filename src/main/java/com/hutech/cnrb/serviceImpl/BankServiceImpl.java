package com.hutech.cnrb.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hutech.cnrb.entity.Bank;
import com.hutech.cnrb.exceptionhandler.ResourceNotFoundException;
import com.hutech.cnrb.exceptionhandler.UserNameAlreadyExistException;
import com.hutech.cnrb.repository.BankRepository;
import com.hutech.cnrb.service.BankService;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankRepository bankRepository;

	@Override
	
	public Bank saveBank(Bank bank) {
		 
		Bank bank1 = bankRepository.findByBankName(bank.getBankName());		
		if (bank1 != null) {
			bank1.setBankAmount(bank1.getBankAmount()+bank.getBankAmount());
			return bankRepository.save(bank1);
		} else {
			return bankRepository.save(bank);
		}
	}

	@Override
	public double getBankBalance(String bankId) throws ResourceNotFoundException {
		if (bankRepository.findByBankId(bankId) == null) {
			throw new ResourceNotFoundException("given bank id Not present in Db", 404);
		}
		Bank bank = bankRepository.findByBankId(bankId);
		return bank.getBankAmount();
	}
}
