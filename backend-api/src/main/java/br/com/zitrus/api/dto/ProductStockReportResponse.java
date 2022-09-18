package br.com.zitrus.api.dto;

import java.util.UUID;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 18/09/2022
 */
public class ProductStockReportResponse {

	private UUID productId;
	
	private String code;
	
	private String description;
	
	private UUID productTypeId;
	
	private String productType;
	
	private Double quantityAvailable;
	
	private Double quantitySold;

	public UUID getProductId() {
		return productId;
	}
	
	public void setProductId(UUID productId) {
		this.productId = productId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public UUID getProductTypeId() {
		return productTypeId;
	}
	
	public void setProductTypeId(UUID productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Double getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(Double quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public Double getQuantitySold() {
		return quantitySold;
	}

	public void setQuantitySold(Double quantitySold) {
		this.quantitySold = quantitySold;
	}

	@Override
	public String toString() {
		return "ProductStockReportResponse [productId=" + productId + ", code=" + code + ", description=" + description	+ 
				", productTypeId=" + productTypeId + ", productType=" + productType + ", quantityAvailable=" + quantityAvailable + ", quantitySold=" + quantitySold + "]";
	}
}