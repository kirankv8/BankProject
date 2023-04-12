package com.hutech.cnrb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bank {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;
	
	private String bankName ;

	private  String ifsccode ;
	
	private double bankAmount;
	
	private String bankId;

	private  String branchcode ;

	@PostPersist
	public void setBranchId() {
		if (id != null)
			bankId = "SVB" + 00 + id;
	}

}
