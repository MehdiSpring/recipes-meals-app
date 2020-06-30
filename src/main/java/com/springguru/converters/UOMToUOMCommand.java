package com.springguru.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.springguru.commands.UnitOfMeasureCommand;
import com.springguru.models.UnitOfMeasure;

import lombok.Synchronized;

@Component
public class UOMToUOMCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand>{

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasureCommand convert(UnitOfMeasure source) {

		if(source == null)
		   return null;
		
		UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
		uomCommand.setId(source.getId());
		uomCommand.setUom(source.getUom());
		
		return uomCommand;
	}
	

}
