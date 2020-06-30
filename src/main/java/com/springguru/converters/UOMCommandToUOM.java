package com.springguru.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.springguru.commands.UnitOfMeasureCommand;
import com.springguru.models.UnitOfMeasure;

import lombok.Synchronized;

@Component
public class UOMCommandToUOM implements Converter<UnitOfMeasureCommand, UnitOfMeasure>{
	
	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasure convert(UnitOfMeasureCommand source) {
		if(source == null)
		   return null;
		
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(source.getId());
		uom.setUom(source.getUom());
		
		return uom;
	}
	
	
	
	

}
