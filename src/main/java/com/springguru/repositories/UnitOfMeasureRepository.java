package com.springguru.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.springguru.models.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long>{

	Optional<UnitOfMeasure> findByUom(String uom); 
}
