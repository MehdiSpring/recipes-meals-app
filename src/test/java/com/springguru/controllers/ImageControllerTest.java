package com.springguru.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.springguru.commands.RecipeCommand;
import com.springguru.models.Recipe;
import com.springguru.service.ImageService;
import com.springguru.service.RecipeService;

@ExtendWith(MockitoExtension.class)
class ImageControllerTest {

	@Mock
	RecipeService recipeService;
	
	@Mock
	ImageService imageService;
	
	@InjectMocks
	ImageController imageController;
	
	MockMvc mockMVC;
	
	MockMultipartFile mockMultiPartFile;
	
	Recipe recipe;
	
	@BeforeEach
	void setUp() throws Exception {
		mockMVC = MockMvcBuilders.standaloneSetup(imageController).build();
		recipe = new Recipe();
		recipe.setId(1L);
		
	}

	@Test
	void testUploadImage() throws Exception {
		when(recipeService.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(recipe));
		mockMVC.perform(get("/image/1/upload/")).andExpect(status().isOk())
												.andExpect(view().name("/recipe/imageForm"));
	}

	@Test
	void testSaveImage() throws Exception{
		String chaineDeTest = "hmmm";
		mockMultiPartFile = new MockMultipartFile("imagefile", "test.txt", "text", chaineDeTest.getBytes());
		mockMVC.perform(multipart("/image/1/save/").file(mockMultiPartFile)).andExpect(status().is3xxRedirection());
																			
	}
	
	@Test
	void testLoadImageFromDB() throws Exception{
		String chaineDeTest = "hmmm";
		byte[] array = chaineDeTest.getBytes();
		Byte[] imageObject = new Byte[array.length];
		
		for(int i=0; i<array.length;i++)
			imageObject[i] = array[i];
		
		RecipeCommand recipeCom = new RecipeCommand();
		recipeCom.setImage(imageObject);
		
		when(recipeService.findByIdCommand(ArgumentMatchers.anyLong())).thenReturn(recipeCom );
		
		MockHttpServletResponse response = mockMVC.perform(get("/image/1/load/")).andExpect(status().isOk())
											  .andReturn().getResponse();
		
		byte[] image = response.getContentAsByteArray();
		
		assertEquals(4, image.length);
	}

}
