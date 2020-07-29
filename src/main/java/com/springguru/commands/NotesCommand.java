package com.springguru.commands;

import lombok.Setter;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class NotesCommand { 
	
	private Long idNotes;
	private String recipeNotes;
	//private RecipeCommand recipe;

}
