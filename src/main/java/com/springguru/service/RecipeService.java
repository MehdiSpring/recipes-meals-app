package com.springguru.service;

import java.util.Optional;
import java.util.Set;

import com.springguru.commands.RecipeCommand;
import com.springguru.models.Recipe;

public interface RecipeService {
	
	public Set<Recipe> findAll();
	public Recipe findById(Long id);
	public RecipeCommand findByIdCommand(Long idCommand);
	public Recipe findByDescription(String description);
	
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
	public void deleteRecipe(Long id);

}
