package com.hutech.cnrb;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.hutech.cnrb.entity.Bank;
import com.hutech.cnrb.repository.BankRepository;
@Component
public class DataIntializr {
	
	@Autowired
	private BankRepository bankRepository;

	@EventListener
    public void addRole(ApplicationReadyEvent event) {		
	List<Bank> bank = new ArrayList<Bank>(2);
	bank.add(0,new Bank(1,"CONCN_BANK", "CNONO123", 0, "CNON01", "BRANCH001"));
	bankRepository.saveAll(bank);
    
}
}
