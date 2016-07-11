package com.example.controller;

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
	
	
	
	@RequestMapping(value="/signUp", method=RequestMethod.GET)
	public String showSignUp(){
		return "signUp";
	}
	
	@RequestMapping(value="/signUp", method=RequestMethod.POST)
	public String signUp(User user){
		String encodedPassword = bcrypt.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepo.save(user);
		return "index";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLogin(){
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(User user, Model model){
		User found = userRepo.findByEmail(user.getEmail());
		if(bcrypt.matches(user.getPassword(), found.getPassword())){
			model.addAttribute("boards", br.findAll());
			return "myboard";
		} else {
			return "login";
		}
	}
	
}
