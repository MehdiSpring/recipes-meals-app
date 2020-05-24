package com.springguru.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.springguru.models.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{

	Optional<Recipe> findByDescription(String description);
}
