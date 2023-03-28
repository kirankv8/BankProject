package com.hutech.cnrb.service;

import java.util.List;

import com.hutech.cnrb.dto.AccountDto;
import com.hutech.cnrb.exceptionhandler.ResourceNotFoundException;
import com.hutech.cnrb.exceptionhandler.UserNameAlreadyExistException;
import com.hutech.cnrb.response.AccountResponse;
import com.hutech.cnrb.response.AccountsResponse;

import jakarta.mail.MessagingException;

public interface AccountService {

	AccountResponse saveAccount(AccountDto accountDto, String bankId)
			throws ResourceNotFoundException, UserNameAlreadyExistException, MessagingException;

	List<AccountsResponse> getAllCustomers(String bankId) throws ResourceNotFoundException;

	String getAccountBalance(String bankId, String accId) throws ResourceNotFoundException;

}
