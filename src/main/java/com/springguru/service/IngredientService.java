package com.springguru.service;

import java.util.Optional;

import com.springguru.commands.IngredientCommand;
import com.springguru.models.Ingredient;

public interface IngredientService {

	Optional<Ingredient> findById(Long id);
	IngredientCommand findByIdCommand(Long idCommand);
	IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
	void delete(Long id);
}
