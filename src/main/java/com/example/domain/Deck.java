package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Deck {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long deckId;
	private String deckName;
	private Integer order;
	private long boardId;
	
	
}
