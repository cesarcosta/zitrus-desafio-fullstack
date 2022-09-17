package br.com.zitrus.api.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zitrus.api.dto.ProductRequest;
import br.com.zitrus.api.dto.ProductResponse;
import br.com.zitrus.api.entities.Product;
import br.com.zitrus.api.mappers.ProductMapper;
import br.com.zitrus.api.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductMapper mapper;
	
	@PostMapping
	public ResponseEntity<ProductResponse> save(@Valid @RequestBody ProductRequest productRequest) {
		Product product = productService.save(mapper.toEntity(productRequest));
		ProductResponse response = mapper.toModel(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<ProductResponse>> list() {
		List<Product> products = productService.listAll();
		return ResponseEntity.ok(mapper.toCollectionModel(products));
	}
	
	@PutMapping("/{productId}")
	public ResponseEntity<ProductResponse> update(@PathVariable UUID productId, @Valid @RequestBody ProductRequest request) {
		Product product = mapper.toEntity(request);
		Product productUpdated = productService.update(productId, product);
		return ResponseEntity.ok(mapper.toModel(productUpdated));
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<ProductResponse> remove(@PathVariable UUID productId) {
		productService.remove(productId);
		return ResponseEntity.noContent().build();
	}
}