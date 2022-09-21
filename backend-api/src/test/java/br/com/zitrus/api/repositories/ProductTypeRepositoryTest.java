package br.com.zitrus.api.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.zitrus.api.entities.ProductType;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 20/09/2022
 */
@DataJpaTest
@DisplayName("Tests for Product Repository")
public class ProductTypeRepositoryTest {

	@Autowired
	private ProductTypeRepository productTypeRepository;
	
	@Test
	@DisplayName("Save ProductType when Successfull")
	public void save_PersistProductType_whenSuccessfull() {
		ProductType type = createProductType();
		ProductType productTypeSaved = productTypeRepository.save(type);
		Assertions.assertThat(productTypeSaved).isNotNull();
		Assertions.assertThat(productTypeSaved.getId()).isNotNull();
		Assertions.assertThat(productTypeSaved.getDescription()).isEqualTo(type.getDescription());
	}
	
	@Test
	@DisplayName("Update ProductType when Successfull")
	public void save_UpdateProductType_whenSuccessfull() {
		ProductType type = createProductType();
		ProductType productTypeSaved = productTypeRepository.save(type);
		
		productTypeSaved.setDescription("ProductType updated");
		
		ProductType productTypeUpdated = productTypeRepository.save(productTypeSaved);
		
		Assertions.assertThat(productTypeUpdated).isNotNull();
		Assertions.assertThat(productTypeUpdated.getId()).isNotNull();
		Assertions.assertThat(productTypeUpdated.getDescription()).isEqualTo(productTypeUpdated.getDescription());
	}
	
	@Test
	@DisplayName("Delete ProductType when Successfull")
	public void delete_RemovesProductType_whenSuccessfull() {
		ProductType type = createProductType();
		
		ProductType productTypeSaved = productTypeRepository.save(type);
		
		productTypeRepository.delete(productTypeSaved);
		
		Optional<ProductType> productOptional = productTypeRepository.findById(productTypeSaved.getId());
		
		Assertions.assertThat(productOptional).isEmpty();
	}
	
//	@Test
//	@DisplayName("Find by Code returns a ProductType when Successfull")
//	public void findByCode_ReturnsAProductType_whenSuccessfull() {
//		ProductType type = createProductType();
//		
//		ProductType productTypeSaved = productTypeRepository.save(type);
//		
//		String code = productTypeSaved.getCode();
//		
//		Optional<Product> productByCode = productTypeRepository.findByCode(code);
//		
//		Assertions.assertThat(productByCode).isNotEmpty();
//		Assertions.assertThat(productByCode.get().getId()).isNotNull();
//		Assertions.assertThat(productByCode.get().getCode()).isEqualTo(code);
//	}
//	
//	@Test
//	@DisplayName("Find by Code returns a Empty Object when no ProductType found")
//	public void findByCode_ReturnsEmptyObject_whenNoProductTypeFound() {
//		Optional<Product> productByCode = productTypeRepository.findByCode("847569347634");
//		Assertions.assertThat(productByCode).isEmpty();
//	}
	
	@Test
	@DisplayName("Find All returns a list ProductType when Successfull")
	public void findAll_ReturnsListProductType_whenSuccessfull() {
		ProductType type = createProductType();
		
		ProductType productTypeSaved = productTypeRepository.save(type);
		
		List<ProductType> products = productTypeRepository.findAll();
		
		Assertions.assertThat(products).isNotEmpty();
		Assertions.assertThat(products).contains(productTypeSaved);
	}
	
	@Test
	@DisplayName("Find by ID returns a ProductType when Successfull")
	public void findById_ReturnsProductType_whenSuccessfull() {
		ProductType type = createProductType();
		
		ProductType productTypeSaved = productTypeRepository.save(type);
		
		UUID id = productTypeSaved.getId();
		
		Optional<ProductType> productOptional = productTypeRepository.findById(id);
		
		Assertions.assertThat(productOptional).isNotEmpty();
		Assertions.assertThat(productOptional.get().getDescription()).isEqualTo(productTypeSaved.getDescription());
		Assertions.assertThat(productOptional.get().getId()).isEqualTo(productTypeSaved.getId());
	}
	
	@Test
	@DisplayName("Find by ID returns a empty Object when no ProductType found")
	public void findById_ReturnsEmptyObject_whenProductTypeNoFound() {
		Optional<ProductType> productTypeOptional = productTypeRepository.findById(UUID.randomUUID());
		Assertions.assertThat(productTypeOptional).isEmpty();
	}
	
	@Test
	@DisplayName("Find by Description containing returns a List ProductType when successfull")
	public void findByDescriptionContaining_ReturnsProductType_whenSuccessfull() {
		ProductType type = createProductType();
			
		ProductType productTypeSaved = productTypeRepository.save(type);
		
		Optional<ProductType> productTypesOptional = productTypeRepository.findByDescription("ProductType Test");
		
		Assertions.assertThat(productTypesOptional).isNotEmpty();
		Assertions.assertThat(productTypesOptional).contains(productTypeSaved);
	}
	
	@Test
	@DisplayName("Find by Description containing returns a empty List ProductType when no products found")
	public void findByDescriptionContaining_ReturnsEmptyListProductType_whenNoProductsFound() {
		Optional<ProductType> productTypesOptional = productTypeRepository.findByDescription("ProductType Test 1");
		Assertions.assertThat(productTypesOptional).isEmpty();
	}
	
	@Test
	@DisplayName("Find by Description containing returns a List ProductType when successfull")
	public void findByDescriptionContaining_ReturnsProduct_whenSuccessfull() {
		ProductType type = createProductType();
			
		ProductType productTypeSaved = productTypeRepository.save(type);
		
		List<ProductType> productOptional = productTypeRepository.findByDescriptionContainingIgnoreCase("ProductType Test");
		
		Assertions.assertThat(productOptional).isNotEmpty();
		Assertions.assertThat(productOptional).contains(productTypeSaved);
	}
	
	@Test
	@DisplayName("Find by Description containing returns a empty List ProductType when no products found")
	public void findByDescriptionContaining_ReturnsEmptyListProduct_whenNoProductsFound() {
		List<ProductType> productOptional = productTypeRepository.findByDescriptionContainingIgnoreCase("ProductType Test 1");
		Assertions.assertThat(productOptional).isEmpty();
	}
	
	private ProductType createProductType() {
		ProductType type = new ProductType();
		type.setDescription("ProductType Test");
		return type;
	}
}