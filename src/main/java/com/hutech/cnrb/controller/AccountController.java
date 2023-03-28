package com.hutech.cnrb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hutech.cnrb.dto.AccountDto;
import com.hutech.cnrb.exceptionhandler.ResourceNotFoundException;
import com.hutech.cnrb.exceptionhandler.UserNameAlreadyExistException;
import com.hutech.cnrb.response.AccountResponse;
import com.hutech.cnrb.response.AccountsResponse;
import com.hutech.cnrb.service.AccountService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping("bank/{bankId}/save/account")
	public AccountResponse registerAccount(@RequestBody AccountDto accountDto, @PathVariable String bankId)
			throws ResourceNotFoundException, UserNameAlreadyExistException, MessagingException {
		return accountService.saveAccount(accountDto, bankId);
	}

	@GetMapping("bank/{bankId}/save/all/customers")
	public List<AccountsResponse> getAllData(@PathVariable String bankId) throws ResourceNotFoundException {
		return accountService.getAllCustomers(bankId);
	}
	@GetMapping("/bank/{bankId}/account/{accId}/balance")
	public String getAccountBalance(@PathVariable String bankId,@PathVariable String accId) throws ResourceNotFoundException {
		return accountService.getAccountBalance(bankId,accId);
	}
}
