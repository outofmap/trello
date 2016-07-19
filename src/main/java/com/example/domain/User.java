package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(name ="email",nullable = false)
	private String email;
	@Column(name="userName",nullable = false)
	private String userName;
	@Column(name = "password",nullable = false)
	private String password;
	
	public User(String email, String userName, String password) {
		this.email = email;
		this.userName = userName;
		this.password = password;
	}
	
}
