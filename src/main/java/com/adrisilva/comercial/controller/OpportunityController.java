package com.adrisilva.comercial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.adrisilva.comercial.model.Opportunity;
import com.adrisilva.comercial.repository.OpportunityRepository;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/opportunities")
public class OpportunityController {

	@Autowired
	private OpportunityRepository opportunities;
	
	@GetMapping
	public List<Opportunity> list()
	{
		return opportunities.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Opportunity> show(@PathVariable Long id)
	{
		Optional<Opportunity> opportunity =  opportunities.findById(id);
		
		if(!opportunity.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(opportunity.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Opportunity store(@Valid @RequestBody Opportunity opportunity)
	{
		Optional<Opportunity> opportunityCheck = opportunities
				.findByDescriptionAndNameProspector(opportunity.getDescription(), opportunity.getNameProspector());
		
		if(opportunityCheck.isPresent()) {
			this.showErrorMessage("Já existe uma oportunidade para esse prospector com a mesma descrição");
		}
		
		return opportunities.save(opportunity);
	}
	
	@PutMapping
	public Opportunity update(@Valid @RequestBody Opportunity opportunity)
	{
		Optional<Opportunity> opportunityCheck = opportunities
				.findByDescriptionAndNameProspector(opportunity.getDescription(), opportunity.getNameProspector());
		
		if(opportunityCheck.isPresent()) {
			this.showErrorMessage("Já existe uma oportunidade para esse prospector com a mesma descrição");
		}
		
		return opportunities.save(opportunity);
	}
	
	private Exception showErrorMessage(String message)
	{
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
	}
}
