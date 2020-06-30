package com.springguru.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.springguru.commands.UnitOfMeasureCommand;
import com.springguru.models.UnitOfMeasure;

class UOMToUOMCommandTest {

	Long id = 1L;
	String uomDesc = "TestDescription";
	
	UOMToUOMCommand uomToCommand;
	
	UnitOfMeasure uom;
	
	@BeforeEach
	void setUp() throws Exception {
		uom = new UnitOfMeasure();
		uomToCommand = new UOMToUOMCommand();
	}

	@Test
	public void testNullSource()
	{
		assertNull(uomToCommand.convert(null));
	}
	
	@Test
	public void testNoEmtySource()
	{
		assertNotNull(uomToCommand.convert(uom));
	}
	
	@Test
	void testConvert() {
		uom.setId(id);
		uom.setUom(uomDesc);
		
		UnitOfMeasureCommand uomCommand = uomToCommand.convert(uom);
		
		assertEquals(id, uomCommand.getId());
		assertEquals(uomDesc, uomCommand.getUom());
	}

}
