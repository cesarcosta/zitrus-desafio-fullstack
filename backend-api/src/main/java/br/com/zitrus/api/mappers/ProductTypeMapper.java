package br.com.zitrus.api.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zitrus.api.dto.ProductTypeRequest;
import br.com.zitrus.api.dto.ProductTypeResponse;
import br.com.zitrus.api.entities.ProductType;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 17/09/2022
 */
@Component
public class ProductTypeMapper {

	@Autowired
	private ModelMapper modelMapper;

	public ProductTypeResponse toModel(ProductType product) {
		return modelMapper.map(product, ProductTypeResponse.class);
	}

	public List<ProductTypeResponse> toCollectionModel(List<ProductType> products) {
		return products.stream().map(this::toModel).collect(Collectors.toList());
	}

	public ProductType toEntity(ProductTypeRequest request) {
		return modelMapper.map(request, ProductType.class);
	}
}