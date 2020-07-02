package com.springguru.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.springguru.commands.IngredientCommand;
import com.springguru.models.Ingredient;
import com.springguru.models.Recipe;
import com.springguru.models.UnitOfMeasure;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;

@RequiredArgsConstructor
@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient>{

	//private final RecipeCommandToRecipe recipeCommandtoRecipe;
	private final UOMCommandToUOM uomCommandToUOM;
	
	@Nullable
	@Synchronized
	@Override
	public Ingredient convert(IngredientCommand source) {
		
		if(source == null)
			return null;
		
		Ingredient ingredient = new Ingredient();
		ingredient.setId(source.getId());
		ingredient.setDescription(source.getDescription());
		ingredient.setAmount(source.getAmount());
		
		UnitOfMeasure uom = this.uomCommandToUOM.convert(source.getUom());
		if(uom != null)
			ingredient.setUom(uom);
		
		/*
		 * Recipe recipe = this.recipeCommandtoRecipe.convert(source.getRecipe());
		 * if(recipe != null) ingredient.setRecipe(recipe);
		 */
		
		return ingredient;
	}

}
