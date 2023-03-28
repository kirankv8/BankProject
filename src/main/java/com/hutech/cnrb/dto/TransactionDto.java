package com.hutech.cnrb.dto;

import lombok.Data;

@Data
public class TransactionDto {

	private String accountNumber;

	private double transactionAmount;

	private String source;

	private String description;

}
