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
public class Deck {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long deckId;
	@Column(nullable = false)
	private String deckName;
	
	@ManyToOne
	@JoinColumn(name="boardId")
	private Board board;
	
	public Deck(String name, Board board){
		this.deckName = name;
		this.board = board;
	}
	
}
