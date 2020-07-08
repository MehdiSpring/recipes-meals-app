package com.springguru.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.springguru.models.Category;
import com.springguru.repositories.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	
	@Override
	public Set<Category> findAll() {
		Set<Category> categories = new HashSet<Category>();
		this.categoryRepository.findAll().iterator().forEachRemaining(categories::add);
		
		return categories;
	}

}
