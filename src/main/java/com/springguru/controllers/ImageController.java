package com.springguru.controllers;


import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springguru.commands.RecipeCommand;
import com.springguru.service.ImageService;
import com.springguru.service.RecipeService;


import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
public class ImageController {
	
	private final RecipeService recipeService;
	private final ImageService imageService;
	

	public ImageController(RecipeService recipeService, ImageService imageService) {
		this.recipeService = recipeService;
		this.imageService = imageService;
	}

	@GetMapping("/image/{id}/upload/")
	public String uploadImage(@PathVariable String id, Model model)
	{
		model.addAttribute("recipe", this.recipeService.findById(new Long(id)).get());
		return "/recipe/imageForm";
	}
	
	@PostMapping("/image/{id}/save/")
	public String saveImage(@RequestParam(name = "imagefile") MultipartFile file, @PathVariable String id) throws Exception
	{
		log.info("The image has been saved succesfully : file size : "+file.getBytes().length+" - id : "+id);
		this.imageService.saveImage(new Long(id), file);
		
		return "redirect:/recipe/show/"+id;
	}
	
	@GetMapping("/image/{id}/load/")
	public void loadImageFromDB(@PathVariable String id, HttpServletResponse response) throws Exception
	{
		RecipeCommand recipeCommand = this.recipeService.findByIdCommand(new Long(id));
		Byte[] imageObject = recipeCommand.getImage();
		byte[] arrayOfByte = new byte[imageObject.length];
		
		for(int i=0; i< imageObject.length; i++)
			arrayOfByte[i] = imageObject[i];
		
		OutputStream os = response.getOutputStream();
		os.write(arrayOfByte);
		
		os.close();
		
	}

}
