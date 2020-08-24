package com.springguru.converters;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.springguru.commands.IngredientCommand;
import com.springguru.models.Ingredient;
import com.springguru.models.Recipe;
import com.springguru.repositories.RecipeRepository;

class IngredientCommandToIngredientTest {

	Long id=1L;
	String description = "descTest";
	BigDecimal amount = new BigDecimal(3);
	
	IngredientCommand ingredientCommad;
	
	UOMCommandToUOM uomCommandToUom;
	
	IngredientCommandToIngredient ingredientCommandToIngredient;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		
		uomCommandToUom = new UOMCommandToUOM();
		ingredientCommad = new IngredientCommand();
		
		ingredientCommandToIngredient = new IngredientCommandToIngredient(uomCommandToUom, recipeRepository);
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
		ingredientCommad.setRecipeId(1L);
		
		when(recipeRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(new Recipe()));
		
		Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommad);
		assertEquals(id, ingredient.getId());
		assertEquals(description, ingredient.getDescription());
		assertEquals(amount, ingredient.getAmount());
		assertNull(ingredient.getUom());
		
	}

}
