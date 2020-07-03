package com.springguru.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.springguru.commands.NotesCommand;
import com.springguru.models.Notes;

class NotesToNotesCommandTest {

	Long id=1L;
	String recipeNotes = "recipteNotesTest";
	
	Notes notes;
	
	NotesToNotesCommand notesToNotesCommand;
	
	@BeforeEach
	void setUp() throws Exception {
		notes = new Notes();
		notesToNotesCommand = new NotesToNotesCommand();
	}

	@Test
	public void testNullSource()
	{
		assertNull(notesToNotesCommand.convert(null));
	}
	
	@Test
	public void testNotNullSource()
	{
		assertNotNull(notesToNotesCommand.convert(notes));
	}
	
	@Test
	void testConvert() {
		notes.setIdNotes(id);
		notes.setRecipeNotes(recipeNotes);
		
		NotesCommand notesCommand = notesToNotesCommand.convert(notes);
		assertEquals(id, notesCommand.getIdNotes());
		assertEquals(recipeNotes, notesCommand.getRecipeNotes());
	}

}
