package com.springguru.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.springguru.commands.RecipeCommand;
import com.springguru.converters.RecipeCommandToRecipe;
import com.springguru.converters.RecipeToRecipeCommand;
import com.springguru.models.Recipe;
import com.springguru.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService{

	private final RecipeRepository recipeRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	private final RecipeToRecipeCommand recipeToRecipeCommand;
	
	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe,
			RecipeToRecipeCommand recipeToRecipeCommand) {
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}
	
	@Override
	public Set<Recipe> findAll() {
		Set<Recipe> recipes = new HashSet<Recipe>();
		this.recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
		return recipes;
	}

	@Override
	public Optional<Recipe> findById(Long id) {
		
		return this.recipeRepository.findById(id);
	}

	@Override
	public Optional<Recipe> findByDescription(String description) {
		
		return this.recipeRepository.findByDescription(description);
	}

	//@Transactional
	@Override
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
		Recipe recipe = this.recipeCommandToRecipe.convert(recipeCommand);
		if(recipe!=null)
		{
			this.recipeRepository.save(recipe);
			return this.recipeToRecipeCommand.convert(recipe);
		}
		return null;
	}

	@Override
	public RecipeCommand findByIdCommand(Long idCommand) {
		Optional<Recipe> recipeOptional = this.findById(idCommand);
		
		RecipeCommand recipeCommand = this.recipeToRecipeCommand.convert(recipeOptional.get());
		
		return recipeCommand;
	}

	@Override
	public void deleteRecipe(Long id) {
		this.recipeRepository.deleteById(id);
		
	}
	
	

}
