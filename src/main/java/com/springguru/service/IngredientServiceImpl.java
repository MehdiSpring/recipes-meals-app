package com.springguru.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springguru.commands.IngredientCommand;
import com.springguru.converters.IngredientCommandToIngredient;
import com.springguru.converters.IngredientToIngredientCommand;
import com.springguru.models.Ingredient;
import com.springguru.repositories.IngredientRepository;
import com.springguru.repositories.UnitOfMeasureRepository;

@Service
public class IngredientServiceImpl implements IngredientService {
    
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final IngredientRepository ingredientRepository;
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToingredient;	

	
	public IngredientServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
			IngredientRepository ingredientRepository, IngredientToIngredientCommand ingredientToIngredientCommand,
			IngredientCommandToIngredient ingredientCommandToingredient) {
		
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.ingredientRepository = ingredientRepository;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToingredient = ingredientCommandToingredient;
	}

	@Override
	public Optional<Ingredient> findById(Long id) {
		
		return this.ingredientRepository.findById(id);
	}

	@Override
	public IngredientCommand findByIdCommand(Long idCommand) {
		Optional<Ingredient> ingrOptional =  this.ingredientRepository.findById(idCommand);
		IngredientCommand ingredientCommand = this.ingredientToIngredientCommand.convert(ingrOptional.get());
		
		return ingredientCommand;
	}

	@Override
	public void saveIngredientCommand(IngredientCommand ingredientCommand) {
		Ingredient ingredient = this.ingredientCommandToingredient.convert(ingredientCommand);
		
		//this.unitOfMeasureRepository.save(ingredient.getUom());
		this.ingredientRepository.save(ingredient);
		
	}

	@Override
	public void delete(Long id) {
		this.ingredientRepository.deleteById(id);
		
	}

}
