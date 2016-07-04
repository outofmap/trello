package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.domain.Board;
import com.example.domain.BoardRepository;
import com.example.domain.Deck;

@Controller
public class BoardController {
	@Autowired
	BoardRepository br;
	
	@RequestMapping(value="/boards", method=RequestMethod.GET)
	public String showAll(Model model) {
		Iterable<Board>boards = br.findAll();
		System.out.println(boards.toString());
		model.addAttribute("boards", boards);
		return "projectMain";
	}
	
}
