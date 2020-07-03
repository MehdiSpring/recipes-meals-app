package com.springguru.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.springguru.commands.CategoryCommand;
import com.springguru.models.Category;

class CategoryToCategoryCommandTest {

		Long id=1L;
		String categoryName = "CatNameTest";
		
		Category category;
		
		CategoryToCategoryCommand categoryToCategoryCommand;
		
		@BeforeEach
		void setUp() throws Exception {
			category = new Category();
			categoryToCategoryCommand = new CategoryToCategoryCommand();
		}

		@Test
		public void testNullSource()
		{
			assertNull(categoryToCategoryCommand.convert(null));
		}
		
		@Test
		public void testNotNullSource()
		{
			assertNotNull(categoryToCategoryCommand.convert(category));
		}
		
		@Test
		void testConvert() {
			category.setId(id);
			category.setCategoryName(categoryName);
			
			CategoryCommand categoryCommand = categoryToCategoryCommand.convert(category);
			assertEquals(id, categoryCommand.getId());
			assertEquals(categoryName, categoryCommand.getCategoryName());
		}

	


}
