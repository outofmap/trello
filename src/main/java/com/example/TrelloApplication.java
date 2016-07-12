package com.example;

import org.h2.server.web.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.domain.Customer;
import com.example.domain.CustomerRepository;
import com.example.domain.User;
import com.example.domain.UserRepository;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = ThymeleafAutoConfiguration.class)
@EnableSwagger2
public class TrelloApplication {
	private static final Logger log = LoggerFactory.getLogger(TrelloApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(TrelloApplication.class, args);
	}
	
	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/console/*");
		return registration;
	}
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
	
	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new User("test@t.com", "테스트유저","ps123"));
			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for(User user: repository.findAll()) {
				log.info(user.toString());
			}

		};
	}
	
	@Bean
	public BCryptPasswordEncoder bcrypt(){
		return new BCryptPasswordEncoder();
	}
}
