package com.kordulup.ticketing.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kordulup.ticketing.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
