package br.com.zitrus.api.dto;

import static br.com.zitrus.api.util.IsNullUtil.isNullOrEmpty;

import java.util.UUID;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 18/09/2022
 */
public class ProductStockMovementReportResponse {

	public UUID productId;

	public String description;

	public UUID productTypeId;

	public String productType;

	public Double price;

	public Double totalSold;

	public Double quantitySold;

	public UUID getProductId() {
		return productId;
	}

	public void setProductId(UUID productId) {
		this.productId = productId;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTotalSold() {
		return totalSold;
	}

	public void setTotalSold(Double totalSold) {
		this.totalSold = totalSold;
	}

	public Double getQuantitySold() {
		return quantitySold;
	}

	public void setQuantitySold(Double quantitySold) {
		this.quantitySold = quantitySold;
	}

	public Double getTotalProfit() {
		Double result = 0d;

		if (!isNullOrEmpty(getPrice()) && !isNullOrEmpty(getTotalSold())) {
			result = getTotalSold() - getPrice();
		}
		return result;
	}

	@Override
	public String toString() {
		return "ProductStockMovementReportResponse [productId=" + productId + ", description=" + description
				+ ", productTypeId=" + productTypeId + ", productType=" + productType + ", price=" + price + ", totalSold=" + totalSold + ", quantitySold=" + quantitySold + "]";
	}
}