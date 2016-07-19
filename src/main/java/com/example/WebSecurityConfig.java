package com.example;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Resource(name = "customUserDetailsService")
	private UserDetailsService customUserDeatailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable(); // CSRF(Cross-site request
													// forgery)와
													// CORS(Cross-Origin
													// Resource Sharing)무효화
		// static resources
		http
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/boards/**").authenticated()
			.anyRequest().permitAll()
			.and()
		.formLogin()
				.loginPage("/loginpage")
				.loginProcessingUrl("/user/login")
				.failureUrl("/loginpage")
				.usernameParameter("email")
				.passwordParameter("password")
				.defaultSuccessUrl("/boards",true)
				.permitAll()
			.and()
		.logout()
				.permitAll();

		http.httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDeatailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
