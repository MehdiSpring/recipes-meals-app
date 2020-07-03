package com.springguru.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.springguru.commands.CategoryCommand;
import com.springguru.commands.IngredientCommand;
import com.springguru.commands.RecipeCommand;
import com.springguru.models.Category;
import com.springguru.models.Difficulty;
import com.springguru.models.Ingredient;
import com.springguru.models.Recipe;

class RecipeToRecipeCommandTest {

	Long id = 1l;
	Integer cookTime = 20;
	String description = "Simple-recipe";
    Difficulty difficulty = Difficulty.EASY;
    
	Recipe recipe;
	
	UOMToUOMCommand uomToUomCommand;
	IngredientToIngredientCommand ingredientToIngredientCommand;
	CategoryToCategoryCommand categoryToCategoryCommand;
	NotesToNotesCommand notesToNotesCommand;
	
	RecipeToRecipeCommand recipeToRecipeCommand;
	
	@BeforeEach
	void setUp() throws Exception {
		recipe = new Recipe();
		recipe.setId(id);
		recipe.setDescription(description);
		recipe.setCookTime(cookTime);
		recipe.setDifficulty(difficulty);
		
		Category category1 = new Category();
		category1.setId(1L);
		Category category2 = new Category();
		category2.setId(2L);
		
		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1L);
		Ingredient ingredient2 = new Ingredient();
		ingredient2.setId(2L);
		
		recipe.getCategories().add(category1);
		recipe.getCategories().add(category2);
		
		recipe.getIngredients().add(ingredient1);
		recipe.getIngredients().add(ingredient2);
		
		uomToUomCommand = new UOMToUOMCommand();
		ingredientToIngredientCommand = new IngredientToIngredientCommand(uomToUomCommand);
		categoryToCategoryCommand = new CategoryToCategoryCommand();
		notesToNotesCommand = new NotesToNotesCommand();
		
		recipeToRecipeCommand = new RecipeToRecipeCommand(categoryToCategoryCommand, ingredientToIngredientCommand, notesToNotesCommand);
	}

	@Test
	void test() {
		
		
		RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);
		assertEquals(id, recipeCommand.getId());
		assertEquals(description, recipeCommand.getDescription());
		assertEquals(cookTime, recipeCommand.getCookTime());
		assertEquals(difficulty, recipeCommand.getDifficulty());
		assertEquals(2, recipeCommand.getCategories().size());
		assertEquals(2, recipeCommand.getIngredients().size());
		assertNull(recipeCommand.getNotes());
	}

}
