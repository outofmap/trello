package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.example.domain.Board;
import com.example.domain.BoardRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class BoardWebIntegrationTest extends WebIntegrationTest {
	private static final Logger logger = LoggerFactory.getLogger(BoardWebIntegrationTest.class);
	
	@Autowired
	private BoardRepository boardRepository;
	private RestTemplate restTemplate;
	
	@Before
	public void init(){
		restTemplate = new RestTemplate();
	}
	
	@Test
	public void createBoard() {
		Board b = new Board("first");
		ResponseEntity<Board> re = restTemplate.postForEntity(baseUrl()+"/api/boards", b, Board.class);
		assertThat(re.getStatusCode(), is(HttpStatus.CREATED));
//		logger.debug("create board()",actual);
	}
}
