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

import br.com.zitrus.api.dto.ProductStockMovementReportResponse;
import br.com.zitrus.api.dto.ProductStockReportResponse;
import br.com.zitrus.api.mappers.ProductStockMovementReportMapper;
import br.com.zitrus.api.mappers.ProductStockReportMapper;
import br.com.zitrus.api.repositories.projections.ProductReportSale;
import br.com.zitrus.api.repositories.projections.ProductReportStockQuantity;
import br.com.zitrus.api.services.ProductService;
import br.com.zitrus.api.services.StockMovementService;
import br.com.zitrus.api.util.UUIDUtil;

@RestController
@RequestMapping("/products/report")
public class ProductReportController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private StockMovementService stockMovementService;
	
	@Autowired
	private ProductStockMovementReportMapper productStockMovementMapper;
	
	@Autowired
	private ProductStockReportMapper productStockReportMapper;
	
	@GetMapping("/stock")
	public ResponseEntity<List<ProductStockReportResponse>> listStocks(@RequestParam(value = "type", required = false) String type) {
		boolean isUUIDValid = !isNullOrEmpty(type) && UUIDUtil.isValidUUID(type); 
		UUID search = isUUIDValid ? UUID.fromString(type) : null;
		List<ProductReportStockQuantity> products = productService.findProductsByType(search);
		return ResponseEntity.ok(productStockReportMapper.toCollectionModel(products));
	}
	
	@GetMapping("/movements")
	public ResponseEntity<List<ProductStockMovementReportResponse>> listMovements(@RequestParam(value = "product", required = false, defaultValue = "%%") String product) {
		List<ProductReportSale> itens = stockMovementService.listProducts(!isNullOrEmpty(product) ? product : null);
		return ResponseEntity.ok(productStockMovementMapper.toCollectionModel(itens));
	}
}