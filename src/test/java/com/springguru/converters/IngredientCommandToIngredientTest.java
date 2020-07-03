package com.springguru.converters;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.springguru.commands.IngredientCommand;
import com.springguru.models.Ingredient;

class IngredientCommandToIngredientTest {

	Long id=1L;
	String description = "descTest";
	BigDecimal amount = new BigDecimal(3);
	
	IngredientCommand ingredientCommad;
	
	UOMCommandToUOM uomCommandToUom;
	
	IngredientCommandToIngredient ingredientCommandToIngredient;
	
	
	@BeforeEach
	void setUp() throws Exception {
		uomCommandToUom = new UOMCommandToUOM();
		ingredientCommad = new IngredientCommand();
		
		ingredientCommandToIngredient = new IngredientCommandToIngredient(uomCommandToUom);
	}

	@Test
	public void testNullSource()
	{
		assertNull(ingredientCommandToIngredient.convert(null));
	}
	
	@Test
	void testConvert() {
		
		ingredientCommad.setId(id);
		ingredientCommad.setDescription(description);
		ingredientCommad.setAmount(amount);
		
		Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommad);
		assertEquals(id, ingredient.getId());
		assertEquals(description, ingredient.getDescription());
		assertEquals(amount, ingredient.getAmount());
		assertNull(ingredient.getUom());
		
	}

}
