package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Customer;
import com.example.domain.CustomerRepository;

@RestController
public class HomeController {
	@Autowired
	CustomerRepository cr;

	@RequestMapping(value = "/customers")
	public Iterable<Customer> showCustomers() {
		return cr.findByLastName("Bauer");
	}
	
}
