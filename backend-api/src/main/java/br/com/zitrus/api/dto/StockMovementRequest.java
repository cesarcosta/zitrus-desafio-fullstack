package br.com.zitrus.api.dto;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zitrus.api.util.DateUtil;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 17/09/2022
 */
public class StockMovementRequest {

	@NotNull
	private UUID productId;
	
	@NotBlank
	private String type;
	
	@NotBlank
	private String date;
	
	@NotNull
	private Double quantity;
	
	@NotNull
	private Double total;

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

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	public Date getDateSale() {
		return DateUtil.getDateFromString(date, DateUtil.DD_MM_YYYY);
	}
	
	@Override
	public String toString() {
		return "StockMovementRequest [productId=" + productId + ", type=" + type + ", date=" + date + ", quantity=" + quantity + ", total=" + total + "]";
	}
}