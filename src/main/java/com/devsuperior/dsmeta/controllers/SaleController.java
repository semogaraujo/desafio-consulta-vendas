package com.devsuperior.dsmeta.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSellerMinDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleSellerMinDTO>> getReport(@RequestParam(name = "name", defaultValue = "") String sellerName,
												   @RequestParam(name = "minDate", required = false) LocalDate minDate,
												   @RequestParam(name = "maxDate", required = false)  LocalDate maxDate,
												   Pageable pageable) {
																 	
		Page<SaleSellerMinDTO> dto = service.getReport(sellerName, minDate, maxDate, pageable);

		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SummaryDTO>> getSummary(@RequestParam(name = "minDate", required = false) LocalDate minDate,
													   @RequestParam(name = "maxDate", required = false)  LocalDate maxDate
													   ) {
		
		List<SummaryDTO> dto = service.getSummary(minDate, maxDate);
				
		return ResponseEntity.ok(dto);
	}
}
