package com.hutech.cnrb.response;

import java.time.LocalDate;

import lombok.Data;
@Data
public class TransactionResponse {

	private double transactionAmount;

	private double balance;

	private String source;

	private String description;

	private LocalDate date;

	private String accountNumber;

	private String bankId;
	
}
