package br.com.zitrus.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductRequest {

	@NotBlank
	private String code;

	@NotBlank
	private String description;

	@NotNull
	private Double quantity;

	@NotNull
	private Double price;

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
		return "ProductRequest [code=" + code + ", description=" + description + ", quantity=" + quantity + ", price=" + price + "]";
	}
}