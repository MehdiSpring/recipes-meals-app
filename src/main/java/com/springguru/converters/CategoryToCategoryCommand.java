package com.springguru.converters;

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
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand>{

	private final RecipeToRecipeCommand recipeToRecipeCommand;
	
	@Nullable
	@Synchronized
	@Override
	public CategoryCommand convert(Category source) {
		if(source == null)
			return null;
		
		CategoryCommand categoryCommand = new CategoryCommand();
		categoryCommand.setId(source.getId());
		categoryCommand.setCategoryName(source.getCategoryName());
		
		Set<Recipe> recipes = source.getRecipes();
		for(Recipe recipe: recipes)
		{
			RecipeCommand recipeCommand = this.recipeToRecipeCommand.convert(recipe);
			if(recipeCommand != null)
				categoryCommand.getRecipes().add(recipeCommand);
		}
		
		return categoryCommand; 
	}

}
