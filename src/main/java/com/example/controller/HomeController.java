package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Customer;
import com.example.domain.CustomerRepository;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String index(){
		return "index";
	}


}
