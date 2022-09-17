package br.com.zitrus.api.dto;

import java.util.UUID;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 17/09/2022
 */
public class ProductResponse {

	private UUID id;
	
	private UUID productTypeId;
	
	private String code;
	
	private String description;
	
	private Double quantity;
	
	private Double price;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public UUID getProductTypeId() {
		return productTypeId;
	}
	
	public void setProductTypeId(UUID productTypeId) {
		this.productTypeId = productTypeId;
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

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductResponse [id=" + id + ", productTypeId=" + productTypeId + ", code=" + code + ", description="
				+ description + ", quantity=" + quantity + ", price=" + price + "]";
	}
}