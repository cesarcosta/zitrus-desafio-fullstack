package br.com.zitrus.api.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zitrus.api.dto.ProductStockReportResponse;
import br.com.zitrus.api.repositories.projections.ProductReportStockQuantity;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 18/09/2022
 */
@Component
public class ProductStockReportMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public ProductStockReportResponse toModel(ProductReportStockQuantity product) {
		return modelMapper.map(product, ProductStockReportResponse.class);
	}

	public List<ProductStockReportResponse> toCollectionModel(List<ProductReportStockQuantity> products) {
		return products.stream().map(this::toModel).collect(Collectors.toList());
	}
}