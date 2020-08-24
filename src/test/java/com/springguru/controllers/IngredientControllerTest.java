package com.springguru.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.springguru.commands.IngredientCommand;
import com.springguru.service.IngredientService;
import com.springguru.service.RecipeService;
import com.springguru.service.UOMService;

@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {

	@Mock
	UOMService uomService;
	
	@Mock
	RecipeService recipeService;
	
	@Mock
	IngredientService ingredientService;
	
	@InjectMocks
	IngredientController ingredientController;
	
	MockMvc mockMVC;
	
	@BeforeEach
	void setUp() throws Exception {
		mockMVC = MockMvcBuilders.standaloneSetup(ingredientController).build();
	}

	@Test
	void testListIngredientsOfRecipe() throws Exception {
		mockMVC.perform(get("/recipe/1/ingredients/")).andExpect(status().isOk())
													  .andExpect(view().name("recipe/ingredients/list.html"));
	}

	@Test
	void testShowIngredient() throws Exception {
		mockMVC.perform(get("/ingredient/show/1")).andExpect(status().isOk())
												  .andExpect(view().name("recipe/ingredients/details.html"));
	}

	@Test
	void testRedirectToUpdatePage() throws Exception{
		mockMVC.perform(get("/ingredient/1/update/1")).andExpect(status().isOk())
													  .andExpect(view().name("recipe/ingredients/ingredientForm.html"));
	
	}

	@Test
	void testRedirectToCreatePage() throws Exception{
		mockMVC.perform(get("/ingredient/1/create/")).andExpect(status().isOk())
													 .andExpect(view().name("recipe/ingredients/ingredientForm.html"));
	}

	@Test
	void testCreateOrUpdate() throws Exception{
		IngredientCommand ingrCom = new IngredientCommand();
		ingrCom.setRecipeId(1L);
		when(ingredientService.saveIngredientCommand(ArgumentMatchers.any())).thenReturn(ingrCom);
		mockMVC.perform(post("/ingredient/save")).andExpect(status().is3xxRedirection())
												 .andExpect(view().name("redirect:/recipe/1/ingredients/"));
	}

	@Test
	void testDelete() throws Exception{
		mockMVC.perform(get("/ingredient/1/delete/1")).andExpect(status().is3xxRedirection())
													  .andExpect(view().name("redirect:/recipe/1/ingredients/"));
	}

}
