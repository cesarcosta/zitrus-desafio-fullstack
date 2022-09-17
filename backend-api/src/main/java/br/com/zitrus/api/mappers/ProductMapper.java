package br.com.zitrus.api.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zitrus.api.dto.ProductRequest;
import br.com.zitrus.api.dto.ProductResponse;
import br.com.zitrus.api.entities.Product;

@Component
public class ProductMapper {

	@Autowired
	private ModelMapper modelMapper;

	public ProductResponse toModel(Product product) {
		return modelMapper.map(product, ProductResponse.class);
	}

	public List<ProductResponse> toCollectionModel(List<Product> products) {
		return products.stream().map(this::toModel).collect(Collectors.toList());
	}
	
	public Product toEntity(ProductRequest request) {
		return modelMapper.map(request, Product.class);
	}
}