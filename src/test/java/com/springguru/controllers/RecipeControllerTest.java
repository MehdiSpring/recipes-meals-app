package com.springguru.controllers;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;import com.springguru.commands.RecipeCommand;
import com.springguru.exception.NotFoundException;
import com.springguru.models.Recipe;
import com.springguru.repositories.RecipeRepository;
import com.springguru.service.CategoryService;
import com.springguru.service.RecipeService;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

	@Mock
	RecipeService recipeService;
	
	@Mock
	CategoryService categoryService;
	
	@InjectMocks
	RecipeController recipeController;
	
	MockMvc mockMVC;
	
	Optional<Recipe> recipe;
	
	@BeforeEach
	void setUp() throws Exception {
		//MockitoAnnotations.initMocks(this);
		mockMVC = MockMvcBuilders.standaloneSetup(recipeController).
				  //this method allow us to configure a Controller Advice for our MOCKMVC
				  setControllerAdvice(new ControllerGlobalExceptionHandler()).
				  build();
		recipe = Optional.of(new Recipe());
	}

	@Test
	void testRecipeById() throws Exception{
		when(recipeService.findById(ArgumentMatchers.anyLong())).thenReturn(recipe.get());
		mockMVC.perform(get("/recipe/show/1")).andExpect(status().isOk())
		                                      .andExpect(view().name("recipe/details"))
		                                      .andExpect(model().attributeExists("recipe"));
	}
	
	@Test
	void testRecipeForm() throws Exception{
		mockMVC.perform(get("/recipe/new/")).andExpect(status().isOk())
											.andExpect(view().name("recipe/recipeForm"));
	}
	
	@Test
	void testGetRecipetoUpdate() throws Exception {
	    mockMVC.perform(get("/recipe/update/1")).andExpect(status().isOk())
	    										.andExpect(view().name("recipe/recipeForm"));
	}
	
	@Test
	void testSaveOrUpdate() throws Exception{
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(5L);
		when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);
		mockMVC.perform(post("/recipe/save")).andExpect(status().is3xxRedirection())
										     .andExpect(view().name("redirect:/recipe/show/5"));
	}
	@Test
	void testDeleteRecipe() throws Exception{
		mockMVC.perform(get("/recipe/delete/1")).andExpect(status().is3xxRedirection())
												   .andExpect(view().name("redirect:/index"));
	}
	
	@Test
	void testRecipeNoExistingId() throws Exception{
		when(recipeService.findById(ArgumentMatchers.anyLong())).thenThrow(NotFoundException.class);
		
		mockMVC.perform(get("/recipe/show/3")).andExpect(status().isNotFound())
											  .andExpect(view().name("error404"));
	}
	
	@Test
	void testRecipeBadId() throws Exception{
		mockMVC.perform(get("/recipe/show/badId")).andExpect(status().isBadRequest())
												  .andExpect(view().name("error400"))
												  .andExpect(model().attributeExists("message"));
	}
}
