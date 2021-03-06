package com.springguru.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String categoryName;
	
	@ManyToMany(mappedBy = "categories")
	private Set<Recipe> recipes = new HashSet<Recipe>();

	

	/*
	 * public Long getId() { return id; }
	 * 
	 * public void setId(Long id) { this.id = id; }
	 * 
	 * public String getCategoryName() { return categoryName; }
	 * 
	 * public void setCategoryName(String categoryName) { this.categoryName =
	 * categoryName; }
	 * 
	 * public Set<Recipe> getRecipes() { return recipes; }
	 * 
	 * public void setRecipes(Set<Recipe> recipes) { this.recipes = recipes; }
	 */
	
	
	
}

