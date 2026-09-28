package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SellerSaleMinProjection;

public class SummaryDTO {

	public String sellerName;
	public Double total;

	public SummaryDTO() {
	}

	public SummaryDTO(String sellerName, Double total) {
		this.sellerName = sellerName;
		this.total = total;
	}

	public SummaryDTO(SellerSaleMinProjection projection) {
		sellerName = projection.getSellerName();
		total = projection.getTotal();
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
