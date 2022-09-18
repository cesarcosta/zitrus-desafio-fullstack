package br.com.zitrus.api.services;

import static br.com.zitrus.api.util.IsNullUtil.isNullOrEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.zitrus.api.dto.ProductStockReportResponse;
import br.com.zitrus.api.entities.Product;
import br.com.zitrus.api.entities.ProductType;
import br.com.zitrus.api.enums.MovementType;
import br.com.zitrus.api.exceptions.BusinessException;
import br.com.zitrus.api.exceptions.EntityNotFoundException;
import br.com.zitrus.api.repositories.ProductRepository;
import br.com.zitrus.api.repositories.ProductTypeRepository;
import br.com.zitrus.api.repositories.StockMovementRepository;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 17/09/2022
 */
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductTypeRepository productTypeRepository;
	
	@Autowired
	private StockMovementRepository stockMovementRepository;
	
	public Product save(Product product) {
		validate(product);
		return productRepository.save(product);
	}
	
	public List<Product> listAll() {
		return productRepository.findAll();
	}
	
	public Product update(UUID id, Product product) {
		Product productExists = findById(id);
		productExists.setCode(product.getCode());
		productExists.setDescription(product.getDescription());
		productExists.setPrice(product.getPrice());
		productExists.setQuantity(product.getQuantity());
		productExists.setType(product.getType());
		save(productExists);
		return productExists;
	}
	
	public Product findById(UUID id) {
		return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado!"));
	}
	
	public void remove(UUID id) {
		Product product = findById(id);
		productRepository.delete(product);
	}
	
	public void updateStock(UUID id, MovementType movementType, Double quantity) {
		Product product = findById(id);
		Double quantityUpdated = movementType.equals(MovementType.ENTRADA) ? product.getQuantity() + quantity : product.getQuantity() - quantity; 
		product.setQuantity(quantityUpdated);
		productRepository.save(product);
	}
	
	public List<ProductStockReportResponse> findProductsByType(UUID productTypeId) {
		List<ProductStockReportResponse> result = new ArrayList<>();
		
		List<Product> products = findAllByProductType(productTypeId);
		
		if (!isNullOrEmpty(products)) {
			for (Product product : products) {
				Double quantitySoldByProduct = stockMovementRepository.findQuantitySoldByProduct(product.getId());
				ProductStockReportResponse item = createFromProduct(product, quantitySoldByProduct);
				result.add(item);
			}
		}
		return result;
	}
	
	private void validate(Product product) {
		boolean codeAlreadyExists = productRepository.findByCode(product.getCode()).stream().anyMatch(productExists -> !productExists.equals(product));

		if (codeAlreadyExists) {
			throw new BusinessException("Já existe Produto cadastrado com este código!");
		}
		
		if (!isNullOrEmpty(product.getType()) && !isNullOrEmpty(product.getType().getId())) {
			Optional<ProductType> typeExists = productTypeRepository.findById(product.getType().getId());
			if (typeExists.isEmpty()) {
				throw new BusinessException("Tipo de Produto não encontrado!");
			}
		}
	}
	
	private List<Product> findAllByProductType(UUID productTypeId) {
		Product productQuery = new Product();
		if (!isNullOrEmpty(productTypeId)) {
			productQuery.setType(new ProductType(productTypeId));
		}
		
		List<Product> products = productRepository.findAll(Example.of(productQuery));
		return products;
	}
	
	private ProductStockReportResponse createFromProduct(Product product, Double quantitySoldByProduct) {
		ProductStockReportResponse item = new ProductStockReportResponse();
		item.setProductId(product.getId());
		item.setCode(product.getCode());
		item.setDescription(product.getDescription());
		item.setProductTypeId(product.getType().getId());
		item.setProductType(product.getType().getDescription());
		item.setQuantityAvailable(product.getQuantity());
		item.setQuantitySold(quantitySoldByProduct);
		return item;
	}
}