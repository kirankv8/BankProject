package com.hutech.cnrb.response;

import lombok.Data;

@Data
public class AccountResponse {
	private String accountNumber;

	private String name;

	private Long adharcardNumber;

	private String pancardNumber;

	private String email;

	private Long phoneNumber;

	private String bankId;

	private String accountType;

	private Double accountBalance;

	
}
