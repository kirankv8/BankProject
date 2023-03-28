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
	
	private final String bankName = "CANARA-BANK";

	private final String ifsccode = "CNRB00541";

	private double bankAmount;
	
	private String bankId;

	private final String branchcode = "BRANCH-01";

	@PostPersist
	public void setBranchId() {
		if (id != null)
			bankId = "SVB" + 00 + id;
	}

}
