package com.springguru.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.springguru.commands.NotesCommand;
import com.springguru.models.Notes;
import com.springguru.models.Recipe;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;

@RequiredArgsConstructor
@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
	
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	
	@Nullable
	@Synchronized
	@Override
	public Notes convert(NotesCommand source) {
		
		if(source == null)
			return null;
		
		Notes notes = new Notes();
		notes.setIdNotes(source.getIdNotes());
		notes.setRecipeNotes(source.getRecipeNotes());
		
		Recipe recipe = this.recipeCommandToRecipe.convert(source.getRecipe());
		if(recipe != null)
			notes.setRecipe(recipe);
		
		return notes;
	}

}
