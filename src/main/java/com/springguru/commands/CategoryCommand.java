package com.springguru.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand { 
	
	private Long id;
	private String categoryName;
	//private Set<RecipeCommand> recipes = new HashSet<RecipeCommand>();

}
