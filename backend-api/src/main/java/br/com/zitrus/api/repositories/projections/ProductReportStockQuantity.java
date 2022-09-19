package br.com.zitrus.api.repositories.projections;

import java.util.UUID;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 18/09/2022
 */
public interface ProductReportStockQuantity {

	public UUID getProductId();
	
	public String getDescription();
	
	public String getCode();
	
	public UUID getProductTypeId();
	
	public String getProductType();
	
	public Double getQuantityAvailable();
	
	public Double getQuantitySold();
}