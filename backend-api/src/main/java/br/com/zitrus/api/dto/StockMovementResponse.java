package br.com.zitrus.api.dto;

import java.util.UUID;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 17/09/2022
 */
public class StockMovementResponse {

	private UUID id;
	
	private UUID productId;
	
	private String type;
	
	private String date;
	
	private Double total;
	
	private Double quantity;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getProductId() {
		return productId;
	}

	public void setProductId(UUID productId) {
		this.productId = productId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "StockMovementResponse [id=" + id + ", productId=" + productId + ", type=" + type + ", date=" + date + ", total=" + total + ", quantity=" + quantity + "]";
	}
}