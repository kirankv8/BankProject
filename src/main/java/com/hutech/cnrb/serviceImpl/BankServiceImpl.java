package com.hutech.cnrb.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hutech.cnrb.entity.Bank;
import com.hutech.cnrb.exceptionhandler.ResourceNotFoundException;
import com.hutech.cnrb.repository.BankRepository;
import com.hutech.cnrb.service.BankService;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankRepository bankRepository;

	@Override
	public Bank saveBank(Bank bank) {
	  Bank bank2=new Bank();
	  bank2.setBankAmount(bank.getBankAmount());
		return bankRepository.save(bank2);
	}

	@Override
	public double getBankBalance(String bankId)throws ResourceNotFoundException {
		if(bankRepository.findByBankId(bankId)==null) {
			throw new ResourceNotFoundException("given bank id Not present in Db", 404);
		}	
		Bank bank=bankRepository.findByBankId(bankId);
		return bank.getBankAmount();
	}
}
