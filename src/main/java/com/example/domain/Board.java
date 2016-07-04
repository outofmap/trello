package com.example.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor      
@Data
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long boardId;
	private String name;
	
	public Board(String name) {
		this.name = name;
	}
	
}
