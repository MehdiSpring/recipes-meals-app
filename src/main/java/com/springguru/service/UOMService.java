package com.springguru.service;

import java.util.List;

import com.springguru.commands.UnitOfMeasureCommand;
import com.springguru.models.UnitOfMeasure;

public interface UOMService {

	public List<UnitOfMeasure> findAllUOM();
	public List<UnitOfMeasureCommand> findAllUOMCommand();
}
