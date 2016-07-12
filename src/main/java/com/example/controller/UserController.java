package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.domain.BoardRepository;
import com.example.domain.User;
import com.example.domain.UserRepository;

@Controller
public class UserController {
	@Autowired
	UserRepository userRepo;
	@Autowired
	BoardRepository br;
	@Autowired
	BCryptPasswordEncoder bcrypt;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	@RequestMapping(value="/signUp", method=RequestMethod.GET)
	public String showSignUp(){
		return "signUp";
	}
	
	@RequestMapping(value="/signUp", method=RequestMethod.POST)
	public String signUp(User user){
		String encodedPassword = bcrypt.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepo.save(user);
		logger.info("saved"+user.toString());
		return "index";
	}
	
	@RequestMapping(value="/loginpage", method=RequestMethod.GET)
	public String showLogin(){
		return "login";
	}
	
	
}
