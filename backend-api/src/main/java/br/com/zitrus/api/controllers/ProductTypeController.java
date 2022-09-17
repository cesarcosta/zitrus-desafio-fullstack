package br.com.zitrus.api.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zitrus.api.dto.ProductTypeRequest;
import br.com.zitrus.api.dto.ProductTypeResponse;
import br.com.zitrus.api.entities.ProductType;
import br.com.zitrus.api.mappers.ProductTypeMapper;
import br.com.zitrus.api.services.ProductTypeService;

@RestController
@RequestMapping("/types")
public class ProductTypeController {

	@Autowired
	private ProductTypeService productTypeService;
	
	@Autowired
	private ProductTypeMapper mapper;
	
	@PostMapping
	public ResponseEntity<ProductTypeResponse> save(@Valid @RequestBody ProductTypeRequest request) {
		ProductType productType = productTypeService.save(mapper.toEntity(request));
		ProductTypeResponse response = mapper.toModel(productType);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<ProductTypeResponse>> list() {
		List<ProductType> types = productTypeService.listAll();
		return ResponseEntity.ok(mapper.toCollectionModel(types));
	}
	
	@PutMapping("/{typeId}")
	public ResponseEntity<ProductTypeResponse> save(@PathVariable UUID typeId, @Valid @RequestBody ProductTypeRequest request) {
		ProductType productType = productTypeService.update(typeId, mapper.toEntity(request));
		ProductTypeResponse response = mapper.toModel(productType);
		return ResponseEntity.ok(response);
	}
}