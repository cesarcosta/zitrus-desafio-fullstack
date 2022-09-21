package br.com.zitrus.api.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.zitrus.api.entities.Product;
import br.com.zitrus.api.repositories.projections.ProductReportStockQuantity;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 20/09/2022
 */
@DataJpaTest
@DisplayName("Tests for Product Repository")
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;
	
	@Test
	@DisplayName("Save Product when Successfull")
	public void save_PersistProduct_whenSuccessfull() {
		Product product = createProduct();
		Product productSaved = productRepository.save(product);
		Assertions.assertThat(productSaved).isNotNull();
		Assertions.assertThat(productSaved.getId()).isNotNull();
		Assertions.assertThat(productSaved.getDescription()).isEqualTo(product.getDescription());
	}
	
	@Test
	@DisplayName("Update Product when Successfull")
	public void save_UpdateProduct_whenSuccessfull() {
		Product product = createProduct();
		Product productSaved = productRepository.save(product);
		
		productSaved.setDescription("Product updated");
		
		Product productUpdated = productRepository.save(productSaved);
		
		Assertions.assertThat(productUpdated).isNotNull();
		Assertions.assertThat(productUpdated.getId()).isNotNull();
		Assertions.assertThat(productUpdated.getDescription()).isEqualTo(productSaved.getDescription());
	}
	
	@Test
	@DisplayName("Delete Product when Successfull")
	public void delete_RemovesProduct_whenSuccessfull() {
		Product product = createProduct();
		
		Product productSaved = productRepository.save(product);
		
		productRepository.delete(productSaved);
		
		Optional<Product> productOptional = productRepository.findById(productSaved.getId());
		
		Assertions.assertThat(productOptional).isEmpty();
	}
	
	@Test
	@DisplayName("Find by Code returns a Product when Successfull")
	public void findByCode_ReturnsAProduct_whenSuccessfull() {
		Product product = createProduct();
		
		Product productSaved = productRepository.save(product);
		
		String code = productSaved.getCode();
		
		Optional<Product> productByCode = productRepository.findByCode(code);
		
		Assertions.assertThat(productByCode).isNotEmpty();
		Assertions.assertThat(productByCode.get().getId()).isNotNull();
		Assertions.assertThat(productByCode.get().getCode()).isEqualTo(code);
	}
	
	@Test
	@DisplayName("Find by Code returns a Empty Object when no product found")
	public void findByCode_ReturnsEmptyObject_whenNoProductFound() {
		Optional<Product> productByCode = productRepository.findByCode("847569347634");
		Assertions.assertThat(productByCode).isEmpty();
	}
	
	@Test
	@DisplayName("Find All returns a list Product when Successfull")
	public void findAll_ReturnsListProduct_whenSuccessfull() {
		Product product = createProduct();
		
		Product productSaved = productRepository.save(product);
		
		List<Product> products = productRepository.findAll();
		
		Assertions.assertThat(products).isNotEmpty();
		Assertions.assertThat(products).contains(productSaved);
	}
	
	@Test
	@DisplayName("Find by ID returns a Product when Successfull")
	public void findById_ReturnsProduct_whenSuccessfull() {
		Product product = createProduct();
		
		Product productSaved = productRepository.save(product);
		
		UUID id = productSaved.getId();
		
		Optional<Product> productOptional = productRepository.findById(id);
		
		Assertions.assertThat(productOptional).isNotEmpty();
		Assertions.assertThat(productOptional.get().getDescription()).isEqualTo(productSaved.getDescription());
		Assertions.assertThat(productOptional.get().getId()).isEqualTo(productSaved.getId());
	}
	
	@Test
	@DisplayName("Find by ID returns a empty Object when no Product found")
	public void findById_ReturnsEmptyObject_whenProductNoFound() {
		Optional<Product> productOptional = productRepository.findById(UUID.randomUUID());
		Assertions.assertThat(productOptional).isEmpty();
	}
	
	@Test
	@DisplayName("Find by Description containing returns a List Product when successfull")
	public void findByDescriptionContaining_ReturnsProduct_whenSuccessfull() {
		Product product = createProduct();
			
		Product productSaved = productRepository.save(product);
		
		List<Product> productOptional = productRepository.findByDescriptionContainingIgnoreCase("Product Test");
		
		Assertions.assertThat(productOptional).isNotEmpty();
		Assertions.assertThat(productOptional).contains(productSaved);
	}
	
	@Test
	@DisplayName("Find by Description containing returns a empty List Product when no products found")
	public void findByDescriptionContaining_ReturnsEmptyListProduct_whenNoProductsFound() {
		List<Product> productOptional = productRepository.findByDescriptionContainingIgnoreCase("Product Test 1");
		
		Assertions.assertThat(productOptional).isEmpty();
	}
	
	@Test
	@DisplayName("getProductsByTypeWithStock returns a List Product when successfull")
	public void getProductsByTypeWithStock_ReturnsListProduct_whenSuccessfull() {
		List<ProductReportStockQuantity> productOptional = productRepository.getProductsByTypeWithStock(UUID.fromString("0136d610-a3b2-4986-8258-0e6caddb123f"));
		Assertions.assertThat(productOptional).isNotEmpty();
	}

	@Test
	@DisplayName("getProductsByTypeWithStock returns a empty List Product when no products found")
	public void getProductsByTypeWithStock_ReturnsEmptyListProduct_whenNoProductsFound() {
		List<ProductReportStockQuantity> productOptional = productRepository.getProductsByTypeWithStock(UUID.randomUUID());
		Assertions.assertThat(productOptional).isEmpty();
	}
	
	private Product createProduct() {
		Product product = new Product();
		product.setCode("1782364782634");
		product.setDescription("Product Test");
		product.setQuantity(1d);
		product.setPrice(100d);
		product.setType(null);
		return product;
	}
}