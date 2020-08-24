package com.springguru.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.springguru.commands.IngredientCommand;
import com.springguru.converters.IngredientCommandToIngredient;
import com.springguru.converters.IngredientToIngredientCommand;
import com.springguru.models.Ingredient;
import com.springguru.repositories.IngredientRepository;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class IngredientServiceImplTest {

	@Autowired
	IngredientRepository ingredientRepository;
	
	@Mock
	IngredientToIngredientCommand ingrToIngrCom;
	
	@Mock
	IngredientCommandToIngredient ingrComToIngr;
	
	IngredientService ingrService;
	
	Ingredient ingredient;
	
	IngredientCommand ingredientCommand;
	
	@BeforeEach
	void setUp() throws Exception {
		this.ingrService = new IngredientServiceImpl(ingredientRepository, ingrToIngrCom, ingrComToIngr);
		
		ingredient = new Ingredient();
		//ingredient.setId(1L);
		
		ingredientCommand = new IngredientCommand();
		//ingredientCommand.setId(1L);
	}

	@Test
	void testFindById() {	
		this.ingredientRepository.save(ingredient);
		Optional<Ingredient> optiIngr = this.ingrService.findById(1L);
		Ingredient ingr = new Ingredient();
		if(optiIngr.isPresent())
			ingr = optiIngr.get();
		assertEquals(1L, ingr.getId());
	}

	@Test
	void testFindByIdCommand() {
		//ingredient.setId(2L);
		ingredientCommand.setId(2L);
		this.ingredientRepository.save(ingredient);
		when(this.ingrToIngrCom.convert(ArgumentMatchers.any())).thenReturn(ingredientCommand);
		
		IngredientCommand ingrCom = new IngredientCommand();
		ingrCom = this.ingrService.findByIdCommand(2L);
		
		
		assertEquals(2L, ingrCom.getId());
		
	}

	@Test
	void testSaveIngredientCommand() {
		IngredientCommand ingrCom = new IngredientCommand();
		ingrCom.setId(4L);
		when(this.ingrComToIngr.convert(ArgumentMatchers.any())).thenReturn(ingredient);
		when(this.ingrToIngrCom.convert(ArgumentMatchers.any())).thenReturn(ingrCom);
		IngredientCommand ingr = this.ingrService.saveIngredientCommand(ingredientCommand);
		
		assertEquals(4L, ingr.getId());
		
		ingredient = this.ingrService.findById(4L).get();
		
		assertEquals(4L, ingredient.getId());		
		
	}

	
	@Test
	void testDelete() {
			
		this.ingredientRepository.save(ingredient);
		
		Ingredient ingr = this.ingrService.findById(3L).get();
		
		assertEquals(3L, ingr.getId());
		
		this.ingrService.delete(3L);
		
		Set<Ingredient> ingredients = new HashSet<Ingredient>();
		this.ingredientRepository.findAll().iterator().forEachRemaining(ingredients::add);
		
		assertEquals(0, ingredients.size());
	}

}
