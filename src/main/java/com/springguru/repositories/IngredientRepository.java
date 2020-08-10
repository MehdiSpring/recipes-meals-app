package com.springguru.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springguru.models.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

}

