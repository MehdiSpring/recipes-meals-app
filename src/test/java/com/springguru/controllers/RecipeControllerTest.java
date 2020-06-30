package com.springguru.controllers;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.springguru.models.Recipe;
import com.springguru.repositories.RecipeRepository;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

	@Mock
	RecipeRepository recipeRepository;
	
	@InjectMocks
	RecipeController recipeController;
	
	MockMvc mockMVC;
	
	Optional<Recipe> recipe;
	
	@BeforeEach
	void setUp() throws Exception {
		//MockitoAnnotations.initMocks(this);
		mockMVC = MockMvcBuilders.standaloneSetup(recipeController).build();
		recipe = Optional.of(new Recipe());
	}

	@Test
	void testRecipeById() throws Exception{
		when(recipeRepository.findById(ArgumentMatchers.anyLong())).thenReturn(recipe);
		mockMVC.perform(get("/recipe/show/1")).andExpect(status().isOk())
		                                      .andExpect(view().name("recipe/details"))
		                                      .andExpect(model().attributeExists("recipe"));
	}

}
