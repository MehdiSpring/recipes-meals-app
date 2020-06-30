package com.springguru.converters;

import java.util.Set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.springguru.commands.CategoryCommand;
import com.springguru.commands.IngredientCommand;
import com.springguru.commands.NotesCommand;
import com.springguru.commands.RecipeCommand;
import com.springguru.models.Category;
import com.springguru.models.Ingredient;
import com.springguru.models.Notes;
import com.springguru.models.Recipe;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;

@RequiredArgsConstructor
@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe>{

	private final CategoryCommandToCategory categoryCommandToCategory;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	private final NotesCommandToNotes notesCommandToNotes;
	
	@Nullable
	@Synchronized
	@Override
	public Recipe convert(RecipeCommand source) {
		if(source==null)
			return null;
		
		Recipe recipe = new Recipe();
		recipe.setId(source.getId());
		recipe.setCookTime(source.getCookTime());
		recipe.setDescription(source.getDescription());
		recipe.setDifficulty(source.getDifficulty());
		recipe.setDirections(source.getDirections());
		recipe.setImage(source.getImage());
		recipe.setPrepTime(source.getPrepTime());
		recipe.setServings(source.getServings());
		recipe.setSource(source.getSource());
		recipe.setUrl(source.getUrl());
		
		Set<CategoryCommand> categoriesCommands = source.getCategories();
		for(CategoryCommand categoryCommand: categoriesCommands)
		{
			Category category = this.categoryCommandToCategory.convert(categoryCommand);
			if(category != null)
				recipe.getCategories().add(category);
		}
		
		Set<IngredientCommand> ingredientsCommands = source.getIngredients();
		for(IngredientCommand ingredientCommand: ingredientsCommands)
		{
			Ingredient ingredient = this.ingredientCommandToIngredient.convert(ingredientCommand);
			if(ingredient != null)
				recipe.getIngredients().add(ingredient);
		}
		
		NotesCommand notesCommand = source.getNotes();
		Notes notes = this.notesCommandToNotes.convert(notesCommand);
		if(notes != null)
			recipe.setNotes(notes);
		
		
		return recipe;
	}

}
