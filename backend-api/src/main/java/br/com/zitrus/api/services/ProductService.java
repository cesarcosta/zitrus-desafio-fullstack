package br.com.zitrus.api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zitrus.api.entities.Product;
import br.com.zitrus.api.exceptions.BusinessException;
import br.com.zitrus.api.exceptions.EntityNotFoundException;
import br.com.zitrus.api.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
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
	
	private void validate(Product product) {
		boolean codeAlreadyExists = productRepository.findByCode(product.getCode()).stream().anyMatch(productExists -> !productExists.equals(product));

		if (codeAlreadyExists) {
			throw new BusinessException("Já existe Produto cadastrado com este código!");
		}
	}
}