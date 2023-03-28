package com.hutech.cnrb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hutech.cnrb.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> 
{

}
