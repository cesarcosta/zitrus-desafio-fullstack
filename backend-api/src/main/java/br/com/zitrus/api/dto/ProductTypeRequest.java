package br.com.zitrus.api.dto;

import javax.validation.constraints.NotBlank;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 17/09/2022
 */
public class ProductTypeRequest {

	@NotBlank
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ProductTypeRequest [description=" + description + "]";
	}
}