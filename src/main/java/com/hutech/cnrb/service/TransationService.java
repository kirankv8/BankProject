package com.hutech.cnrb.service;

import com.hutech.cnrb.dto.TransactionDto;
import com.hutech.cnrb.exceptionhandler.ResourceNotFoundException;
import com.hutech.cnrb.response.TransactionAverage;
import com.hutech.cnrb.response.TransactionResponse;

public interface TransationService {

	TransactionResponse createtTransactions(TransactionDto trDto, String accountNumber)
			throws ResourceNotFoundException;

	TransactionAverage getAverageTransaction(String bankId, String accountId, Integer month)throws ResourceNotFoundException;

}
