package com.springguru.bootstrap;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.springguru.models.Category;
import com.springguru.models.Difficulty;
import com.springguru.models.Ingredient;
import com.springguru.models.Notes;
import com.springguru.models.Recipe;
import com.springguru.models.UnitOfMeasure;
import com.springguru.repositories.CategoryRepository;
import com.springguru.repositories.RecipeRepository;
import com.springguru.repositories.UnitOfMeasureRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoadData implements CommandLineRunner{

	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final CategoryRepository categoryRepository;
	
	


	/*
	 * public LoadData(RecipeRepository recipeRepository, UnitOfMeasureRepository
	 * unitOfMeasureRepository, CategoryRepository categoryRepository) {
	 * this.recipeRepository = recipeRepository; this.unitOfMeasureRepository =
	 * unitOfMeasureRepository; this.categoryRepository = categoryRepository; }
	 */




	@Override
	@Transactional
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Optional<UnitOfMeasure> tablespoon = this.unitOfMeasureRepository.findByUom("tablespoon");
		Optional<UnitOfMeasure> teaspoon = this.unitOfMeasureRepository.findByUom("teaspoon");
		Optional<UnitOfMeasure> clove = this.unitOfMeasureRepository.findByUom("clove");
		
		
		Recipe spicyGrilled = new Recipe();
		spicyGrilled.setCookTime(15);
		spicyGrilled.setPrepTime(20);
		spicyGrilled.setDifficulty(Difficulty.EASY);
		spicyGrilled.setDescription("Spicy Grilled Chicken Tacos");
		spicyGrilled.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
		spicyGrilled.setServings(4);
		
		Set<Ingredient> ingredients = new HashSet<Ingredient>();
		ingredients.add(new Ingredient("dried oregano", new BigDecimal(1), teaspoon.get(), spicyGrilled));
		ingredients.add(new Ingredient("dried cumin", new BigDecimal(1), teaspoon.get(), spicyGrilled));
		ingredients.add(new Ingredient("sugar", new BigDecimal(1), teaspoon.get(), spicyGrilled));
		ingredients.add(new Ingredient("salt", new BigDecimal(1/2), teaspoon.get(), spicyGrilled));
		ingredients.add(new Ingredient("garlic, finely chopped", new BigDecimal(1), clove.get(), spicyGrilled));
		ingredients.add(new Ingredient("finely grated orange zest", new BigDecimal(1), tablespoon.get(), spicyGrilled));
		ingredients.add(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tablespoon.get(), spicyGrilled));
		ingredients.add(new Ingredient("olive oil", new BigDecimal(2), tablespoon.get(), spicyGrilled));
		
		spicyGrilled.setIngredients(ingredients);
		
		Notes note = new Notes();
		note.setRecipeNotes
		        ("Look for ancho chile powder with the Mexican ingredients at your grocery store, "
				+ "on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, "
				+ "the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, "
				+ "though the flavor won't be quite the same.)");
		
		note.setRecipe(spicyGrilled);
		spicyGrilled.setNotes(note);
		
		Set<Category> categories = new HashSet<Category>();
		
		Optional<Category> category = this.categoryRepository.findByCategoryName("Morrocan");	
		categories.add(category.get());
				
		spicyGrilled.setCategories(categories);
		
		this.recipeRepository.save(spicyGrilled);
		
		log.debug("******* All the data have been saved successfully *******");
	}

}
