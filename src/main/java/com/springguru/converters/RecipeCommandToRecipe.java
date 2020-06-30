package com.springguru.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.springguru.commands.RecipeCommand;
import com.springguru.models.Recipe;

import lombok.Synchronized;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe>{

	@Nullable
	@Synchronized
	@Override
	public Recipe convert(RecipeCommand source) {
		if(source==null)
			return null;
		
		Recipe recipe = new Recipe();
		
		return recipe;
	}

}
