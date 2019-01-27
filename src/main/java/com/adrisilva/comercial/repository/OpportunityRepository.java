package com.adrisilva.comercial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adrisilva.comercial.model.Opportunity;

public interface OpportunityRepository extends JpaRepository<Opportunity, Long>{

	Optional<Opportunity> findByDescriptionAndNameProspector(String description, String nameProspector);
	
}
