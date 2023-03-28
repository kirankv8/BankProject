package com.hutech.cnrb.dto;

import com.hutech.cnrb.entity.Address;

import lombok.Data;

@Data
public class AccountDto {
	
	private String accountNumber;

	private String name;

	private Long adharcardNumber;

	private String pancardNumber;

	private String email;
	

	private Long phoneNumber;

	private String branchId;

	private String accountType;

	private Double accountBalance;

	
	private Address address;
}
