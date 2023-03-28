package com.hutech.cnrb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hutech.cnrb.dto.TransactionDto;
import com.hutech.cnrb.exceptionhandler.ResourceNotFoundException;
import com.hutech.cnrb.response.TransactionAverage;
import com.hutech.cnrb.response.TransactionResponse;
import com.hutech.cnrb.service.TransationService;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

	@Autowired
	private TransationService transationService;

	@PostMapping("/account/{accountNumber}/transactions")
	public TransactionResponse transactions(@RequestBody TransactionDto trDto, @PathVariable String accountNumber)
			throws ResourceNotFoundException {

		return transationService.createtTransactions(trDto, accountNumber);
	}public TransactionController() {
		// TODO Auto-generated constructor stub
	}
	@GetMapping("/bank/{bankId}/account/{accountId}/average")
	public TransactionAverage getAverageTransactions(@PathVariable String bankId,@PathVariable String accountId,@RequestParam Integer month)throws ResourceNotFoundException {
		return transationService.getAverageTransaction(bankId,accountId,month);
	}

}
