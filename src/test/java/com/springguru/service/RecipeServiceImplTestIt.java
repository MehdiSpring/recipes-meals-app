package com.springguru.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.springguru.commands.IngredientCommand;
import com.springguru.commands.RecipeCommand;
import com.springguru.converters.RecipeCommandToRecipe;
import com.springguru.converters.RecipeToRecipeCommand;
import com.springguru.models.Ingredient;
import com.springguru.models.Recipe;
import com.springguru.repositories.RecipeRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RecipeServiceImplTestIt {

	Long id=2L;
	Integer cookTime = 20;
	Integer prepTime = 20;
	Set<IngredientCommand> ingredientsCommands = new HashSet<IngredientCommand>();
	
	RecipeCommand recipeCommand;
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	RecipeCommandToRecipe recipeCommandToRecipe;
	
	@Autowired
	RecipeToRecipeCommand recipeToRecipeCommand;
	
	@Autowired
	RecipeServiceImpl recipesServiceImpl;
	

	@BeforeEach
	void setUp() throws Exception {
		
		recipeCommand = new RecipeCommand();
		recipeCommand.setId(id);
		recipeCommand.setCookTime(cookTime);
		recipeCommand.setPrepTime(prepTime);
		
		IngredientCommand ingredient1 = new IngredientCommand();
		ingredient1.setId(10L);
		IngredientCommand ingredient2 = new IngredientCommand();
		ingredient2.setId(11L);
		
		ingredientsCommands.add(ingredient1);
		ingredientsCommands.add(ingredient2);
		
		recipeCommand.setIngredients(ingredientsCommands);
	}

	@Transactional
	@Test
	void testSaveRecipeCommand() {
		
		RecipeCommand recipeCommand2 = this.recipesServiceImpl.saveRecipeCommand(recipeCommand);
		
		assertEquals(id, recipeCommand2.getId());
		assertEquals(cookTime, recipeCommand2.getCookTime());
		assertEquals(prepTime, recipeCommand2.getPrepTime());
		assertEquals(2, recipeCommand2.getIngredients().size());
		
		Recipe recipe = this.recipeRepository.findById(id).get();
		assertNotNull(recipe);
		
		assertEquals(id, recipe.getId());
		assertEquals(cookTime, recipe.getCookTime());
		assertEquals(prepTime, recipe.getPrepTime());
		assertEquals(2, recipe.getIngredients().size());
		
	}

}
