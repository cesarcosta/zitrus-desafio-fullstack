package br.com.zitrus.api.services;

import static br.com.zitrus.api.util.IsNullUtil.isNullOrEmpty;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zitrus.api.entities.Product;
import br.com.zitrus.api.entities.ProductType;
import br.com.zitrus.api.enums.MovementType;
import br.com.zitrus.api.exceptions.BusinessException;
import br.com.zitrus.api.exceptions.EntityNotFoundException;
import br.com.zitrus.api.repositories.ProductRepository;
import br.com.zitrus.api.repositories.ProductTypeRepository;
import br.com.zitrus.api.repositories.projections.ProductReportStockQuantity;

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
	
	public List<ProductReportStockQuantity> findProductsByType(UUID productTypeId) {
		return productRepository.getProductsByTypeWithStock(productTypeId);
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
}