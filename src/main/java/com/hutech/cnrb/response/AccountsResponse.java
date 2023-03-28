package com.hutech.cnrb.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AccountsResponse {

	private String customerName;
	
	private double remaingBalance;
	
	private double totalExpenses;
	
	private double totalEarnings;
	
	private LocalDate lastTransaction;
	
}
