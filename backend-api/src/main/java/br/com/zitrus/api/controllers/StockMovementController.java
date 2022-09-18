package br.com.zitrus.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zitrus.api.dto.StockMovementRequest;
import br.com.zitrus.api.dto.StockMovementResponse;
import br.com.zitrus.api.entities.StockMovement;
import br.com.zitrus.api.mappers.StockMovementMapper;
import br.com.zitrus.api.services.StockMovementService;

@RestController
@RequestMapping("/stock/movement")
public class StockMovementController {

	@Autowired
	private StockMovementService stockMovementService;

	@Autowired
	private StockMovementMapper mapper;

	@PostMapping
	public ResponseEntity<StockMovementResponse> save(@Valid @RequestBody StockMovementRequest request) {
		StockMovement movementRequest = mapper.toEntity(request);
		StockMovement stockMovement = stockMovementService.create(movementRequest);
		StockMovementResponse response = mapper.toModel(stockMovement);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}