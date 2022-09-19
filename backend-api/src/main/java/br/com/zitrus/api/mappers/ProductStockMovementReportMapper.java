package br.com.zitrus.api.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zitrus.api.dto.ProductStockMovementReportResponse;
import br.com.zitrus.api.repositories.projections.ProductReportSale;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 18/09/2022
 */
@Component
public class ProductStockMovementReportMapper {

	@Autowired
	private ModelMapper modelMapper;

	public ProductStockMovementReportResponse toModel(ProductReportSale product) {
		return modelMapper.map(product, ProductStockMovementReportResponse.class);
	}

	public List<ProductStockMovementReportResponse> toCollectionModel(List<ProductReportSale> products) {
		return products.stream().map(this::toModel).collect(Collectors.toList());
	}
}