package com.springguru.repositories;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import com.springguru.controllers.IndexController;
import com.springguru.models.UnitOfMeasure;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
public class UnitOfMeasureRepositoryTestIT { 

	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Mock
	Model model;
	
	@Autowired
	IndexController indexController;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		//indexController = new IndexController(categoryRepository);
	}

	@Test
	/*
	 * This annotation indicates that the application context associated with this test has to be closed and removed
	 * We can use in cases when we modifiy for a example a statut of bean ...
	 * So the next method test will have a new application context loaded */
	//@DirtiesContext
	public void testFindByUom() {
		Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByUom("teaspoon");
		assertEquals("teaspoon", uom.get().getUom());
	}
	
	@Test
	public void testIndexController() throws Exception
	{
		indexController.getIndexMsg(model);
		ArgumentCaptor<String> categoryName = ArgumentCaptor.forClass(String.class);
		verify(model, times(1)).addAttribute(Mockito.eq("category"), categoryName.capture());
		String name = categoryName.getValue();
		assertEquals(categoryRepository.findByCategoryName("Morrocan").get().getCategoryName(), name);
	}

}
