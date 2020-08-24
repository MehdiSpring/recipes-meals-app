package com.springguru.converters;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.springguru.commands.IngredientCommand;
import com.springguru.models.Ingredient;
import com.springguru.models.Recipe;

class IngredientToIngredientCommandTest {

	Long id=1L;
	String description = "descTest";
	BigDecimal amount = new BigDecimal(3);
	
	Ingredient ingredient;
	
	UOMToUOMCommand uomToUomCommand;
	
	IngredientToIngredientCommand ingredientToIngredientCommand;
	
	
	@BeforeEach
	void setUp() throws Exception {
		uomToUomCommand = new UOMToUOMCommand();
		ingredient = new Ingredient();
		
		ingredientToIngredientCommand = new IngredientToIngredientCommand(uomToUomCommand);
	}

	@Test
	public void testNullSource()
	{
		assertNull(ingredientToIngredientCommand.convert(null));
	}
	
	@Test
	void testConvert() {
		
		ingredient.setId(id);
		ingredient.setDescription(description);
		ingredient.setAmount(amount);
		ingredient.setRecipe(new Recipe());
		
		IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(ingredient);
		assertEquals(id, ingredientCommand.getId());
		assertEquals(description, ingredientCommand.getDescription());
		assertEquals(amount, ingredientCommand.getAmount());
		assertNull(ingredientCommand.getUom());
		
	}
}
