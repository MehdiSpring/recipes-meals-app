package com.springguru.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	private String description;
	private BigDecimal amount;
	
	//EAGER is the default behavior of @OneToOne relation
	@OneToOne(fetch = FetchType.EAGER)
	private UnitOfMeasure uom;
		
	@ManyToOne
	private Recipe recipe;
	 
	  
	public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) 
	{ 
		  this.recipe = recipe; this.description = description;
	      this.amount = amount; this.uom = uom; 
	}

	public Ingredient()
	{
		
	}

	
	 
	
	
	
	/*
	 * public UnitOfMeasure getUom() { return uom; } public void
	 * setUom(UnitOfMeasure uom) { this.uom = uom; } public Long getId() { return
	 * id; } public void setId(Long id) { this.id = id; } public String
	 * getDescription() { return description; } public void setDescription(String
	 * description) { this.description = description; } public BigDecimal
	 * getAmount() { return amount; } public void setAmount(BigDecimal amount) {
	 * this.amount = amount; } public Recipe getRecipe() { return recipe; } public
	 * void setRecipe(Recipe recipe) { this.recipe = recipe; }
	 */
	
	

}
