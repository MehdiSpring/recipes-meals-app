package com.springguru.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.springguru.commands.RecipeCommand;
import com.springguru.models.Recipe;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;

@RequiredArgsConstructor
@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand>{

	private final CategoryToCategoryCommand categoryToCategoryCommand;
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final NotesToNotesCommand notesToNotesCommand;
	
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
