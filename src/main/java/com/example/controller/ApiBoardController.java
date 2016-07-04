package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Board;
import com.example.domain.BoardRepository;

@RestController
@RequestMapping("/api")
public class ApiBoardController {
	@Autowired
	BoardRepository br;
	
	@RequestMapping(value= "/boards", method = RequestMethod.POST)
	public ResponseEntity<Board> createBoard(@RequestBody Board board){//@RequestParam String name){
		System.out.println("aaa" );
		Board saved = br.save(board);
		return new ResponseEntity<Board>(saved, HttpStatus.CREATED);
	}
	
}
