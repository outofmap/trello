package com.example.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Card {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cardId;
	private String title;
	private String writer;
	private String description;
	//private List<User> assignee;
	private String due;
	private long deckId;
	private Integer order;
	

}
