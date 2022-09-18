package br.com.zitrus.api.controllers;

import static br.com.zitrus.api.util.IsNullUtil.isNullOrEmpty;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.zitrus.api.dto.ProductStockReportResponse;
import br.com.zitrus.api.services.ProductService;
import br.com.zitrus.api.util.UUIDUtil;

@RestController
@RequestMapping("/products/report")
public class ProductReportController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/stock")
	public ResponseEntity<List<ProductStockReportResponse>> search(@RequestParam("type") String type) {
		boolean isUUIDValid = !isNullOrEmpty(type) && UUIDUtil.isValidUUID(type); 
		UUID search = isUUIDValid ? UUID.fromString(type) : null;
		return ResponseEntity.ok(productService.findProductsByType(search));
	}
}