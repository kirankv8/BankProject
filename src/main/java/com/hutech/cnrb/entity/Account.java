package com.hutech.cnrb.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostPersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String accountNumber;

	private String name;

	private Long adharcardNumber;

	private String pancardNumber;

	private String email;

	private Long phoneNumber;

	private String bankId;

	private String accountType;

	private Double accountBalance;

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Transactions> transactions;
	
	  @PostPersist public void setAccountnumber() {
	if (id != null)
		accountNumber ="CNRB" +00 + id; }
	 
}
