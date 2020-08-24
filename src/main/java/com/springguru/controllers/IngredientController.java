package com.springguru.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springguru.commands.IngredientCommand;
import com.springguru.commands.UnitOfMeasureCommand;
import com.springguru.service.IngredientService;
import com.springguru.service.RecipeService;
import com.springguru.service.UOMService;

@Controller
public class IngredientController {
	
	private final UOMService uomService;
	private final RecipeService recipeService;
	private final IngredientService ingredientService;
	
	public IngredientController(UOMService uomService, RecipeService recipeService,
			IngredientService ingredientService) {
		
		this.uomService = uomService;
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
	}

	@GetMapping
	@RequestMapping("/recipe/{id}/ingredients/")
	public String listIngredientsOfRecipe(@PathVariable String id, Model model)
	{
		model.addAttribute("recipeCommand", this.recipeService.findByIdCommand(new Long(id)));
		return "recipe/ingredients/list.html";
	}

	@GetMapping
	@RequestMapping("/ingredient/show/{id}")
	public String showIngredient(@PathVariable String id, Model model)
	{
		model.addAttribute("ingredientCommand", this.ingredientService.findByIdCommand(new Long(id)));
		return "recipe/ingredients/details.html";
	}
	
	@GetMapping
	@RequestMapping("/ingredient/{recipeId}/update/{ingredientId}")
	public String redirectToUpdatePage(@PathVariable String recipeId, @PathVariable String ingredientId, Model model)
	{
		IngredientCommand ingredientCommand = this.ingredientService.findByIdCommand(new Long(ingredientId));
		model.addAttribute("ingredientCommand", ingredientCommand);
		model.addAttribute("listUom",this.uomService.findAllUOMCommand());
		
		return "recipe/ingredients/ingredientForm.html";
	}
	
	@GetMapping
	@RequestMapping("/ingredient/{recipeId}/create/")
	public String redirectToCreatePage(@PathVariable String recipeId, Model model)
	{
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setRecipeId(new Long(recipeId));
		
		UnitOfMeasureCommand uomComand = new UnitOfMeasureCommand();
		ingredientCommand.setUom(uomComand);
		
		model.addAttribute("ingredientCommand", ingredientCommand);
		model.addAttribute("listUom",this.uomService.findAllUOMCommand());
		
		return "recipe/ingredients/ingredientForm.html";
	}
	
	@PostMapping
	@RequestMapping("/ingredient/save")
	public String createOrUpdate(@ModelAttribute IngredientCommand ingredientCommand)
	{
		IngredientCommand ingrCom = this.ingredientService.saveIngredientCommand(ingredientCommand);
		return "redirect:/recipe/"+ingrCom.getRecipeId()+"/ingredients/";
	}
	
	@GetMapping
	@RequestMapping("/ingredient/{recipeId}/delete/{ingrId}")
	public String delete(@PathVariable String recipeId,@PathVariable String ingrId)
	{
		this.ingredientService.delete(new Long(ingrId));
		return "redirect:/recipe/"+recipeId+"/ingredients/";
	}
	
}
