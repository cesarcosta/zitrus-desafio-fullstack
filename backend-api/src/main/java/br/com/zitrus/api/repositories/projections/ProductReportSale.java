package br.com.zitrus.api.repositories.projections;

import java.util.UUID;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 18/09/2022
 */
public interface ProductReportSale {

	public UUID getProductId();
	
	public String getDescription();
	
	public UUID getProductTypeId();
	
	public String getProductType();
	
	public Double getPrice();
	
	public Double getTotalSold();
	
	public Double getQuantitySold();
}
