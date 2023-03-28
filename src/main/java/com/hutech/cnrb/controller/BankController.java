package com.hutech.cnrb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hutech.cnrb.entity.Bank;
import com.hutech.cnrb.exceptionhandler.ResourceNotFoundException;
import com.hutech.cnrb.exceptionhandler.UserNameAlreadyExistException;
import com.hutech.cnrb.service.BankService;

@RestController
@RequestMapping("/api/v1")
public class BankController {

	@Autowired
	private BankService bankService;
	
	@PostMapping("/register/bank")
	public Bank registerBank(@RequestBody Bank bank)throws UserNameAlreadyExistException {
		return bankService.saveBank(bank);
	}
	
	@GetMapping("/bank/{bankId}/bankBalance")
	public String BankBalance(@PathVariable String bankId)throws ResourceNotFoundException {	
		return  "TOTAL BANK BALANCE IS = " +bankService.getBankBalance(bankId);
}

}