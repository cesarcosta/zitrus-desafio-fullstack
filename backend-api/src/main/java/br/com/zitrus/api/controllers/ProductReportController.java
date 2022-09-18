package br.com.zitrus.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zitrus.api.dto.ProductStockReportResponse;
import br.com.zitrus.api.services.ProductService;

@RestController
@RequestMapping("/products/report")
public class ProductReportController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/stock")
	public ResponseEntity<List<ProductStockReportResponse>> search() {
		return ResponseEntity.ok(productService.findProducts());
	}
}