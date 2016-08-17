package com.example.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
