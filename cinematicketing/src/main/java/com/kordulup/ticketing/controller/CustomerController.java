package com.kordulup.ticketing.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kordulup.ticketing.entities.Customer;
import com.kordulup.ticketing.repos.CustomerRepository;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class CustomerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		LOGGER.info("Inside getAllCustomers()");
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customers::add);
		return customers;
	}

	@PostMapping("/customer")
	public Customer saveCustomer(@RequestBody Customer c) {
		LOGGER.info("Inside saveCustomer(), saving customer: " + c);
		Customer customer = customerRepository.save(c);
		return customer;
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
		LOGGER.info("Inside deleteCustomer(), deleting user with id: " + id);
		customerRepository.deleteById(id);
		return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
	}

	@PutMapping("/customer/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody Customer c) {
		LOGGER.info("Inside updateCustomer(), updating customer with id: " + id);

		Optional<Customer> customerData = customerRepository.findById(id);

		if (customerData.isPresent()) {
			Customer customer = customerData.get();
			customer.setFirstName(c.getFirstName());
			customer.setLastName(c.getLastName());
			customer.setMiddleName(c.getMiddleName());
			customer.setEmail(c.getEmail());
			customer.setPhone(c.getPhone());
			return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
