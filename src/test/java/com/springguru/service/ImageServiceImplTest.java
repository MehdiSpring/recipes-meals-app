package com.springguru.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.springguru.models.Recipe;
import com.springguru.repositories.RecipeRepository;

class ImageServiceImplTest {

	@Mock
	RecipeRepository recipeRepository;
	
	ImageService imageService;
	
	Recipe recipe;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		imageService = new ImageServiceImpl(recipeRepository);
		
		recipe = new Recipe();
		recipe.setId(1L);
	}

	@Test
	void testSaveImage() throws Exception{
		
		MultipartFile multiPartFile = new MockMultipartFile("test.txt", new byte[2]);
		when(recipeRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(recipe));
		
		ArgumentCaptor<Recipe> captor = ArgumentCaptor.forClass(Recipe.class);
		
		imageService.saveImage(recipe.getId(), multiPartFile);
		
		verify(recipeRepository, times(1)).save(captor.capture());
		
		Recipe savedRecipe = captor.getValue();
		
		assertEquals(2, savedRecipe.getImage().length);
		
		
	}

}
