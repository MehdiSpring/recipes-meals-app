package com.springguru.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.springguru.commands.UnitOfMeasureCommand;
import com.springguru.models.UnitOfMeasure;

class UOMCommandToUOMTest {

		
	Long id = 1L;
	String uomDesc = "uom-test";
	
	UOMCommandToUOM uomCommandToUom;
	UnitOfMeasureCommand uomCommand;
	
	@BeforeEach
	void setUp() throws Exception {
		uomCommand = new UnitOfMeasureCommand();
		uomCommandToUom = new UOMCommandToUOM();
	}

	@Test
	public void testNullSource()
	{
		assertNull(uomCommandToUom.convert(null));
	}
	
	@Test
	public void testNoEmptySource()
	{
		
		assertNotNull(uomCommandToUom.convert(uomCommand));
	}
	
	@Test
	void testConvert() {
		uomCommand.setId(id);
		uomCommand.setUom(uomDesc);
		UnitOfMeasure uom = uomCommandToUom.convert(uomCommand);
		
		assertEquals(id, uom.getId());
		assertEquals(uomDesc, uom.getUom());
	}

}
