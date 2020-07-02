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
		recipeCommand.setId(source.getId());
		recipeCommand.setCookTime(source.getCookTime());
		recipeCommand.setDescription(source.getDescription());
		recipeCommand.setDifficulty(source.getDifficulty());
		recipeCommand.setDirections(source.getDirections());
		recipeCommand.setImage(source.getImage());
		recipeCommand.setPrepTime(source.getPrepTime());
		recipeCommand.setServings(source.getServings());
		recipeCommand.setSource(source.getSource());
		recipeCommand.setUrl(source.getUrl());
	
		Set<Category> categories = source.getCategories();
		for(Category category: categories)
		{
			CategoryCommand categoryCommand = this.categoryToCategoryCommand.convert(category);
			if(categoryCommand != null)
				recipeCommand.getCategories().add(categoryCommand);
		}
		
		Set<Ingredient> ingredients = source.getIngredients();
		for(Ingredient ingredient: ingredients)
		{
			IngredientCommand ingredientCommand = this.ingredientToIngredientCommand.convert(ingredient);
			if(ingredientCommand != null)
				recipeCommand.getIngredients().add(ingredientCommand);
		}
		
		Notes notes = source.getNotes();
		NotesCommand notesCommand = this.notesToNotesCommand.convert(notes);
		if(notesCommand != null)
			recipeCommand.setNotes(notesCommand);
		
		return recipeCommand;
	}

}
