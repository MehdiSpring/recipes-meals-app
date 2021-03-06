package com.springguru.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.springguru.commands.RecipeCommand;
import com.springguru.exception.NotFoundException;
import com.springguru.service.CategoryService;
import com.springguru.service.RecipeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class RecipeController {
	
	private final CategoryService categoryService;
	private final RecipeService recipeService;
	
	@RequestMapping("/recipe/show/{id}")
	public String recipeById(@PathVariable String id, Model model)
	{
		model.addAttribute("recipe", this.recipeService.findById(new Long(id)));
		return "recipe/details";
	}
	
	@RequestMapping("/recipe/new/")
	public String recipeForm(Model model)
	{	
		RecipeCommand recipeCommand = new RecipeCommand();
		model.addAttribute("recipeCommand", recipeCommand);
		model.addAttribute("categories", this.categoryService.findAll());
		
		return "recipe/recipeForm";
	}
	
	@RequestMapping("/recipe/update/{id}")
	public String getRecipeToUpdate(@PathVariable String id, Model model)
	{
		model.addAttribute("recipeCommand",this.recipeService.findByIdCommand(new Long(id)));
		return "recipe/recipeForm";
	}
	
	@PostMapping
	@RequestMapping("/recipe/save")
	public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand)
	{
	
		RecipeCommand command = this.recipeService.saveRecipeCommand(recipeCommand);
		
		return "redirect:/recipe/show/"+command.getId();
	}
	
	@GetMapping
	@RequestMapping("/recipe/delete/{id}")
	public String deleteRecipe(@PathVariable String id, Model model)
	{
		
		this.recipeService.deleteRecipe(new Long(id));
		
		return "redirect:/index";
	}
	
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFoundException(Exception exception)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error404");
		modelAndView.addObject("message", exception.getMessage());
		
		return modelAndView;
	}

	/*This exception became global, so to avoid duplicate it in each controller
	  we used the @ControllerAdvice on the new controller :
	  ControllerGlobalExceptionHandler*/
	
	/*
	 * @ResponseStatus(code=HttpStatus.BAD_REQUEST)
	 * 
	 * @ExceptionHandler(NumberFormatException.class) public ModelAndView
	 * handleNumberFormatException(Exception exception) { ModelAndView modelAndView
	 * = new ModelAndView(); modelAndView.setViewName("error400");
	 * modelAndView.addObject("message", exception.getMessage());
	 * 
	 * return modelAndView; }
	 */
}
