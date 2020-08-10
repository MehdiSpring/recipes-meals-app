package com.springguru.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.springguru.commands.IngredientCommand;
import com.springguru.commands.RecipeCommand;
import com.springguru.commands.UnitOfMeasureCommand;
import com.springguru.models.Ingredient;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;

@RequiredArgsConstructor
@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand>{

	private final UOMToUOMCommand uomToUomCommand;
	//private final RecipeToRecipeCommand recipeToRecipeCommand;
	
	@Nullable
	@Synchronized
	@Override
	public IngredientCommand convert(Ingredient source) {
		
		if(source == null)
			return null;
		
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(source.getId());
		
		ingredientCommand.setRecipeId(source.getRecipe().getId());
		
		ingredientCommand.setDescription(source.getDescription());
		ingredientCommand.setAmount(source.getAmount());
		
		UnitOfMeasureCommand uomCommand = this.uomToUomCommand.convert(source.getUom());
		if(uomCommand != null)
			ingredientCommand.setUom(uomCommand);
		
		/*
		 * RecipeCommand recipeCommand =
		 * this.recipeToRecipeCommand.convert(source.getRecipe()); if(recipeCommand !=
		 * null) ingredientCommand.setRecipe(recipeCommand);
		 */
		
		return ingredientCommand;
	}

}
