package com.springguru.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Notes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idNotes;

	@Lob
	private String recipeNotes;
	
	@OneToOne
	private Recipe recipe;



	/*
	 * public String getRecipeNotes() { return recipeNotes; }
	 * 
	 * public void setRecipeNotes(String recipeNotes) { this.recipeNotes =
	 * recipeNotes; }
	 * 
	 * public Recipe getRecipe() { return recipe; }
	 * 
	 * public void setRecipe(Recipe recipe) { this.recipe = recipe; }
	 */
	
	
}
