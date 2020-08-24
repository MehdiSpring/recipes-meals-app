package com.springguru.converters;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.springguru.commands.CategoryCommand;
import com.springguru.commands.IngredientCommand;
import com.springguru.commands.RecipeCommand;
import com.springguru.models.Difficulty;
import com.springguru.models.Recipe;
import com.springguru.repositories.RecipeRepository;

class RecipeCommandToRecipeTest {
	
	Long id = 1l;
	Integer cookTime = 20;
	String description = "Simple-recipe";
    Difficulty difficulty = Difficulty.EASY;
    
	RecipeCommand recipeCommand;
	
	UOMCommandToUOM uomCommandToUom;
	IngredientCommandToIngredient ingredientCommandToIngredient;
	CategoryCommandToCategory categoryCommandToCategory;
	NotesCommandToNotes notesCommandToNotes;
	
	RecipeCommandToRecipe recipeCommandToRecipe;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		recipeCommand = new RecipeCommand();
		recipeCommand.setId(id);
		recipeCommand.setDescription(description);
		recipeCommand.setCookTime(cookTime);
		recipeCommand.setDifficulty(difficulty);
		
		CategoryCommand category1 = new CategoryCommand();
		category1.setId(1L);
		CategoryCommand category2 = new CategoryCommand();
		category2.setId(2L);
		
		IngredientCommand ingredient1 = new IngredientCommand();
		ingredient1.setId(1L);
		ingredient1.setRecipeId(1L);
		
		IngredientCommand ingredient2 = new IngredientCommand();
		ingredient2.setId(2L);
		ingredient2.setRecipeId(2L);
		
		recipeCommand.getCategories().add(category1);
		recipeCommand.getCategories().add(category2);
		
		recipeCommand.getIngredients().add(ingredient1);
		recipeCommand.getIngredients().add(ingredient2);
		
		uomCommandToUom = new UOMCommandToUOM();
		ingredientCommandToIngredient = new IngredientCommandToIngredient(uomCommandToUom, recipeRepository);
		categoryCommandToCategory = new CategoryCommandToCategory();
		notesCommandToNotes = new NotesCommandToNotes();
		
		recipeCommandToRecipe = new RecipeCommandToRecipe(categoryCommandToCategory, ingredientCommandToIngredient, notesCommandToNotes);
	}

	@Test
	void test() {
		Recipe recipett = new Recipe();
		recipett.setId(1L);
		Optional<Recipe> opt = Optional.of(recipett);
		when(recipeRepository.findById(ArgumentMatchers.anyLong())).thenReturn(opt);
		
		
		Recipe recipe = recipeCommandToRecipe.convert(recipeCommand);
		
		
		assertEquals(id, recipe.getId());
		assertEquals(description, recipe.getDescription());
		assertEquals(cookTime, recipe.getCookTime());
		assertEquals(difficulty, recipe.getDifficulty());
		assertEquals(2, recipe.getCategories().size());
		assertEquals(2, recipe.getIngredients().size());
		assertNull(recipe.getNotes());
	}

}
