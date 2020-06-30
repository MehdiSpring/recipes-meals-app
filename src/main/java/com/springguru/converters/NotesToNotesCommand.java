package com.springguru.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.springguru.commands.NotesCommand;
import com.springguru.commands.RecipeCommand;
import com.springguru.models.Notes;
import com.springguru.models.Recipe;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;

@RequiredArgsConstructor
@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand>{
	
	private final RecipeToRecipeCommand recipeToRecipeCommand;
	
	@Nullable
	@Synchronized
	@Override
	public NotesCommand convert(Notes source) {
		
		if(source == null)
			return null;
		
		NotesCommand notesCommand = new NotesCommand();
		notesCommand.setIdNotes(source.getIdNotes());
		notesCommand.setRecipeNotes(source.getRecipeNotes());
		
		RecipeCommand recipeCommand = this.recipeToRecipeCommand.convert(source.getRecipe());
		
		if(recipeCommand != null)
			notesCommand.setRecipe(recipeCommand);
		
		return notesCommand;
	}

}
