package com.springguru.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.springguru.commands.RecipeCommand;
import com.springguru.models.Recipe;

import lombok.Synchronized;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand>{

	@Nullable
	@Synchronized
	@Override
	public RecipeCommand convert(Recipe source) {
		if(source == null)
			return null;
		
		RecipeCommand recipeCommand = new RecipeCommand();
		
		return recipeCommand;
	}

}
