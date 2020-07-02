package com.springguru.converters;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.springguru.commands.CategoryCommand;
import com.springguru.commands.RecipeCommand;
import com.springguru.models.Category;
import com.springguru.models.Recipe;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;

@RequiredArgsConstructor
@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category>{

	//private final RecipeCommandToRecipe recipeCommandToRecipe;
	
	@Nullable
	@Synchronized
	@Override
	public Category convert(CategoryCommand source) {
		if(source == null)
		   return null;
		Category category = new Category();
		category.setId(source.getId());
		category.setCategoryName(source.getCategoryName());
		
		/*
		 * Set<RecipeCommand> recipesCommands = source.getRecipes();
		 * 
		 * for(RecipeCommand recipeCommand: recipesCommands) { Recipe recipe =
		 * this.recipeCommandToRecipe.convert(recipeCommand); if(recipe!= null)
		 * category.getRecipes().add(recipe); }
		 */
		
		return category;
	}
	
	

}
