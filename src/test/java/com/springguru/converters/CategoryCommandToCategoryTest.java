package com.springguru.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.springguru.commands.CategoryCommand;
import com.springguru.models.Category;

class CategoryCommandToCategoryTest {

	Long id=1L;
	String categoryName = "CatNameTest";
	
	CategoryCommand categoryCommand;
	
	CategoryCommandToCategory categoryCommandToCategory;
	
	@BeforeEach
	void setUp() throws Exception {
		categoryCommand = new CategoryCommand();
		categoryCommandToCategory = new CategoryCommandToCategory();
	}

	@Test
	public void testNullSource()
	{
		assertNull(categoryCommandToCategory.convert(null));
	}
	
	@Test
	public void testNotNullSource()
	{
		assertNotNull(categoryCommandToCategory.convert(categoryCommand));
	}
	
	@Test
	void testConvert() {
		categoryCommand.setId(id);
		categoryCommand.setCategoryName(categoryName);
		
		Category category = categoryCommandToCategory.convert(categoryCommand);
		assertEquals(id, category.getId());
		assertEquals(categoryName, category.getCategoryName());
	}

}
