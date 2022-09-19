package br.com.zitrus.api.services;

import static br.com.zitrus.api.util.IsNullUtil.isNullOrEmpty;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zitrus.api.entities.Product;
import br.com.zitrus.api.entities.ProductType;
import br.com.zitrus.api.exceptions.BusinessException;
import br.com.zitrus.api.exceptions.EntityNotFoundException;
import br.com.zitrus.api.repositories.ProductRepository;
import br.com.zitrus.api.repositories.ProductTypeRepository;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 17/09/2022
 */
@Service
public class ProductTypeService {

	@Autowired
	private ProductTypeRepository productTypeRepository;

	@Autowired
	private ProductRepository productRepository;

	public ProductType save(ProductType type) {
		validate(type);
		return productTypeRepository.save(type);
	}

	public List<ProductType> listAll() {
		return productTypeRepository.findAll();
	}

	public ProductType update(UUID id, ProductType type) {
		ProductType productTypeExists = findById(id);
		productTypeExists.setDescription(type.getDescription());
		save(productTypeExists);
		return productTypeExists;
	}

	public void remove(UUID id) {
		ProductType productType = findById(id);

		List<Product> productsByType = productRepository.findByProductType(productType.getId());

		if (!isNullOrEmpty(productsByType)) {
			throw new BusinessException("Não é possível a exclusão do Tipo! Existem produtos relacionados com ele!");
		}
		
		productTypeRepository.delete(productType);
	}

	public ProductType findById(UUID id) {
		return productTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tipo de Produto não encontrado!"));
	}

	private void validate(ProductType type) {
		boolean descriptionAlreadyExists = productTypeRepository.findByDescription(type.getDescription()).stream().anyMatch(item -> !type.equals(item));

		if (descriptionAlreadyExists) {
			throw new BusinessException("Já existe um Tipo de Produto com esta descrição!");
		}
	}
}
