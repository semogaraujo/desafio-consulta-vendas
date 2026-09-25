package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SellerSaleMinProjection;

public class SummaryDTO {

	public String sellerName;
	public Double sum;

	public SummaryDTO() {
	}

	public SummaryDTO(String sellerName, Double sum) {	
		this.sellerName = sellerName;
		this.sum = sum;
	}

	public SummaryDTO(SellerSaleMinProjection projection) {       
		
		this.sellerName = projection.getSellerName();
		this.sum = projection.getAmount();
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "SummaryDTO [sellerName=" + sellerName + ", sum=" + sum + "]";
	}

}
