package com.springguru.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.springguru.commands.UnitOfMeasureCommand;
import com.springguru.converters.UOMToUOMCommand;
import com.springguru.models.UnitOfMeasure;
import com.springguru.repositories.UnitOfMeasureRepository;

@Service
public class UOMServiceImpl implements UOMService {

	private final UnitOfMeasureRepository uomRepository;
	private final UOMToUOMCommand uomToUomCommand;
	
	public UOMServiceImpl(UnitOfMeasureRepository uomRepository, UOMToUOMCommand uomToUomCommand) {		
		this.uomRepository = uomRepository;
		this.uomToUomCommand = uomToUomCommand;
	}


	@Override
	public List<UnitOfMeasure> findAllUOM() {
		List<UnitOfMeasure> listUom = new ArrayList();
		this.uomRepository.findAll().iterator().forEachRemaining(listUom ::add);
		return listUom;
	}


	@Override
	public List<UnitOfMeasureCommand> findAllUOMCommand() {
		List<UnitOfMeasure> listUom = this.findAllUOM();
		List<UnitOfMeasureCommand> listUomCommand = new ArrayList<UnitOfMeasureCommand>();
		
		for(UnitOfMeasure uom:listUom)
		{
			listUomCommand.add(this.uomToUomCommand.convert(uom));
		}
		
		return listUomCommand;
	}

}
