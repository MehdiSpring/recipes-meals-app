package com.springguru.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Equality;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.springguru.models.Category;
import com.springguru.models.Ingredient;
import com.springguru.models.Recipe;
import com.springguru.repositories.CategoryRepository;
import com.springguru.repositories.RecipeRepository;
import com.springguru.service.RecipeService;

public class IndexControllerTest {

	@Mock
	Model model;
	
	@Mock
	RecipeService recipeService;
	
	IndexController indexController;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		indexController = new IndexController(recipeService);
	}

	@Test
    public void testMockMVC() throws Exception
    {
		/*Recipe recipe = new Recipe();
		recipe.setDescription("Mehdi");
		Optional<Recipe> recipeOptional = Optional.of(recipe);		
		when(recipeService.findByDescription("Spicy Grilled Chicken Tacos")).thenReturn(recipeOptional);*/
    	
		MockMvc mockMVC = MockMvcBuilders.standaloneSetup(indexController).build();
    	mockMVC.perform(get("/"))
    	.andExpect(status().is(200))
    	.andExpect(view().name("index"));
    }
	
	
	@Test
	public void testGetIndexMsg() {
		
		
		
		
		/*when(recipeService.findByDescription("Spicy Grilled Chicken Tacos")).thenReturn(recipeOptional);
		assertEquals(msg, indexController.getIndexMsg(model));
		
		
		verify(recipeService, times(1)).findByDescription("Spicy Grilled Chicken Tacos");*/
		
		
		
		//verify(model, times(1)).addAttribute("recipeName", null);
		//verify(model, times(1)).addAttribute("ingredients", ingredients);
		
		
		
		/*ArgumentCaptor<Recipe> recipeCapture = ArgumentCaptor.forClass(Recipe.class);
		verify(model, times(1)).addAttribute(Mockito.eq("recipeName"), recipeCapture.capture());
		String recipeName = recipeCapture.getValue().getDescription();
		assertEquals("Mehdi", recipeName);*/
		
		//ArgumentCaptor<String> categoryNameCapture = ArgumentCaptor.forClass(String.class);
		//verify(categoryRepository, times(1)).findByCategoryName(categoryNameCapture.capture());
		//assertEquals("Morrocan", categoryNameCapture.getValue());
		
	}

}
