package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor      
@Data
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long boardId;
	@Column(nullable = false)
	private String name;
	
	
	public Board(String name) {
		this.name = name;
	}
	
	
}
