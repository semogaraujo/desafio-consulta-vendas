package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleSellerMinDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SellerSaleMinProjection;


public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query(value = "SELECT new com.devsuperior.dsmeta.dto.SaleSellerMinDTO(sa.id, sa.date, sa.amount, se.name) "
			+ "FROM Sale sa " 
			+ "JOIN sa.seller se " 
			+ "WHERE sa.date BETWEEN :minDate AND :maxDate "
			+ "AND UPPER(se.name) LIKE UPPER(CONCAT('%', :sellerName, '%'))")
	Page<SaleSellerMinDTO> searchReport(LocalDate minDate, LocalDate maxDate, String sellerName, Pageable pageable);
	
	@Query(nativeQuery = true, value = "SELECT se.name AS sellerName, SUM(sa.amount) AS total "
			+ "FROM tb_seller se "
			+ "INNER JOIN tb_sales sa "
			+ "ON se.id = sa.seller_id "
			+ "WHERE sa.date BETWEEN :minDate AND :maxDate "
			+ "GROUP BY se.name")
	List<SellerSaleMinProjection> searchSummary(LocalDate minDate, LocalDate maxDate);
		
	@Query(value = "SELECT new com.devsuperior.dsmeta.dto.SummaryDTO(sa.seller.name AS sellerName, SUM(sa.amount) AS total) "
			+ "FROM Sale sa " 
			+ "WHERE sa.date BETWEEN :minDate AND :maxDate "
			+ "GROUP BY sa.seller.name")
	List<SummaryDTO> searchSummaryJpql(LocalDate minDate, LocalDate maxDate);
	
	
	/*
	@Query(value = "SELECT  new com.devsuperior.uri2609.dto.CategorySumDTO(pr.category.name, SUM(pr.amount))"
			+ "FROM Product pr "	
			+ "GROUP BY pr.category.name")
	List<CategorySumDTO> search2();
	*/
									
}

