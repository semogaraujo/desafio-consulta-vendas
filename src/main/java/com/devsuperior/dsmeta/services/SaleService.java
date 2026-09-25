package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSellerMinDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SellerSaleMinProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	public Page<SaleSellerMinDTO> getReport(String sellerName, LocalDate minDate, LocalDate maxDate, Pageable pageable) {
		
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		
		if (minDate == null) {
			minDate = today.minusYears(1L);			
		}
		
		if (maxDate == null) {
			maxDate = today;			
		}		
	
		return repository.searchReport(minDate, maxDate, sellerName, pageable);
	}
		
	public List<SummaryDTO> getSummary(LocalDate minDate, LocalDate maxDate) {
		
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		
		if (minDate == null) {
			minDate = today.minusYears(1L);			
		}
		
		if (maxDate == null) {
			maxDate = today;			
		}		
	
		List<SellerSaleMinProjection> result = repository.searchSummary(minDate, maxDate);		
		List<SummaryDTO> dto = result.stream().map(x -> new SummaryDTO(x)).collect(Collectors.toList());
		           
		return dto;
	}
	/*
	public List<SummaryDTO> getSummary(LocalDate minDate, LocalDate maxDate) {
		
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		
		if (minDate == null) {
			minDate = today.minusYears(1L);			
		}
		
		if (maxDate == null) {
			maxDate = today;			
		}		
	
		List<SummaryDTO> dto = repository.searchSummaryJpql(minDate, maxDate);		
				
		return dto;
	}
	*/
}

