package com.springguru.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springguru.service.RecipeService;

@Controller
public class IngredientController {
	
	private final RecipeService recipeService;

	public IngredientController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@GetMapping
	@RequestMapping("/recipe/{id}/ingredients/")
	public String listIngredientsOfRecipe(@PathVariable String id, Model model)
	{
		model.addAttribute("recipeCommand", this.recipeService.findByIdCommand(new Long(id)));
		return "recipe/ingredients/list.html";
	}

}
