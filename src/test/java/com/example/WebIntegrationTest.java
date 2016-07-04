package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = TrelloApplication.class)	//trello 프로젝트와 같은 설정파일로 시작하기 
@WebAppConfiguration
@IntegrationTest("server.port:0")
public abstract class WebIntegrationTest {
	
	@Value("${local.server.port}")
    private int port;
	protected String baseUrl() {
		return String.format("http://localhost:%d", port); //random으로 port 할당 
	}
	
}
