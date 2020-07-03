package com.springguru.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.springguru.commands.CategoryCommand;
import com.springguru.commands.NotesCommand;
import com.springguru.models.Category;
import com.springguru.models.Notes;

class NotesCommandToNotesTest {

	Long id=1L;
	String recipeNotes = "recipteNotesTest";
	
	NotesCommand notesCommand;
	
	NotesCommandToNotes notesCommandToNotes;
	
	@BeforeEach
	void setUp() throws Exception {
		notesCommand = new NotesCommand();
		notesCommandToNotes = new NotesCommandToNotes();
	}

	@Test
	public void testNullSource()
	{
		assertNull(notesCommandToNotes.convert(null));
	}
	
	@Test
	public void testNotNullSource()
	{
		assertNotNull(notesCommandToNotes.convert(notesCommand));
	}
	
	@Test
	void testConvert() {
		notesCommand.setIdNotes(id);
		notesCommand.setRecipeNotes(recipeNotes);
		
		Notes notes = notesCommandToNotes.convert(notesCommand);
		assertEquals(id, notes.getIdNotes());
		assertEquals(recipeNotes, notes.getRecipeNotes());
	}

}
