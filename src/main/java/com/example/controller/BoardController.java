package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Deck;

@Controller
public class BoardController {
	
	@RequestMapping("/boards")
	public String show() {
		return "projectMain";
	}
	
}
