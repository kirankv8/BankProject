package com.hutech.cnrb;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import com.hutech.cnrb.entity.Bank;

@SpringBootApplication
public class CanaraBankApplication  {

	
	public static void main(String[] args) {
		SpringApplication.run(CanaraBankApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}		
}
