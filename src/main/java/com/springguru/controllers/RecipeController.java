package com.springguru.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.springguru.service.RecipeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class RecipeController {
	
	private final RecipeService recipeService;
	
	@RequestMapping("/recipe/show/{id}")
	public String recipeById(@PathVariable String id, Model model)
	{
		model.addAttribute("recipe", this.recipeService.findById(new Long(id)).get());
		return "recipe/details";
	}
	

}
