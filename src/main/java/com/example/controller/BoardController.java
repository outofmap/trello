package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.domain.Board;
import com.example.domain.BoardRepository;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Autowired
	BoardRepository br;
	
	@RequestMapping(value="/boards", method=RequestMethod.GET)
	public String showAll(Model model) {
		Iterable<Board>boards = br.findAll();
		model.addAttribute("boards", boards);
		return "myboard";
	}
	
	@RequestMapping(value="/boards/{boardId}", method=RequestMethod.GET)
	public String showBoard(@PathVariable Long boardId){
		return "board";
	}
}
