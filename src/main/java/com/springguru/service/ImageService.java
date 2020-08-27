package com.springguru.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	
	public void saveImage(Long idRecipe, MultipartFile file) throws Exception;

}
