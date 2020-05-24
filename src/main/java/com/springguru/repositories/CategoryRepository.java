package com.springguru.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.springguru.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	Optional<Category> findByCategoryName(String categoryName);
	

}
