package com.springguru.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springguru.models.Recipe;
import com.springguru.repositories.RecipeRepository;

@Service
public class ImageServiceImpl implements ImageService {

	private final RecipeRepository recipeRepository;
	
	
	public ImageServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}


	@Override
	public void saveImage(Long idRecipe, MultipartFile file) throws Exception{
		Recipe recipe = this.recipeRepository.findById(idRecipe).orElse(new Recipe());
		Byte[] imageObject = new Byte[file.getBytes().length];
		
		byte[] byteArray = file.getBytes();
		
		for(int i=0; i < byteArray.length ; i++)
			imageObject[i] = byteArray[i];
		
		recipe.setImage(imageObject);
		
		this.recipeRepository.save(recipe);
		
	}

}
