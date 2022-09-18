package br.com.zitrus.api.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zitrus.api.dto.StockMovementRequest;
import br.com.zitrus.api.dto.StockMovementResponse;
import br.com.zitrus.api.entities.StockMovement;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 17/09/2022
 */
@Component
public class StockMovementMapper {

	@Autowired
	private ModelMapper modelMapper;

	public StockMovementResponse toModel(StockMovement product) {
		return modelMapper.map(product, StockMovementResponse.class);
	}

	public List<StockMovementResponse> toCollectionModel(List<StockMovement> products) {
		return products.stream().map(this::toModel).collect(Collectors.toList());
	}

	public StockMovement toEntity(StockMovementRequest request) {
		return modelMapper.map(request, StockMovement.class);
	}
}