package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/boards")
public class ApiBoardController {
	private static final Logger logger = LoggerFactory.getLogger(ApiBoardController.class);
	@Autowired
	BoardRepository br;
	
	@RequestMapping(value= "", method = RequestMethod.POST)
	public ResponseEntity<Board> createBoard(@RequestBody Board board){
		logger.debug("board:"+board);
		Board saved = br.save(board);
		return new ResponseEntity<Board>(saved, HttpStatus.CREATED);
	}
	
	
}
