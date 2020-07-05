package com.springguru.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springguru.models.Category;
import com.springguru.models.Recipe;
import com.springguru.repositories.CategoryRepository;

import com.springguru.service.RecipeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@Controller 
public class IndexController {
	
	
	private final RecipeService recipeService;
	
	/*
	 * public IndexController(CategoryRepository categoryRepository,
	 * RecipeRepository recipeRepository) { this.categoryRepository =
	 * categoryRepository; this.recipeRepository = recipeRepository; }
	 */



	@RequestMapping({"","/","index"})
	public String getIndexMsg(Model model)
	{ 
		log.info("----- The indexpage is consulted ------");
		//The optional type is a new feature of java 8
		/*Since Spring 5, all the methods declared in a CrudRepositoy interface that retrieves an entity object, will no longer return the object directly,
		but they will encapsulate it in an Optional object and return this optional type*/
		//Optional<Category> category = this.categoryRepository.findById(1L);
		
		
		model.addAttribute("recipes", this.recipeService.findAll());
		
		return "index";
	}

}
